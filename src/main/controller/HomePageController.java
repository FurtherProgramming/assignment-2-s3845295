package main.controller;

import javafx.event.ActionEvent;
import main.helper.SceneHelper;

import java.io.IOException;

public class HomePageController {
    
//    SceneController sceneController = new SceneController();

    public void loginButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO LOGIN.FXML
        SceneHelper.switchScene("Login", event);
    }
    
    public void registerButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO REGISTER.FXML
        SceneHelper.switchScene("EmployeeRegister", event);
    }


}