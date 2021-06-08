package main.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import javafx.scene.Node;

/*/
Helper class to manage switching scenes. Static methods may be called with the name of the requested scene, and SceneHelper will switch/ create a new scene.
Allows for the repeated reuse of code.
Methods are overloaded to include UserData if required to be attached to the stage.
 */

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

    // OVERLOAD -- ATTACH USERDATA
    public static void switchScene(String sceneName, ActionEvent event, Object userData) throws IOException {
        switchScene(sceneName, event);
        primaryStage.setUserData(userData);
    }

    
    public static void newScene(String sceneName, ActionEvent event) throws IOException {
        sceneName = "../ui/" + sceneName + ".fxml";

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(SceneHelper.class.getResource(sceneName));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    // OVERLOAD -- ATTACH USERDATA
    public static void newScene(String sceneName, ActionEvent event, Object userData) throws IOException {

        sceneName = "../ui/" + sceneName +  ".fxml";

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(SceneHelper.class.getResource(sceneName));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
        stage.setUserData(userData);
    }
    
    public static void close(ActionEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

    public static Object getPrimaryStageUserData() {
        return primaryStage.getUserData();
    }
}
