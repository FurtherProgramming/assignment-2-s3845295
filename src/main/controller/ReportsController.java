package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.helper.SceneHelper;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML
    Label status;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void handleBookingsReportButton(ActionEvent event) {

    }

    public void handleEmployeesReportButton(ActionEvent event) {

    }
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("TableView", event);
    }
}
