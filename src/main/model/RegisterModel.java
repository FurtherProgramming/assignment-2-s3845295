package main.model;

import main.SQLConnection;
import main.object.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterModel {

    User user = User.getUser();
    Connection connection;

    public RegisterModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    // UPDATE USER INSTANCE TO NEW USER CREATED!
    public void registerToDb(String firstName, String lastName, String role, String username, String password, String secretQuestion, String secretAnswer) throws SQLException {
        System.out.println("registerToDb()");

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
        
        user.setUser(username);
    }
}
