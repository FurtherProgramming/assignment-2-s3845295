package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController {
//    public HomePageModel homePageModel = new HomePageModel();

    public void loginButton(ActionEvent event) throws IOException {
        // go to login.fxml
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        stage.close();
    }
    
    public void registerButton(ActionEvent event) {

        // go to register.fmxl
    }


}