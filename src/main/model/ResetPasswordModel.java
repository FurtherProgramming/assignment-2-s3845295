package main.model;

import main.SQLConnection;
import main.helper.User;

import java.sql.PreparedStatement;
import java.sql.Connection;

public class ResetPasswordModel {
    
    Connection connection;
    
    public ResetPasswordModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }
    
    public void resetPassword(User user, String password) {
        String sqlUPDATE =  "UPDATE Employee " +
                            "SET Password = ? " +
                            "Where ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUPDATE)) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, user.getUserID());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
