package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import main.helper.SceneHelper;

import java.net.URL;
import java.util.ResourceBundle;


import main.helper.LockdownDate;

public class LockdownController implements Initializable {

    LockdownDate lockdownDate = LockdownDate.getLockdownDateInstance();

    @FXML
    Label status;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button OKButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void refresh() {
        if (startDatePicker.getValue() != null && endDatePicker.getValue() != null) {
            if (startDatePicker.getValue().compareTo(endDatePicker.getValue()) < 0) {
                status.setText("");
                OKButton.setDisable(false);
            }
            else {
                status.setText("End date must be after start date");
            }
        }
    }

    @FXML
    public void handleDatePick(ActionEvent event) {
        refresh();
    }
    
    @FXML
    public void handleOKButton(ActionEvent event) {
        lockdownDate.setLockdownStartDate(startDatePicker.getValue());
        lockdownDate.setLockdownEndDate(endDatePicker.getValue());

        SceneHelper.close(event);
    }

    @FXML
    public void handleCancelButton(ActionEvent event) {
        SceneHelper.close(event);
    }
    
}
