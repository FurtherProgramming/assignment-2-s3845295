package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.model.ManageBookingsModel;

import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageBookingsController implements Initializable {

    private ManageBookingsModel manageBookingsModel = new ManageBookingsModel();
    
    private int selectedBookingID;

    private ArrayList<Object[]> bookingArrayList = new ArrayList<Object[]>();

    @FXML
    ListView<String> bookingListView = new ListView<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ManageBookingsController.initialize()");
        
        try {
            manageBookingsModel.populateBookings(bookingArrayList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        System.out.println(bookingArrayList);

        populateBookingListView();
    }

    private void populateBookingListView() {
        System.out.println("populateBookingListView()");
        
        String[] categories = {"Booking ID", "Date", "Table ID", "Name", "Username", "Accepted"};

        for (Object[] booking : bookingArrayList) {
            String listString = "";
            for (int i = 0; i < 6; i++) {
                listString += categories[i] + ": " + booking[i] + " | ";
            }
            System.out.println(listString);
            bookingListView.getItems().add(listString);
        }
    }

    


}
