package main.model;

import main.SQLConnection;
import main.helper.User;

import main.object.Table;
import main.object.Table.Status;

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
    public void bookTable(User user, int tableID, LocalDate date) throws SQLException {
        System.out.println("employeeID: " + user.getUserID());
        System.out.println("tableID: " + tableID);
        System.out.println("date: " + date);

        String sqlINSERT = "INSERT INTO Booking (TableID, Date, EmployeeID) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT)) {
            preparedStatement.setInt(1, tableID);
            preparedStatement.setDate(2, Date.valueOf(date));
            System.out.println("Date.valueOf(date): " + Date.valueOf(date));
            preparedStatement.setInt(3, user.getUserID());
            preparedStatement.executeUpdate();
        }
        
        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE TableID = ? AND Date = ? AND EmployeeID = ?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, tableID);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setInt(3, user.getUserID());

            ResultSet resultSet = preparedStatement.executeQuery();
            user.set_lastBookingID(resultSet.getInt(1));
        }

        String sqlUPDATE =  "UPDATE Employee " +
                            "SET LastBookingID = ? " +
                            "WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUPDATE)) {
            preparedStatement.setInt(1, user.get_lastBookingID());
            preparedStatement.setInt(2, user.getUserID());
            preparedStatement.executeUpdate();
        }
    }
    
    public void updateTableStatus(Table table, User user, LocalDate date) {
        table.setStatus(Status.AVAILABLE);

        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE TableID = ? AND Date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt(4) == user.getUserID()) {
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

        try {
            if (wasPreviouslyBooked(table, user, date)) {
                table.setStatus(Status.PREVBOOKED);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    boolean wasPreviouslyBooked(Table table, User user, LocalDate date) throws SQLException {
        boolean previouslyBooked = false;
        
        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE BookingID = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, user.get_lastBookingID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Date resultDate = resultSet.getDate(3);

                int dateCompare = resultDate.compareTo(Date.valueOf(date));

                if ((resultSet.getInt(2) == table.getTableID()) && dateCompare < 0) {
                    previouslyBooked = true;
                }
            }

        }
        return previouslyBooked;
    }
    
    public boolean doesUserHaveBooking(User user, LocalDate date) throws SQLException {

        boolean  userBooking = false;

        if (user.get_lastBookingID() == 0) {
            return false;
        }
        
        // check if user has no existing booking.
        String sqlQUERY = "SELECT * FROM Booking WHERE EmployeeID = ? AND Date >= ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1,user.getUserID());
            preparedStatement.setDate(2, Date.valueOf(date));

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                userBooking = true;
            }


        }
        System.out.println("doesUserHaveBooking(): " + userBooking);
        return userBooking;
    }
    
    public boolean isUserAdmin(User user) {
        return user.isAdmin();
    }
}
