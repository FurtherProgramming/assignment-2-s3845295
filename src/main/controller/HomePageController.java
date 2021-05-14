package main.controller;

import javafx.event.ActionEvent;
import java.io.IOException;

public class HomePageController {
    
    SceneController sceneController = new SceneController();

    public void loginButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO LOGIN.FXML
        sceneController.switchScene("login", event);
    }
    
    public void registerButton(ActionEvent event) throws IOException {
        // SWITCH SCENE TO REGISTER.FXML
        sceneController.switchScene("register", event);
    }


}