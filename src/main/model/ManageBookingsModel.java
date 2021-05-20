package main.model;

import main.SQLConnection;
import main.helper.CurrentDate;
import java.sql.*;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageBookingsModel {

    Connection connection;
    
    public ManageBookingsModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }
    
    public void populateBookings(ArrayList<Object[]> bookingArrayList) throws SQLException {
        System.out.println("ManageBookingsModel.populateBookings()");
        


        String sqlQUERYBooking =    "SELECT Booking.BookingID, Booking.TableID, Booking.Date, Booking.Confirmed, Employee.name, Employee.surname, Employee.username " +
                                    "FROM Booking " +
                                    "INNER JOIN Employee ON Booking.EmployeeID  = Employee.id " +
                                    "WHERE Date >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERYBooking)) {

            preparedStatement.setDate(1, Date.valueOf(CurrentDate.getCurrentDate()));
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
    
    public void acceptBooking(int bookingID) throws SQLException {

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
