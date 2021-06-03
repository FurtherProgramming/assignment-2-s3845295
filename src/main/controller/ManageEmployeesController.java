package main.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import main.helper.SceneHelper;
import main.model.ManageBookingsModel;
import main.model.ManageEmployeesModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ManageEmployeesController implements Initializable {

    private ManageEmployeesModel manageEmployeesModel = new ManageEmployeesModel();
    private ArrayList<Integer> selectedEmployeeIDs = new ArrayList<Integer>();
    private ArrayList<Object[]> employeeArrayList = new ArrayList<Object[]>();
    private HashMap<Integer, Integer> listViewIndexEmployeeIDMap = new HashMap<Integer, Integer>();

    @FXML
    private ListView<String> employeeListView = new ListView<String>();
    @FXML
    private Label statusLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ManageEmployeesController.initialize()");
        employeeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        refresh();
    }
    
    private void refresh() {
        try {manageEmployeesModel.populateEmployees(employeeArrayList);}
        catch (SQLException e) {e.printStackTrace();}

        System.out.println(employeeArrayList);

        populateEmployeeListView();

        if (employeeArrayList.size() == 0) { statusLabel.setText("There are no bookings to accept/reject"); }
        else { statusLabel.setText(""); }
        
    }

    protected void populateEmployeeListView() {
        System.out.println("populateEmployeeListView()");

        String[] categories = {"Employee ID", "Name", "Username", "Password", "Role", "Secret Question", "Secret Answer", "Admin"};
        int listCount = 0;

        for (Object[] booking : employeeArrayList) {
            String listString = "";
            for (int i = 0; i < categories.length; i++) {
                listString += categories[i] + ": " + booking[i] + " | ";
            }
            employeeListView.getItems().add(listString);
            listViewIndexEmployeeIDMap.put(listCount, (Integer)booking[0]);
            listCount++;
        }
    }

    
    private void sceneRefresh(ActionEvent event) throws IOException {
        SceneHelper.switchScene("ManageEmployees", event);
    }

    public void handleListViewSelection(MouseEvent event) {
        System.out.println("handleListViewSelection()");

        selectedEmployeeIDs.clear();
        ObservableList<Integer> selection = employeeListView.getSelectionModel().getSelectedIndices();
        for (Integer index : selection) {
            selectedEmployeeIDs.add(listViewIndexEmployeeIDMap.get(index));
        }

        System.out.println("selectedBookingIDs" + selectedEmployeeIDs);
//        validateButtons();
    }
    
    
    public void handleAddButton(ActionEvent event) {
        // OPEN ADD
    }
    
    public void handleEditButton(ActionEvent event) throws IOException {
        // OPEN EDIT

        // attach currently selected employee id to new scene
//        SceneHelper.switchScene("AddEmployee", event, selectedEmployeeIDs.get(0));

    }
    
    public void handleDeleteButton(ActionEvent event) {
        // delete selected IDS
    }
    
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("TableView", event);
    }
}
