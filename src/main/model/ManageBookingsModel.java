package main.model;

import main.SQLConnection;
import main.helper.CurrentDate;
import main.object.User;

import javax.xml.transform.Result;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManageBookingsModel {

    Connection connection;
    
    public ManageBookingsModel() {
        connection = SQLConnection.connect();
        if (connection == null) {
            System.exit(1);
        }
    }
    
    public void populateBookings(ArrayList<Object[]> bookingArrayList, LocalDate currentDate) throws SQLException {
        System.out.println("ManageBookingsModel.populateBookings()");

        String sqlQUERY =    "SELECT Booking.BookingID, Booking.TableID, Booking.Date, Booking.Confirmed, Employee.FirstName, Employee.LastName, Employee.username, Employee.ID " +
                                    "FROM Booking " +
                                    "INNER JOIN Employee ON Booking.EmployeeID  = Employee.id " +
                                    "WHERE Date >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {

            preparedStatement.setDate(1, Date.valueOf(currentDate));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] bookingInfo = new Object[6];

                bookingInfo[0] = resultSet.getInt(1);                                               // BOOKING ID
                bookingInfo[1] = resultSet.getDate(3);                                              // DATE
                bookingInfo[2] = resultSet.getInt(2);                                               // TABLE ID
                bookingInfo[3] = resultSet.getString(5) + " " + resultSet.getString(6); // NAME
                bookingInfo[4] = resultSet.getString(7);                                            // USERNAME
                bookingInfo[5] = resultSet.getBoolean(4);                                           // CONFIRMED

                bookingArrayList.add(bookingInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // OVERLOAD -- ONLY GET BOOKINGS FROM A SPECIFIC USER
    public void populateBookings(ArrayList<Object[]> bookingArrayList, User user, LocalDate currentDate) throws SQLException {
        System.out.println("ManageBookingsModel.populateBookings(user)");

        String sqlQUERY =    "SELECT Booking.BookingID, Booking.TableID, Booking.Date, Booking.Confirmed, Employee.FirstName, Employee.LastName, Employee.username " +
                                    "FROM Booking " +
                                    "INNER JOIN Employee ON Booking.EmployeeID  = Employee.ID " +
                                    "WHERE Date >= ? AND Employee.ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setDate(1, Date.valueOf(currentDate));
            preparedStatement.setInt(2, user.getUserID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] bookingInfo = new Object[6];

                bookingInfo[0] = resultSet.getInt(1);                                               // BOOKING ID
                bookingInfo[1] = resultSet.getDate(3);                                              // DATE
                bookingInfo[2] = resultSet.getInt(2);                                               // TABLE ID
                bookingInfo[3] = resultSet.getString(5) + " " + resultSet.getString(6); // NAME
                bookingInfo[4] = resultSet.getString(7);                                            // USERNAME
                bookingInfo[5] = resultSet.getBoolean(4);                                           // CONFIRMED

                bookingArrayList.add(bookingInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    // IS BOOKING AT LEAST 48 HOURS AWAY
    public Boolean canUserEditBooking(int bookingID, LocalDate currentDate) {
        
        String sqlQUERY =   "SELECT * " +
                            "FROM Booking " +
                            "WHERE BookingID = ? " +
                            "AND Date >= ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            preparedStatement.setInt(1, bookingID);
            preparedStatement.setDate(2, Date.valueOf(currentDate.plusDays(2)));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public void acceptBooking(int bookingID) {

        String sqlUPDATE =  "UPDATE Booking " +
                            "SET Confirmed = true " +
                            "Where BookingID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUPDATE)) {
            preparedStatement.setInt(1, bookingID);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectBooking(int bookingID) {
        
        String sqlDELETE =  "DELETE " +
                            "FROM Booking " +
                            "WHERE BookingID = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDELETE)) {
            preparedStatement.setInt(1, bookingID);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
