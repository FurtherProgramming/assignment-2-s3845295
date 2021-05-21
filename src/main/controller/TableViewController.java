package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import main.datastructure.Table;
import main.datastructure.Table.Status;
import main.helper.CurrentDate;
import main.helper.User;
import main.model.TableViewModel;
import main.helper.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.HashMap;


public class TableViewController implements Initializable {
    
    
    private TableViewModel tableViewModel = new TableViewModel();

    private User user = User.getUser();
    
    private Table selectedTable;
    private LocalDate selectedDate;
    
    private final Color colourAvailable = Color.rgb(104,186,102);
    private final Color colourSelected = Color.rgb(188,227,143);
    private final Color colourBooked = Color.rgb(255,137,137);
    private final Color colourLocked = Color.rgb(255,173,111);
    private final Color colourPrevBooked = Color.rgb(184, 140, 209);
    private final Color colourUserBooked = Color.rgb(119, 186, 224);
    private final Color colourPending = Color.rgb(190,190,190);

    private HashMap<Status, Color> statusColourMap = new HashMap<Status, Color>();
    private HashMap<Rectangle, Table> rectangleTableMap = new HashMap<Rectangle, Table>();
    private HashMap<Status, String> statusTextMap = new HashMap<Status, String>();

    
    @FXML
    private Label statusLabel;
    @FXML
    private Menu adminMenu;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button bookButton;

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
    private Rectangle table1Rectangle;
    @FXML
    private Rectangle table2Rectangle;
    @FXML
    private Rectangle table3Rectangle;
    @FXML
    private Rectangle table4Rectangle;
    @FXML
    private Rectangle table5Rectangle;
    @FXML
    private Rectangle table6Rectangle;
    @FXML
    private Rectangle table7Rectangle;
    @FXML
    private Rectangle table8Rectangle;
    
    Table table1;
    Table table2;
    Table table3;
    Table table4;
    Table table5;
    Table table6;
    Table table7;
    Table table8;

    private Table[] tableArray;


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

        table1 = new Table (1, table1Label, table1Rectangle);
        table2 = new Table (2, table2Label, table2Rectangle);
        table3 = new Table (3, table3Label, table3Rectangle);
        table4 = new Table (4, table4Label, table4Rectangle);
        table5 = new Table (5, table5Label, table5Rectangle);
        table6 = new Table (6, table6Label, table6Rectangle);
        table7 = new Table (7, table7Label, table7Rectangle);
        table8 = new Table (8, table8Label, table8Rectangle);

        tableArray = new Table[]{table1, table2, table3, table4, table5, table6, table7, table8};

        initialiseStatusColourMap();
        initialiseStatusTextMap();
        initialiseRectangleTableMap();

        setBookButtonDisable(true);

        if (tableViewModel.isUserAdmin(user)) {
            System.out.println("USERADMIN");
            setAdminDisable(false);
        }
        
