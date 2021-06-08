package main.controller;

import javafx.event.ActionEvent;
import main.helper.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeRegisterController extends AbstractEmployeeController {


    public void handleSaveButton(ActionEvent event) throws IOException {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String secretQuestion = txtSecretQuestion.getText();
        String secretAnswer = txtSecretAnswer.getText();

        try {
            manageEmployeesModel.addEmployee(firstName, lastName, role, username, password, secretQuestion, secretAnswer);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SceneHelper.switchScene("Homepage", event);
        }
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("Homepage", event);
    }
}
