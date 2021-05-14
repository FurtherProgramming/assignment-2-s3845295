package main.datastructure;

import java.time.LocalDate;

public class Booking {
    
    private final int _tableID;
    private final int _employeeID;
    private final LocalDate _date;
    
    public Booking(int tableID, int employeeID, LocalDate date) {
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
}

