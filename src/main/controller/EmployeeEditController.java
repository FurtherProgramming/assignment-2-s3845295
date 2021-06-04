package main.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import main.helper.SceneHelper;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeEditController extends AbstractEmployeeController {
    
    private int employeeID;

    // GET EMPLOYEE ID FROM SCENE USERDATA
    public void handleMouseEnter(MouseEvent event) {
        if (employeeID == 0) {
            employeeID = (int) SceneHelper.getPrimaryStageUserData();
            
            Object[] employeeInfo = manageEmployeesModel.getEmployeeInfo(employeeID);
            
            txtFirstName.setText(((String)employeeInfo[1]).split(" ")[0]);
            txtLastName.setText(((String)employeeInfo[1]).split(" ")[1]);
            txtRole.setText((String)employeeInfo[4]);
            txtUsername.setText((String)employeeInfo[2]);
            txtPassword.setText((String)employeeInfo[3]);
            txtSecretQuestion.setText((String)employeeInfo[5]);
            txtSecretAnswer.setText((String)employeeInfo[6]);
            System.out.println((boolean)employeeInfo[7]);
            boxAdmin.setSelected((boolean)employeeInfo[7]);

        }
    }


    public void save(ActionEvent event) throws IOException {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String secretQuestion = txtSecretQuestion.getText();
        String secretAnswer = txtSecretAnswer.getText();
        boolean admin = boxAdmin.isSelected();

        try {
            manageEmployeesModel.editEmployee(employeeID, firstName, lastName, role, username, password, secretQuestion, secretAnswer, admin);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SceneHelper.switchScene("ManageEmployees", event);
        }
    }


}
