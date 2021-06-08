package main.controller;

import javafx.event.ActionEvent;
import main.helper.SceneHelper;
import java.io.IOException;
import java.sql.SQLException;

/*/
This is the controller for the EmployeeAdd.fxml view. It calls manageEmployeesModel.addEmployee() to add a new employee to the database.
 */

public class EmployeeAddController extends AbstractEmployeeController {

    public void handleSaveButton(ActionEvent event) throws IOException {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String secretQuestion = txtSecretQuestion.getText();
        String secretAnswer = txtSecretAnswer.getText();
        boolean admin = boxAdmin.isSelected();

        try {
            manageEmployeesModel.addEmployee(firstName, lastName, role, username, password, secretQuestion, secretAnswer, admin);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SceneHelper.switchScene("ManageEmployees", event);
        }
    }
    
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneHelper.switchScene("ManageEmployees", event);
    }
}
