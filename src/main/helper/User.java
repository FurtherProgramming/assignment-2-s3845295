package main.helper;

// SINGLETON USER
public class User {

    private static User user;


    private int userID;
    private String _name;
    private String _surname;
    private int _age;
    private String _username;
    private String _password;
    private boolean _admin;

    private int _lastBookingID;

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
    public void setUserID(int _userID) {
        this.userID = _userID;
    }
    public String getName() {
        return _name;
    }
    public void setName(String _name) {
        this._name = _name;
    }
    public String getSurname() {
        return _surname;
    }
    public void setSurname(String _surname) {
        this._surname = _surname;
    }
    public int getAge() {
        return _age;
    }
    public void setAge(int _age) {
        this._age = _age;
    }
    public String getUsername() {
        return _username;
    }
    public void setUsername(String _username) {
        this._username = _username;
    }
    public String getPassword() {
        return _password;
    }
    public void setPassword(String _password) {
        this._password = _password;
    }
    public boolean isAdmin(){
        return _admin;
    }
    public void setAdmin(boolean _admin) {
        this._admin = _admin;
    }
    public int get_lastBookingID() {
        return _lastBookingID;
    }
    public void set_lastBookingID(int _lastBookingID) {
        this._lastBookingID = _lastBookingID;
    }

}
