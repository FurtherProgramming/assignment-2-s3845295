package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import main.helper.SceneHelper;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;
import main.helper.User;
import main.model.ResetPasswordModel;

import java.util.Random;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    
    User user = User.getUser();
    ResetPasswordModel resetPasswordModel = new ResetPasswordModel();
    
    @FXML
    Label questionLabel;
    @FXML
    Label statusLabel;
    @FXML
    Button resetButton;
    @FXML
    TextField txtAnswer;
    @FXML
    TextField txtPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionLabel.setText(user.getSecretQuestion());
        statusLabel.setText("");
        resetButton.setDisable(true);
    }

    public void handleKeyRelease(KeyEvent event) {
        if (!txtAnswer.getText().isEmpty()) {
            resetButton.setDisable(false);
            txtPassword.setText(getRandomPassword());
        }
        else {
            resetButton.setDisable(true);
            txtPassword.clear();
        }

    }
    
    private String getRandomPassword() {
        String password = "";
        Random random = new Random();
        
        for (int i = 0; i < 8; i++) {
            password += random.nextInt(9);
        }

        return password;
    }

    public void handleResetButton(ActionEvent event) {
        if (resetPasswordModel.validSecretAnswer(user, txtAnswer.getText())) {
            resetPasswordModel.resetPassword(user, txtPassword.getText());
        }
    }

    public void handleCancelButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("homePage", event);
    }

    
}
