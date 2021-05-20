package main.helper;

import java.time.LocalDate;

public class CurrentDate {

    private static LocalDate currentDate;

    private CurrentDate() {
        currentDate = LocalDate.now();
    }
    
    
    public static LocalDate getCurrentDate() {
        if (currentDate == null) {
            CurrentDate instance = new CurrentDate();
        }
        return currentDate;
    }
}
