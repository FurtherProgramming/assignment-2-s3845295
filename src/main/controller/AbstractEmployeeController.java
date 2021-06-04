package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.helper.SceneHelper;
import main.model.ManageEmployeesModel;
import main.object.User;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

abstract class AbstractEmployeeController implements Initializable {

    protected ManageEmployeesModel manageEmployeesModel = new ManageEmployeesModel();
    protected User user = User.getUser();

    @FXML
    protected TextField txtFirstName;
    @FXML
    protected TextField txtLastName;
    @FXML
    protected TextField txtRole;
    @FXML
    protected TextField txtUsername;
    @FXML
    protected TextField txtPassword;
    @FXML
    protected TextField txtSecretQuestion;
    @FXML
    protected TextField txtSecretAnswer;
    @FXML
    protected CheckBox boxAdmin;
    @FXML
    protected Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setDisable(true);
    }

    // ONLY MAKE SAVE BUTTON AVAILABLE TO PRESS WHEN ALL FIELDS ARE POPULATED
    public void handleTextFieldKeyRelease(KeyEvent event) {
        System.out.println("AbstractTextFieldKeyRelease()");
        if (    !txtFirstName.getText().isEmpty() &&
                !txtLastName.getText().isEmpty() &&
                !txtRole.getText().isEmpty() &&
                !txtUsername.getText().isEmpty() &&
                !txtPassword.getText().isEmpty() &&
                !txtSecretQuestion.getText().isEmpty() &&
                !txtSecretAnswer.getText().isEmpty()) {
            saveButton.setDisable(false);
        }
    }

    public abstract void save(ActionEvent event) throws IOException;
    

    
}
