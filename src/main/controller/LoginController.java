package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import main.helper.User;
import main.model.LoginModel;
import main.helper.SceneHelper;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button forgotButton;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forgotButton.setDisable(true);
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        }
        else {
            isConnected.setText("Not Connected");
        }

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event){

        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())) {

                isConnected.setText("Logged in successfully");

                // SWITCH TO TABLEVIEW SCENE
                SceneHelper.switchScene("tableView", event);
            }
            else {
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleKeyRelease(KeyEvent event) {
        if (!txtUsername.getText().isEmpty()) {
            forgotButton.setDisable(false);
        }
        else {
            forgotButton.setDisable(true);
        }
    }
    
    public void handleForgotPassword(ActionEvent event) throws IOException, SQLException {
        System.out.println("handleForgotPassword()");
        User user = User.getUser();
//        user.setUsername(txtUsername.getText());
        user.setUser(txtUsername.getText());
        SceneHelper.switchScene("resetPassword", event);
    }















}
