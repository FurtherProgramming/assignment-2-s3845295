package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterModel {

    Connection connection;

    public RegisterModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }
    
    public Boolean isDbConnected() {
        try {
            return !connection.isClosed();
        }
        catch (Exception e) {
            return false;
        }
    }

    // UPDATE USER INSTANCE TO NEW USER CREATED!
    public void registerToDb(String name, String surname, int age, String username, String password) throws SQLException {
        System.out.println("registerToDb()");

        PreparedStatement preparedStatement = null;

        String sqlINSERT = "INSERT INTO Employee (name, surname, age, username, password) VALUES (?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sqlINSERT);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4, username);
        preparedStatement.setString(5, password);
        preparedStatement.executeUpdate();
        
        preparedStatement.close();

    }
}
