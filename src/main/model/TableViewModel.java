package main.model;

import main.SQLConnection;
import main.datastructure.Booking;
import org.sqlite.SQLiteConnection;

import main.datastructure.Table;
import main.datastructure.Table.Status;

import java.sql.*;

import java.time.LocalDate;

public class TableViewModel {
    
    Connection connection;
    
    public TableViewModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    // BUG: BookingID IS NULL
    public void bookTable(int employeeID, int tableID, LocalDate date) throws SQLException {
        System.out.println("employeeID: " + employeeID);
        System.out.println("tableID: " + tableID);
        System.out.println("date: " + date);

        String sqlINSERT = "INSERT INTO Booking (TableID, Date, EmployeeID) VALUES (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT);

        preparedStatement.setInt(1, tableID);
        preparedStatement.setDate(2, Date.valueOf(date));
        System.out.println("Date.valueOf(date): " + Date.valueOf(date));
        preparedStatement.setInt(3, employeeID);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
    
    public void updateTableStatus(Table table, int userID, LocalDate date) {
        // update table status enum according to database

        boolean tableBooked, tableLocked, tableUserBooked;
        
        table.setStatus(Status.AVAILABLE);

        // check if table is booked
        String sqlQUERY = "SELECT * FROM Booking WHERE TableID = ? AND Date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {

            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            // CHECK FOR TABLE BOOKING ON DATE
            if (resultSet.next()) {
                // CHECK IF THE USER HAS BOOKED IT
                if (resultSet.getInt(4) == userID) {
                    table.setStatus(Status.USERBOOKED);
//                    System.out.println("TableModel.updateTableStatus(): " + table.getLabel() + "USERBOOKED");
                }
                else {
                    table.setStatus(Status.BOOKED);
//                    System.out.println("TableModel.updateTableStatus(): " + table.getLabel() + "BOOKED");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    boolean isBookedPreviously(int employeeID, int tableID) {
        boolean bookedPreviously = false;
        // connect to booking table  and check if last booking by employee was this table: return true.
        return bookedPreviously;
    }
}
