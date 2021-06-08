package main.helper;

import java.time.LocalDate;

/*/
Helper Singleton class to store the lockdown date periods so that they can be access across scenes.
 */

public class LockdownDate {
    
    private static LockdownDate lockdownDate;

    private LocalDate lockdownStartDate;
    private LocalDate lockdownEndDate;
    
    private LockdownDate() {
    }
    
    public static LockdownDate getLockdownDateInstance() {
        if (lockdownDate == null) {
            lockdownDate = new LockdownDate();
        }
        return lockdownDate;
    }

    public LocalDate getLockdownStartDate() {
        return lockdownStartDate;
    }
    public void setLockdownStartDate(LocalDate lockdownStartDate) {
        this.lockdownStartDate = lockdownStartDate;
    }
    public LocalDate getLockdownEndDate() {
        return lockdownEndDate;
    }
    public void setLockdownEndDate(LocalDate lockdownEndDate) {
        this.lockdownEndDate = lockdownEndDate;
    }

}
