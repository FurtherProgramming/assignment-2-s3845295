package main.model;

import main.SQLConnection;
import main.object.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    User user = User.getUser();
    Connection connection;

    public LoginModel(){

        connection = SQLConnection.connect();
        if (connection == null) {
            System.exit(1);
        }

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean isLogin(String username, String password) throws SQLException {
        String query = "select * from employee where username = ? and password= ?";

        boolean login = false;
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login =  true;
            }
        }
        catch (Exception e) {
            return false;
        }

        if (login) {
            user.setUser(username);
        }

        return login;
    }

}
