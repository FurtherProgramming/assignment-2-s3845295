package main.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import javafx.scene.Node;

public class SceneHelper {

    private static Stage primaryStage;
    
    public SceneHelper() {

    }

    public static void switchScene(String sceneName, ActionEvent event) throws IOException {

        sceneName = "../ui/" + sceneName +  ".fxml";

        Parent root = FXMLLoader.load(SceneHelper.class.getResource(sceneName));
        Scene scene = new Scene(root);

        // SWITCH primaryStage TO scene
        if (primaryStage == null) {
            primaryStage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        }
        primaryStage.setScene(scene);
    }
    
    public static void newScene(String sceneName, ActionEvent event) throws IOException {

        sceneName = "../ui/" + sceneName +  ".fxml";
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(SceneHelper.class.getResource(sceneName));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void close(ActionEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }


}
