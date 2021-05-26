package main.model;

import main.SQLConnection;
import main.helper.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public LoginModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean isLogin(String user, String pass) throws SQLException {
        String query = "select * from employee where username = ? and password= ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                System.out.println("SETTING USER");
                setUser(user);

                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
    
    private void setUser(String userString) throws SQLException {

        User user = User.getUser();
        String sqlQUERY = "SELECT * FROM Employee WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {

            preparedStatement.setString(1, userString);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            // WRITE TO USER SINGLETON
            user.setUserID(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
            user.setUsername(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setAdmin(resultSet.getBoolean(7));
            user.set_lastBookingID(resultSet.getInt(8));
            System.out.println("username: " + resultSet.getString(5) + " admin " + resultSet.getBoolean(7));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
