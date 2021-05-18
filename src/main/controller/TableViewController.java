package main.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.model.TableViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


import javafx.scene.Node;


public class TableViewController implements Initializable {
    
    
    private SceneController sceneController = new SceneController();
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


//     MouseEvent IS BEING LISTENED TO BY PANE NOT RECTANGLE
    @FXML
    public void handleTableClick(MouseEvent event) {

        Node node = (Node)event.getSource();
        node.setRotate(node.getRotate()+20);
        System.out.println(node);

        // ONLY GREEN TABLES SHOULD BE ALLOWED TO BE SELECTED, IF STATUS!=GREEN, DISABLE NODE
        selectedTable = node;
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
        sceneController.switchScene("homePage", event);
    }



    // TESTING
    @FXML
    public void handleMouseClick(MouseEvent event) {

        Node node = (Node)event.getSource();
        node.setRotate(node.getRotate()+20);
    }


    public void rotate(ActionEvent event) {

        Node node = (Node)event.getSource();
        node.setRotate(node.getRotate()+20);
        System.out.println(node);
    }


    


}
