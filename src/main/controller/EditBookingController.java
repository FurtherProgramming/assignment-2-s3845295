package main.controller;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import main.helper.SceneHelper;
import main.model.ManageBookingsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*/
This class is the controller for the EditBooking.fxml view, and provides a confirmation dialogue with the user as to whether they want to edit their booking or not.
Editing a booking deletes it from the database so that the user may book another one.
 */

public class EditBookingController implements Initializable {

    private ManageBookingsModel manageBookingsModel = new ManageBookingsModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void handleOKButton(ActionEvent event) throws IOException {
        int bookingID = (int) SceneHelper.getPrimaryStageUserData();
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
