package main.model;

import main.SQLConnection;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;
import java.time.LocalDate;

public class ReportsModel {
    
    Connection connection;
    FileWriter fileWriter;
    File directory = new File("./Reports/");
    
    public ReportsModel() {
        connection = SQLConnection.connect();
        if (connection == null) {
            System.exit(1);
        }
    }
    
    private long getNumberOfReports(String reportType) {
        try {
            // used a stream AND a lambda expression :)
            return  Stream.of(directory.listFiles())
                    .filter(f -> f.getName().contains(reportType))
                    .count();
        }
        catch (Exception e) {
            return 0;
        }
    }

    // WRITES REPORT & RETURNS FILENAME
    public String generateBookingsCSV() throws IOException {
        String fileName = "./Reports/" + "bookings" + getNumberOfReports("bookings") + ".csv";
        fileWriter = new FileWriter(fileName);
        fileWriter.append("Booking ID,Table ID,Date,Employee ID,Confirmed Status\n");
        
        String sqlQUERY = "SELECT * FROM Booking";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fileWriter.append(String.valueOf(resultSet.getInt(1)));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(resultSet.getInt(2)));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(resultSet.getString(3)));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(resultSet.getInt(4)));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(resultSet.getInt(5)));
                fileWriter.append("\n");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        fileWriter.close();
        return fileName;
    }


    // WRITES REPORT & RETURNS FILENAME
    public String generateEmployeesCSV() throws IOException {
        String fileName = "./Reports/" + "employees" + getNumberOfReports("employees") + ".csv";
        fileWriter = new FileWriter(fileName);
        fileWriter.append("Employee ID,First Name,Last Name,Username,Password,Admin Status,Last Booking ID,Role,Secret Question,Secret Answer\n");

        String sqlQUERY = "SELECT * FROM Employee";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (int i = 1; i <= 10; i++) {
                    fileWriter.append(resultSet.getString(i));
                    if (i < 10) {fileWriter.append(",");}
                }
                fileWriter.append("\n");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        fileWriter.close();
        return fileName;
    }
}
