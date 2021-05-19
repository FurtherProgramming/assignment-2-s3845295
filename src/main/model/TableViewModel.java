package main.model;

import main.SQLConnection;
import main.datastructure.Booking;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

public class TableViewModel {
    
    Connection connection;
    
    public TableViewModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }
    
    public void bookTable(int employeeID, int tableID, LocalDate date) throws SQLException {
        System.out.println("employeeID: " + employeeID);
        System.out.println("tableID: " + tableID);
        System.out.println("date: " + date);
        
//        PreparedStatement preparedStatement = null;
//        String sqlINSERT = "INSERT INTO Booking (tableID, date, employeeID) VALUES (?,?,?)";
//
//        preparedStatement = connection.prepareStatement(sqlINSERT);
//
//        Booking booking = new Booking(employeeID, tableID, date);
//        preparedStatement = booking.getPreparedStatement(preparedStatement);
//
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
    }
}
