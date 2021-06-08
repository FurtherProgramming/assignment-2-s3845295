package main.model;

import main.SQLConnection;
import main.object.User;

import main.object.Table;
import main.object.Table.Status;

import java.util.ArrayList;

import java.sql.*;

import java.time.LocalDate;

public class TableViewModel {
    
    Connection connection;
    
    public TableViewModel() {
        connection = SQLConnection.connect();
        if (connection == null) {
            System.exit(1);
        }
    }

    // BUG: BookingID IS NULL
    public void bookTable(User user, int tableID, LocalDate date) throws SQLException {
        String sqlINSERT = "INSERT INTO Booking (TableID, Date, EmployeeID) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT)) {
            preparedStatement.setInt(1, tableID);
            preparedStatement.setDate(2, Date.valueOf(date));
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
            user.setLastBookingID(resultSet.getInt(1));
        }

        String sqlUPDATE =  "UPDATE Employee " +
                            "SET LastBookingID = ? " +
                            "WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUPDATE)) {
            preparedStatement.setInt(1, user.getLastBookingID());
            preparedStatement.setInt(2, user.getUserID());
            preparedStatement.executeUpdate();
        }
    }
    
    public void rejectUnconfirmedBookings(LocalDate currentDate) {
        ArrayList<Integer> bookingIDs = new ArrayList<Integer>();

        // ADD ANY UNCONFIRMED BOOKINGS FOR CURRENT DAY TO bookingIDs
        String sqlQUERY = "SELECT * FROM Booking WHERE Date = ? AND Confirmed = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setDate(1, Date.valueOf(currentDate));
            preparedStatement.setInt(2, 0);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                bookingIDs.add(resultSet.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        // DELETE ANY BOOKINGS IN bookingIDs
        if (bookingIDs.size() > 0) {
            String sqlDELETE =  "DELETE " +
                    "FROM Booking " +
                    "WHERE BookingID = ?";

            for (int bookingID : bookingIDs) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDELETE)) {
                    preparedStatement.setInt(1, bookingID);
                    preparedStatement.executeUpdate();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

        // OTHER TABLE STATUSES REQUIRE DIFFERENT TABLES/QUERIES
        try {
            // CHECK IF TABLE IS LOCKED
            if (isTableLocked(table, date)) {
                table.setStatus(Status.LOCKED);
            }
            // CHECK IF TABLE WAS PREVIOUSLY BOOKED BY USER
            else if (wasPreviouslyBooked(table, user, date)) {
                table.setStatus(Status.PREVBOOKED);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean wasPreviouslyBooked(Table table, User user, LocalDate date) throws SQLException {
        boolean previouslyBooked = false;
        
        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE BookingID = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, user.getLastBookingID());
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
    
    private boolean isTableLocked(Table table, LocalDate date) throws SQLException {
        boolean locked = false;

        String sqlQUERY =   "SELECT * " +
                            "FROM Lockdown " +
                            "WHERE TableID = ? " +
                            "AND StartDate <= ? " +
                            "AND EndDate >= ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setDate(3, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                locked = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return locked;
    }
    
    public boolean doesUserHaveBooking(User user, LocalDate date) throws SQLException {
        boolean  userBooking = false;

        if (user.getLastBookingID() == 0) {
            return false;
        }
        
        // check if user has no existing booking.
        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE EmployeeID = ? " +
                            "AND Date >= ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1,user.getUserID());
            preparedStatement.setDate(2, Date.valueOf(date));

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                userBooking = true;
            }


        }
        return userBooking;
    }
    
    public boolean canUserCheckIn(User user, LocalDate currentDate) {
        String sqlQUERY = "SELECT * FROM Booking WHERE BookingID = ? AND Date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, user.getLastBookingID());
            preparedStatement.setDate(2, Date.valueOf(currentDate));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getBoolean(5)) {
                    return true;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public void lockdownTable(Table table, LocalDate startDate, LocalDate endDate) throws SQLException {
        String sqlINSERT = "INSERT INTO Lockdown (TableID, StartDate, EndDate) VALUES (?,?,?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT)) {
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
