package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import main.helper.SceneHelper;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LockdownController implements Initializable {
    
    private LocalDate lockdownStartDate;
    private LocalDate lockdownEndDate;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    public void handleOKButton(ActionEvent event) {


        SceneHelper.close(event);

        
    }

    @FXML
    public void handleCancelButton(ActionEvent event) {
        SceneHelper.close(event);
    }
    
}
