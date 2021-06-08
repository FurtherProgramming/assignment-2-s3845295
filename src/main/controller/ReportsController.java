package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.helper.SceneHelper;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import main.model.ReportsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*/
Controller for GenerateReports.fxml
Calls the generate...() methods of reportsModel to write CSV files to ./Reports/
 */

public class ReportsController implements Initializable {
    
    ReportsModel reportsModel = new ReportsModel();

    @FXML
    Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ReportsController.initialize()");
    }
    
    public void handleBookingsReportButton(ActionEvent event) throws IOException {
        System.out.println("ReportsController.handleBookingsReportButton()");
        status.setText("Report written to: " + reportsModel.generateBookingsCSV());
    }

    public void handleEmployeesReportButton(ActionEvent event) throws IOException {
        System.out.println("ReportsController.handleEmployeesReportButton()");
        status.setText("Report written to: " + reportsModel.generateEmployeesCSV());
    }
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("TableView", event);
    }
}
