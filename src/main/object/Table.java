package main.object;

import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class Table {

    private int tableID;
    private Status status;
    private Rectangle rectangle;
    private Label label;
    
    public enum Status {
        AVAILABLE,
        BOOKED,
        LOCKED,
        PREVBOOKED,
        USERBOOKED,
        PENDING
    }

    public Table(int tableID, Label label, Rectangle rectangle) {
        
//        System.out.println("CONSTRUCTOR: " + tableID + " " + label + " " + rectangle);
        
        this.status = Status.AVAILABLE;
        this.tableID = tableID;
        this.label = label;
        this.rectangle = rectangle;
    }
    public int getTableID() {
        return tableID;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public Label getLabel() {
        return this.label;
    }
    
    
    
}
