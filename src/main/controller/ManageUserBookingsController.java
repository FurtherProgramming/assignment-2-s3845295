package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.helper.SceneHelper;
import main.object.User;

import java.io.IOException;
import java.sql.SQLException;

public class ManageUserBookingsController extends AbstractManageBookingsController {
    
    User user = User.getUser();

    @FXML
    protected Button editButton;
    
    protected void refresh() {

        try {manageBookingsModel.populateBookings(bookingArrayList, user);}
        catch (SQLException e) {e.printStackTrace();}

        System.out.println(bookingArrayList);

        super.populateBookingListView();

        if (bookingArrayList.size() == 0) { statusLabel.setText("There are no bookings to accept/reject"); }
        else { statusLabel.setText(""); }

    }

    protected void disableButtons(boolean disable) {
        editButton.setDisable(disable);
        rejectButton.setDisable(disable);

    }

    protected void sceneRefresh(ActionEvent event) throws IOException {
        SceneHelper.switchScene("ManageUserBookings", event);
    }

    public void handleEditButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("EditBooking", event, selectedBookingIDs.get(0));
    }
}
