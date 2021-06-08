package main.controller;

import javafx.event.ActionEvent;
import main.helper.SceneHelper;

import java.io.IOException;

public class HomePageController {

    public void handleLoginButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO LOGIN.FXML
        SceneHelper.switchScene("Login", event);
    }
    
    public void handleRegisterButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO REGISTER.FXML
        SceneHelper.switchScene("EmployeeRegister", event);
    }
    
    public void handleQuitButton(ActionEvent event) {
        SceneHelper.close(event);
    }

}