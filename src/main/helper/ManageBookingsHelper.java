package main.helper;

// SINGLETON
public class ManageBookingsHelper {

    // BOOLEAN VALUE FOR SPECIFIC USER BOOKING SEARCH
    private boolean userSpecific;
    private static ManageBookingsHelper instance;
    
    private ManageBookingsHelper() {
        
    }
    
    public static ManageBookingsHelper getInstance() {
        if (instance == null) {
            instance = new ManageBookingsHelper();
        }
        return instance;
    }

    public boolean isUserSpecific() {
        return userSpecific;
    }

    public void setUserSpecific(boolean userSpecific) {
        this.userSpecific = userSpecific;
    }


}
