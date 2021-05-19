package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import main.helper.User;
import main.model.TableViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import main.helper.SceneHelper;



import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class TableViewController implements Initializable {
    
    
    private TableViewModel tableViewModel = new TableViewModel();

    private User user;
    
    private Node selectedTable;
    private LocalDate selectedDate;
    
    Color colourAvailable = Color.rgb(104,186,102);
    Color colourSelected = Color.rgb(188,227,143);
    Color colourBooked = Color.rgb(255,137,137);
    Color colourLocked = Color.rgb(255,173,111);
    Color colourUserBooked = Color.rgb(109,167,167);

    @FXML
    private Label status;

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

    @FXML
    private DatePicker datePicker;

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

        
        refresh();
    }

    private void refresh() {
        // REFRESH TABLE STATUS & CHANGE COLOURS+LABELS+NODEAVAILABILITY
        // USE MODEL TO ACCESS DATABASE
        // iF BOOKED BY USER -> different colour
        
        user = User.getUser();

    }

    @FXML
    public void handleDatePick(ActionEvent event) {
        selectedDate = datePicker.getValue();
        refresh();
    }

    // BOOK TABLE
    @FXML
    public void handleBookButton(ActionEvent event) throws SQLException {
        if (selectedDate == null) {
            status.setText("Please select a date before booking a table");
            return;
        }

        int employeeID = user.getUserID();
        int tableID = Integer.parseInt(selectedTable.getAccessibleText());

        System.out.println("Booking table no: " + tableID + " for user: " + employeeID + " for date: " + selectedDate);
        tableViewModel.bookTable(employeeID, tableID, selectedDate);
    }
    
    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        SceneHelper.switchScene("homePage", event);
    }


    // CHANGE SELECTED TABLE COLOUR, SET selectedTable to node
    @FXML
    public void handleMouseClick(MouseEvent event) {

        Node node = (Node)event.getSource();

        if (selectedTable == null) {
            selectedTable = node;
        }

        ((Rectangle)node).setFill(colourSelected);

        if (node != selectedTable) {
            ((Rectangle)selectedTable).setFill(colourAvailable);
        }

        selectedTable = node;
    }

    // ANIMATE MOUSE HOVER
    @FXML 
    public void handleMouseEnter(MouseEvent event) {
        Node node = (Node)event.getSource();
        
        node.setScaleX(1.05);
        node.setScaleY(1.05);
        
        node.setRotate(1);
    }
    @FXML
    public void handleMouseExit(MouseEvent event) {
        Node node = (Node)event.getSource();

        node.setScaleX(1);
        node.setScaleY(1);
        
        node.setRotate(0);
    }


}
