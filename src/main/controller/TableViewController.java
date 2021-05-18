package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import main.model.TableViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import main.ui.SceneHelper;



import javafx.scene.Node;


public class TableViewController implements Initializable {
    
    
    private TableViewModel tableViewModel = new TableViewModel();
    
    private Node selectedTable;
    private LocalDate selectedDate;


    @FXML
    private Label table1Label;
    @FXML
    private Label table2Label;
    @FXML
    private Label table3Label;
    @FXML
    private Label table4Label;
    @FXML
    private Label table5Label;
    @FXML
    private Label table6Label;
    @FXML
    private Label table7Label;
    @FXML
    private Label table8Label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table1Label.setText("Table 1");
        table2Label.setText("Table 2");
        table3Label.setText("Table 3");
        table4Label.setText("Table 4");
        table5Label.setText("Table 5");
        table6Label.setText("Table 6");
        table7Label.setText("Table 7");
        table8Label.setText("Table 8");
        
        tableStatusRefresh();
    }

    private void tableStatusRefresh() {
        // REFRESH TABLE STATUS & CHANGE COLOURS+LABELS+NODEAVAILABILITY
        // USE MODEL TO ACCESS DATABASE
        // iF BOOKED BY USER -> different colour
    }


    @FXML
    public void handleBookButton(ActionEvent event) throws SQLException {
        int employeeID = -1;
        int tableID = Integer.parseInt(selectedTable.getAccessibleText());
        LocalDate date = selectedDate;

        System.out.println("Booking table no: " + tableID + " for user: " + employeeID + " for date: " + date);
        tableViewModel.bookTable(employeeID, tableID, date);
    }
    
    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        SceneHelper.switchScene("homePage", event);
    }



    @FXML
    public void handleMouseClick(MouseEvent event) {

        Node node = (Node) event.getSource();
        selectedTable = node;

    }
    
    @FXML 
    public void handleMouseEnter(MouseEvent event) {
        Node node = (Node)event.getSource();
        
        node.setScaleX(1.1);
        node.setScaleY(1.1);
    }
    @FXML
    public void handleMouseExit(MouseEvent event) {
        Node node = (Node)event.getSource();

        node.setScaleX(1);
        node.setScaleY(1);
    }




}
