# Hotdesk Application

### Author
COSC2391 Further Programming - Semester 1 2021  
Roman O'Brien - s3845295

## Scope
This is a rudimentary application built in JavaFX which provides [hot desking](https://en.wikipedia.org/wiki/Hot_desking) functionality. It writes to a local database to ensure information is kept between sessions. The functions available to a regular user are:
1. Login / Register / Reset Password
2. Book table for a specific date
3. Edit / Cancel booking
4. Check in for booking (on the day)

Admin accounts have privileged access to functions such as:
5. Approve / Reject booking requests
4. Add / Edit / Delete users
5. Setting a lockdown period for certain tables (to prevent booking during a time frame)

## Running Program
![Hotdesk Homepage](https://github.com/FurtherProgramming/assignment-2-s3845295/blob/main/Images/homepage.png)
### Dependencies
- Java 1.8
- JavaFX 
- sqlite

### Linux

1. `git clone https://github.com/FurtherProgramming/assignment-2-s3845295`   
2. `cd assignment-2-s3845295`  
3. `java -jar hotdesk.jar`

The default admin account for login is:  
Username: `admin`  
Password: `test`

### Other operating systems
Any way you're able to run a `.jar` file will suffice.  
Unfortunately I do not have the expertise to write instructions for other operating systems.

## Example Program Functionality
### Lockdown
To set a lockdown restriction on tables, first select the `Manage Lockdown` menu item under the `Admin` menu.  
![Lockdown Menu Item](https://github.com/FurtherProgramming/assignment-2-s3845295/blob/main/Images/lockdownMenuItem.png)

Select the start and end dates for the lockdown period, then click the `OK` button.  
![Lockdown Date Selection](https://github.com/FurtherProgramming/assignment-2-s3845295/blob/main/Images/lockdownDateSelection.png)  

Once a lockdown period has been set, you're free to click on a table, then click the `Lockdown` button to disable bookings for the lockdown period.  
![Lockdown Table](https://github.com/FurtherProgramming/assignment-2-s3845295/blob/main/Images/lockdownTable.png)

## Bugs
- Currently, the admin is only able to lockdown tables that are available to be booked, i.e. aren't currently booked by a user. This is not intended functionality, but instead a limitation of how the controller for TableView was designed. 

## Limitations
Lockdown periods are not able to be edited or deleted inside the software.

## Packaging
This project was built using the MVC software design pattern. The other classes in the structure are helpers which assist in transferring information between JavaFXML scenes, or object classes.  
The main class is `Main.java`  

__Packaging for classes__:
- `main.controller`
- `main.helper`
- `main.model`
- `main.object`  
- `main.ui`
