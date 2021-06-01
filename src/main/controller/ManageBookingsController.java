package main.controller;

import javafx.collections.ObservableList;
import main.helper.CurrentDate;
import main.helper.ManageBookingsHelper;
import main.helper.SceneHelper;
import main.model.ManageBookingsModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import main.object.User;

import java.net.URL;
import java.sql.SQLException;


public class ManageBookingsController implements Initializable {

    // TODO
    // auto reject any booking requests that haven't been accepted by current day

    private ManageBookingsModel manageBookingsModel = new ManageBookingsModel();
    private ArrayList<Integer> selectedBookingIDs = new ArrayList<Integer>();
    private ArrayList<Object[]> bookingArrayList = new ArrayList<Object[]>();
    private HashMap<Integer, Integer> listViewIndexBookingIDMap = new HashMap<Integer, Integer>();

    private ManageBookingsHelper manageBookingsHelper = ManageBookingsHelper.getInstance();
    private boolean userSpecificQuery;
    private User user = User.getUser();

    @FXML
    ListView<String> bookingListView = new ListView<String>();
    @FXML
    Label statusLabel;
    @FXML
    Button acceptButton;
    @FXML
    Button rejectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ManageBookingsController.initialize()");
        bookingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        refresh();
    }
    
    private void refresh() {
        userSpecificQuery = manageBookingsHelper.isUserSpecific();
        try {
            if (userSpecificQuery) {
                System.out.println("USER SPECIFIC");
                manageBookingsModel.populateBookings(bookingArrayList, user);
            }
            else {
                System.out.println("USER AGNOSTIC");
                manageBookingsModel.populateBookings(bookingArrayList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bookingArrayList);
        
        populateBookingListView();
        
        if (bookingArrayList.size() == 0) {
            statusLabel.setText("There are no bookings to accept/reject");
        }
        else {
            statusLabel.setText("");
        }

    }
    
    private void validateButtons() {
        disableButtons(false);
        if (selectedBookingIDs.size() == 0) {
            disableButtons(true);
        }
    }
    
    private void disableButtons(boolean disable) {
        if (!userSpecificQuery) {
            acceptButton.setDisable(disable);
        }
        rejectButton.setDisable(disable);
    }

    private void sceneRefresh(ActionEvent event) throws IOException {
        if (userSpecificQuery) {
            SceneHelper.switchScene("ManageUserBookings", event);
        }
        else {
            SceneHelper.switchScene("ManageAdminBookings", event);
        }
        
    }

    private void populateBookingListView() {
        System.out.println("populateBookingListView()");
        
        String[] categories = {"Booking ID", "Date", "Table ID", "Name", "Username", "Accepted"};
        int listCount = 0;

        for (Object[] booking : bookingArrayList) {
            String listString = "";
            for (int i = 0; i < 6; i++) {
                listString += categories[i] + ": " + booking[i] + " | ";
            }
            bookingListView.getItems().add(listString);
            listViewIndexBookingIDMap.put(listCount, (Integer)booking[0]);
            listCount++;

        }
    }

    @FXML
    public void handleListViewSelection(MouseEvent event) {
        System.out.println("handleListViewSelection()");

        selectedBookingIDs.clear();
        ObservableList<Integer> selection = bookingListView.getSelectionModel().getSelectedIndices();
        for (Integer index : selection) {
            selectedBookingIDs.add(listViewIndexBookingIDMap.get(index));
        }

        System.out.println("selectedBookingIDs" + selectedBookingIDs);
        validateButtons();
    }

    @FXML
    public void handleAcceptButton(ActionEvent event) throws SQLException, IOException {
        for (Integer bookingID : selectedBookingIDs) {
            manageBookingsModel.acceptBooking(bookingID);
        }
        sceneRefresh(event);
    }

    @FXML
    public void handleRejectButton(ActionEvent event) throws IOException {
        for (Integer bookingID : selectedBookingIDs) {
            System.out.println("REJECTING bookingID: " + bookingID);
            manageBookingsModel.rejectBooking(bookingID);
        }
        sceneRefresh(event);
    }
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("TableView", event);
    }
    
    public void handleEditButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("EditBooking", event, selectedBookingIDs.get(0));
    }

}
