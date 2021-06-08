package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import main.object.User;
import main.model.LoginModel;
import main.helper.SceneHelper;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label statusLabel;
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
    }

    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event) {

        try {
            if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {

                statusLabel.setText("Logged in successfully");

                // SWITCH TO TABLEVIEW SCENE
                SceneHelper.switchScene("TableView", event);
            } else {
                statusLabel.setText("username and password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // VALIDATE FORGOT PASSWORD BUTTON -- ONLY ENABLE WHEN USERNAME IS ENTERED
    public void handleKeyRelease(KeyEvent event) {
        forgotButton.setDisable(txtUsername.getText().isEmpty());
    }

    public void handleForgotPassword(ActionEvent event) throws IOException, SQLException {
        System.out.println("handleForgotPassword()");
        User user = User.getUser();
        user.setUser(txtUsername.getText());
        SceneHelper.switchScene("ResetPassword", event);
    }
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("Homepage", event);
}















}
