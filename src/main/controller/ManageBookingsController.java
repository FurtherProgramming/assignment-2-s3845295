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

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.net.URL;


class ManageBookingsController implements Initializable {

    // TODO
    // auto reject any booking requests that haven't been accepted by current day

    protected ManageBookingsModel manageBookingsModel = new ManageBookingsModel();
    protected ArrayList<Integer> selectedBookingIDs = new ArrayList<Integer>();
    protected ArrayList<Object[]> bookingArrayList = new ArrayList<Object[]>();
    protected HashMap<Integer, Integer> listViewIndexBookingIDMap = new HashMap<Integer, Integer>();

    @FXML
    protected ListView<String> bookingListView = new ListView<String>();
    @FXML
    protected Label statusLabel;
    @FXML
    protected Button acceptButton;
    @FXML
    protected Button rejectButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ManageBookingsController.initialize()");
        bookingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        refresh();
    }

    // OVERRIDDEN METHOD
    protected void refresh() {
    }
    
    protected void validateButtons() {
        disableButtons(false);
        if (selectedBookingIDs.size() == 0) {
            disableButtons(true);
        }
    }

    // OVERRIDDEN METHOD
    protected void disableButtons(boolean disable) {
    }

    // OVERRIDDEN METHOD
    protected void sceneRefresh(ActionEvent event) throws IOException {
    }

    protected void populateBookingListView() {
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
}
