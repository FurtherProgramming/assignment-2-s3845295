package main.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Node;

public class SceneHelper {
    
    public SceneHelper() {

    }

    
    public static void switchScene(String sceneName, ActionEvent event) throws IOException {

        sceneName = "../ui/" + sceneName +  ".fxml";

        Parent root = FXMLLoader.load(SceneHelper.class.getResource(sceneName));
        Scene scene = new Scene(root);

        // SWITCH primaryStage TO scene
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setScene(scene);
    }


}
