package main.model;

import main.SQLConnection;
import main.helper.CurrentDate;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageEmployeesModel {
    
    Connection connection;
    
    public ManageEmployeesModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public void populateEmployees(ArrayList<Object[]> employeeArrayList) throws SQLException {
        System.out.println("ManageEmployeesModel.populateEmployees()");

        String sqlQUERY =   "SELECT * " +
                            "FROM Employee";
                
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] employeeInfo = new Object[8];

                employeeInfo[0] = resultSet.getInt(1);      // ID
                employeeInfo[1] = resultSet.getString(2) + " " + resultSet.getString(3);    // NAME
                employeeInfo[2] = resultSet.getString(4);   // USERNAME
                employeeInfo[3] = resultSet.getString(5);   // PASSWORD
                employeeInfo[4] = resultSet.getString(8);   // ROLE
                employeeInfo[5] = resultSet.getString(9);   // SECRET QUESTION
                employeeInfo[6] = resultSet.getString(10);   // SECRET ANSWER
                employeeInfo[7] = resultSet.getBoolean(6);  // ADMIN
                System.out.println("ADMIN: " + employeeInfo[7]);

                employeeArrayList.add(employeeInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String firstName, String lastName, String role, String username, String password, String secretQuestion, String secretAnswer) throws SQLException {
        System.out.println("ManageEmployeesModel.addEmployee()");

        String sqlINSERT = "INSERT INTO Employee (FirstName, LastName, Role, Username, Password, SecretQuestion, SecretAnswer) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, secretQuestion);
            preparedStatement.setString(7, secretAnswer);
            preparedStatement.executeUpdate();
        }
    }
    // OVERLOAD FOR SETTING ADMIN
    public void addEmployee(String firstName, String lastName, String role, String username, String password, String secretQuestion, String secretAnswer, boolean admin) throws SQLException {
        System.out.println("ManageEmployeesModel.addEmployee(OVERLOAD)");

        String sqlINSERT = "INSERT INTO Employee (FirstName, LastName, Username, Password, Admin, Role, SecretQuestion, SecretAnswer) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setBoolean(5, admin);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, secretQuestion);
            preparedStatement.setString(8, secretAnswer);
            preparedStatement.executeUpdate();
        }
    }
    
    public void editEmployee(int employeeID, String firstName, String lastName, String role, String username, String password, String secretQuestion, String secretAnswer, boolean admin) throws SQLException {
        System.out.println("ManageEmployeesModel.editEmployee()");

        String sqlUPDATE =  "UPDATE Employee " +
                            "SET FirstName = ? " +
                            "SET LastName = ? " +
                            "SET Username = ? " +
                            "SET Password = ? " +
                            "SET Admin = ? " +
                            "SET Role = ? " +
                            "SET SecretQuestion = ? " +
                            "SET SecretAnswer = ? " +
                            "WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUPDATE)) {
            preparedStatement.setInt(9, employeeID);
            
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setBoolean(5, admin);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, secretQuestion);
            preparedStatement.setString(8, secretAnswer);

            preparedStatement.executeUpdate();
        }
    }

    public void removeEmployee(int employeeID) {

        String sqlDELETE =  "DELETE " +
                            "FROM Employee " +
                            "WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDELETE)) {
            preparedStatement.setInt(1, employeeID);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
