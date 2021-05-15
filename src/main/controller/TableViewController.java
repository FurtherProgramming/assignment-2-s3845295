package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import main.model.RegisterModel;
import main.model.TableViewModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableViewController {

    public TableViewModel tableViewModel = new TableViewModel();

    @FXML
    private Label table1StatusLabel;
    @FXML
    private Label table2StatusLabel;
    @FXML
    private Label table3StatusLabel;
    @FXML
    private Label table4StatusLabel;
    @FXML
    private Label table5StatusLabel;
    @FXML
    private Label table6StatusLabel;
    @FXML
    private Label table7StatusLabel;
    @FXML
    private Label table8StatusLabel;

}
