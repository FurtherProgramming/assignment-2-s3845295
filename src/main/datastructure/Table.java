package main.datastructure;

public class Table {
    
    private int _tableID;
    private Status _status;
    
    enum Status {
        OPEN,
        BOOKED,
        LOCKED,
        PREVBOOKED
    }

    public Table(int tableID) {
        // connect to database and establish table status
    }
    
    void refresh() {
        // refresh database and establish stable status
    }
    
    Status getStatus() {
        return this._status;
    }
    
    void setStatus(Status status) {
        this._status = status;
    }
    
    boolean bookedPreviously(int employeeID) {
        boolean bookedPreviously = false;
        // connect to booking table  and check if last booking by employee was this table: return true.
        return bookedPreviously;
    }
    
    
}