        datePicker.setValue(CurrentDate.getCurrentDate());
        selectedDate = CurrentDate.getCurrentDate();
        refresh();
        
    }

    
    private void initialiseStatusColourMap() {
        statusColourMap.put(Status.AVAILABLE, colourAvailable);
        statusColourMap.put(Status.BOOKED, colourBooked);
        statusColourMap.put(Status.LOCKED, colourLocked);
        statusColourMap.put(Status.PREVBOOKED, colourPrevBooked);
        statusColourMap.put(Status.USERBOOKED, colourUserBooked);
        statusColourMap.put(Status.PENDING, colourPending);
    }

    private void initialiseStatusTextMap() {
        statusTextMap.put(Status.BOOKED, "Not Available");
        statusTextMap.put(Status.LOCKED, "Locked");
        statusTextMap.put(Status.PREVBOOKED, "Table was previously booked");
        statusTextMap.put(Status.USERBOOKED, "Confirmed Booking for " + user.getUsername());
        statusTextMap.put(Status.PENDING, "Pending Confirmation");

    }
    
    private void initialiseRectangleTableMap() {
        rectangleTableMap.put(table1Rectangle, table1);
        rectangleTableMap.put(table2Rectangle, table2);
        rectangleTableMap.put(table3Rectangle, table3);
        rectangleTableMap.put(table4Rectangle, table4);
        rectangleTableMap.put(table5Rectangle, table5);
        rectangleTableMap.put(table6Rectangle, table6);
        rectangleTableMap.put(table7Rectangle, table7);
        rectangleTableMap.put(table8Rectangle, table8);
    }

    private void refresh() {
        selectedTable = null;

        for (Table table : tableArray) {
            tableViewModel.updateTableStatus(table, user.getUserID(), selectedDate);
            updateLabelText(table);
            updateRectangleColour(table);
            updateRectangleDisable(table);
        }

        setBookButtonDisable(true);
        validateBookButton();
    }

    private void updateLabelText(Table table) {
        if (table.getStatus() != Status.AVAILABLE) {
            table.getLabel().setText(statusTextMap.get(table.getStatus()));
        }
        else if (table.getStatus() == Status.AVAILABLE) {
            table.getLabel().setText("Table " + table.getTableID());
        }
    }

    private void updateRectangleColour(Table table) {
        System.out.println("updateRectangleColour()");
        System.out.println(table.getTableID() + " " + table.getStatus());
        table.getRectangle().setFill(statusColourMap.get(table.getStatus()));
    }
    
    private void updateRectangleDisable(Table table) {
        table.getRectangle().setDisable(false);
        if (table.getStatus() != Status.AVAILABLE) {
            table.getRectangle().setDisable(true);
        }
    }
    
    private void validateBookButton() {
        if (tableViewModel.canUserBook(user)) {
            setBookButtonDisable(false);
        }
    }

    private void setBookButtonDisable(boolean disable) {
        bookButton.setDisable(disable);
    }

    private void setAdminDisable(boolean disable) {
        adminMenu.setDisable(disable);
    }


    @FXML
    public void handleDatePick(ActionEvent event) {
        selectedDate = datePicker.getValue();
        System.out.println("handleDatePick()");
        refresh();
        statusLabel.setText("");
    }

    // BOOK TABLE
    @FXML
    public void handleBookButton(ActionEvent event) throws SQLException {

        int employeeID = user.getUserID();
        int tableID = selectedTable.getTableID();
        
        if (selectedDate == null) {
            statusLabel.setText("Please select a date before booking a table");
            return;
        }
        else {
            statusLabel.setText("Booked table " + tableID + " for " + user.getUsername() + " on " + selectedDate);
        }

        tableViewModel.bookTable(employeeID, tableID, selectedDate);
        refresh();
    }
    
    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        SceneHelper.switchScene("homePage", event);
    }


    // CHANGE SELECTED TABLE COLOUR, SET selectedTable to table
    @FXML
    public void handleMouseClick(MouseEvent event) {

        Node node = (Node)event.getSource();
        Rectangle rectangle = (Rectangle)node;
        Table table = rectangleTableMap.get(rectangle);

        if (selectedTable == null) {
            selectedTable = table;
        }

        table.getRectangle().setFill(colourSelected);

        if (table != selectedTable) {
            selectedTable.getRectangle().setFill(statusColourMap.get(selectedTable.getStatus()));
        }
        selectedTable = table;
        setBookButtonDisable(false);
    }

    // ANIMATE MOUSE HOVER
    @FXML 
    public void handleMouseEnter(MouseEvent event) {
        Node node = (Node)event.getSource();
        
        node.setScaleX(1.05);
        node.setScaleY(1.05);
    }
    @FXML
    public void handleMouseExit(MouseEvent event) {
        Node node = (Node)event.getSource();

        node.setScaleX(1);
        node.setScaleY(1);
    }
    
    //ADMIN MENU
    public void handleMenuItemManageBookings(ActionEvent event) throws IOException {
        SceneHelper.switchScene("manageBookings", event);
    }


}
