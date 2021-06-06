package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.helper.CurrentDate;
import main.helper.SceneHelper;
import main.object.User;

import java.io.IOException;
import java.sql.SQLException;

public class ManageBookingsUserController extends AbstractManageBookingsController {
    
    User user = User.getUser();

    @FXML
    protected Button editButton;
    
    protected void refresh() {

        try {manageBookingsModel.populateBookings(bookingArrayList, user, CurrentDate.getCurrentDate());}
        catch (SQLException e) {e.printStackTrace();}

        System.out.println(bookingArrayList);

        super.populateBookingListView();

        if (bookingArrayList.size() == 0) { statusLabel.setText("There are no bookings to edit or cancel."); }
        else { statusLabel.setText(""); }

    }

    // OVERLOAD
    protected void validateButtons() {
        disableButtons(false);
        if (selectedBookingIDs.size() == 0) {
            disableButtons(true);
        }

        // ONLY EDIT BOOKING > 48 HOURS
        editButton.setDisable(true);
        if (manageBookingsModel.canUserEditBooking(selectedBookingIDs.get(0), CurrentDate.getCurrentDate())) {
            editButton.setDisable(false);
        }
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
