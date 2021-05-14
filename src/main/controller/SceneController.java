package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Node;

public class SceneController {
    
    public SceneController() {

    }
    
    public void switchScene(String sceneName, ActionEvent event) throws IOException {

        sceneName = "../ui/" + sceneName +  ".fxml";

        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene((scene));
    }


}
