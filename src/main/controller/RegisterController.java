package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import main.model.RegisterModel;
import main.helper.SceneHelper;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public RegisterModel registerModel = new RegisterModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtSecretQuestion;
    @FXML
    private TextField txtSecretAnswer;

    @FXML
    private Button registerButton;

    public RegisterController() {

    }

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setDisable(true);
    }

    // ONLY MAKE REGISTER BUTTON AVAILABLE TO PRESS WHEN ALL FIELDS ARE POPULATED
    public void handleTextFieldKeyRelease(KeyEvent event) {
        if (    !txtFirstName.getText().isEmpty() && 
                !txtLastName.getText().isEmpty() &&
                !txtRole.getText().isEmpty() &&
                !txtUsername.getText().isEmpty() &&
                !txtPassword.getText().isEmpty() &&
                !txtSecretQuestion.getText().isEmpty() &&
                !txtSecretAnswer.getText().isEmpty()) {
            registerButton.setDisable(false);
        }
    }
    
    public void register(ActionEvent event) throws IOException {
        try {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String role = txtRole.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String secretQuestion = txtSecretQuestion.getText();
            String secretAnswer = txtSecretAnswer.getText();

            registerModel.registerToDb(firstName, lastName, role, username, password, secretQuestion, secretAnswer);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SceneHelper.switchScene("tableView", event);
        }
    }


}
