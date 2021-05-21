package main.model;

import main.SQLConnection;
import main.helper.User;

import main.datastructure.Table;
import main.datastructure.Table.Status;

import java.sql.*;

import java.time.LocalDate;
import java.time.Period;

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
        table.setStatus(Status.AVAILABLE);

        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE TableID = ? AND Date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt(4) == userID) {
                    // IF USER HAS BOOKED
                    if (!resultSet.getBoolean(5)) {
                        // CHECK CONFIRMATION STATUS
                        table.setStatus(Status.PENDING);
                    }
                    else {
                        // CONFIRMED BOOKING
                        table.setStatus(Status.USERBOOKED);
                    }
                }
                else {
                    // TABLE BOOKED BY SOMEONE ELSE
                    table.setStatus(Status.BOOKED);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (wasPreviouslyBooked(table, date)) {
            table.setStatus(Status.PREVBOOKED);
        }

    }

    boolean wasPreviouslyBooked(Table table, LocalDate date) {
        boolean bookedPreviously = false;

        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE TableID = ? AND Date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, table.getTableID());
            // CHECK YESTERDAY'S DATE
            preparedStatement.setDate(2, Date.valueOf((date.minus(Period.ofDays(1)))));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bookedPreviously = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return bookedPreviously;
    }
    
    public boolean canUserBook(User user) {
        // check if user has no existing booking.
        return false;
    }
    
    public boolean isUserAdmin(User user) {
        return user.isAdmin();
    }
}
