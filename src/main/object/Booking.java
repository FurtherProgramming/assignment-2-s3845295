package main.object;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Booking {
    
    private final int _tableID;
    private final int _employeeID;
    private final LocalDate _date;
    
    public Booking(int employeeID, int tableID,LocalDate date) {
        this._tableID = tableID;
        this._employeeID = employeeID;
        this._date = date;
    }
    
    public int getTableID() {
        return this._tableID;
    }

    public int getemployeeID() {
        return this._employeeID;
    }
    
    public LocalDate getDate() {
        return this._date;
    }
    
    public PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setInt(1, this._tableID);
        preparedStatement.setDate(2, Date.valueOf(this._date));
        preparedStatement.setInt(3, this._employeeID);

        return preparedStatement;
    }
}

