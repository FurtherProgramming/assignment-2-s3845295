package main.controller;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.stage.Stage;
import main.helper.SceneHelper;
import main.model.ManageBookingsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookingController implements Initializable {

    private ManageBookingsModel manageBookingsModel = new ManageBookingsModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void handleOKButton(ActionEvent event) throws IOException {
        int bookingID = (int) SceneHelper.getStageUserData(event);
        try {
            manageBookingsModel.rejectBooking(bookingID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        SceneHelper.switchScene("TableView", event);
    }
    
    public void handleCancelButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("ManageUserBookings", event);
    }
}
