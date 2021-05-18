package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import main.model.RegisterModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public RegisterModel registerModel = new RegisterModel();
    SceneController sceneController = new SceneController();

    @FXML
    private Label isConnected;
    
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtSurname;
    @FXML
    public TextField intAge;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;

    public RegisterController() {

    }

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (registerModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }
    }
    
    public void Register(ActionEvent event) throws IOException {
        try {
            String name = txtName.getText();
            String surname = txtSurname.getText();
            int age = Integer.parseInt(intAge.getText());
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            registerModel.registerToDb(name, surname, age, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            sceneController.switchScene("tableView", event);
        }
    }


}
