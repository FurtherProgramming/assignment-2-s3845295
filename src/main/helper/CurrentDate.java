package main.helper;

import java.time.LocalDate;

/*/
Helper Singleton class to store the current date easily.
 */

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
