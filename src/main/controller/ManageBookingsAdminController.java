package main.controller;

import javafx.event.ActionEvent;
import main.helper.SceneHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ManageBookingsAdminController extends AbstractManageBookingsController {

    protected void refresh() {

        try {manageBookingsModel.populateBookings(bookingArrayList);}
        catch (SQLException e) {e.printStackTrace();}

        System.out.println(bookingArrayList);

        populateBookingListView();

        if (bookingArrayList.size() == 0) { statusLabel.setText("There are no bookings to accept or reject."); }
        else { statusLabel.setText(""); }

    }

    protected void disableButtons(boolean disable) {
        acceptButton.setDisable(disable);
        rejectButton.setDisable(disable);
    }

    protected void sceneRefresh(ActionEvent event) throws IOException {
        SceneHelper.switchScene("ManageAdminBookings", event);
    }

    public void handleAcceptButton(ActionEvent event) throws SQLException, IOException {
        for (Integer bookingID : selectedBookingIDs) {
            manageBookingsModel.acceptBooking(bookingID);
        }
        sceneRefresh(event);
    }
}
