package main.object;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*/
Singleton User object so that all classes have access to the user currently logged in to the system.
Includes setUser() to be called when a user logs in to the system.
 */
public class User {

    private static User user;

    private int userID;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private boolean admin;
    private int lastBookingID;

    private User() {
    }

    public static User getUser(){
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSecretQuestion() {
        return secretQuestion;
    }
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }
    public String getSecretAnswer() {
        return secretAnswer;
    }
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public boolean isAdmin(){
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public int getLastBookingID() {
        return lastBookingID;
    }
    public void setLastBookingID(int lastBookingID) {
        this.lastBookingID = lastBookingID;
    }

    public void setUser(String username) throws SQLException {
        
        Connection connection;
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

        String sqlQUERY = "SELECT * FROM Employee WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            // WRITE USER INFO FROM DATABASE
            setUserID(resultSet.getInt(1));
            setFirstName(resultSet.getString(2));
            setLastName(resultSet.getString(3));
            setRole(resultSet.getString(8));
            setUsername(resultSet.getString(4));
            setPassword(resultSet.getString(5));
            setAdmin(resultSet.getBoolean(6));
            setLastBookingID(resultSet.getInt(7));
            setSecretQuestion(resultSet.getString(9));
            setSecretAnswer(resultSet.getString(10));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
