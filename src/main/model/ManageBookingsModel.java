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
    
    public ArrayList<Object[]> populateBookings(ArrayList<Object[]> bookingArrayList) throws SQLException {
        System.out.println("ManageBookingsModel.populateBookings()");
        
        Object[] bookingInfo = new Object[6];

        String sqlQUERYBooking = "SELECT * FROM Booking WHERE Date >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERYBooking)) {

            preparedStatement.setDate(1, Date.valueOf(CurrentDate.getCurrentDate()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // BOOKING ID
                bookingInfo[0] = resultSet.getInt(1);
                // DATE
                bookingInfo[1] = resultSet.getDate(3);
                // TABLE ID
                bookingInfo[2] = resultSet.getInt(2);
                // CONFIRMED
                bookingInfo[5] = resultSet.getBoolean(5);

                String sqlQUERYEmployee = "SELECT * FROM Employee WHERE id = ?";

                // TODO
                // IMPLEMENT SQL JOIN STATEMENT TO GET USERDATA

                bookingArrayList.add(bookingInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return bookingArrayList;
    }
}
