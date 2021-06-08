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
### Dependencies
- Java 1.8
- JavaFX 
- sqlite

### Linux

1. `git clone https://github.com/FurtherProgramming/assignment-2-s3845295`   
2. `cd assignment-2-s3845295`  
3. `java -jar hotdesk.jar`

The default admin account information is:  
Username: ```admin```  
Password: ```test```

### Other operating systems
Any way you're able to run a `.jar` file will suffice. Unfortunately I do not have the expertise to write instructions for other operating systems.

## Screenshots

## Bugs
- Currently, the admin is only able to lockdown tables that are available to be booked, i.e. aren't currently booked by a user. This is not intended functionality, but instead a limitation of how the controller for TableView was designed. 

## Packaging
The main class is Main.java  

__Packaging for classes__:
- main.controller
- main.helper
- main.model
- main.object  
- main.ui

## Prepare other content

Readme files are made for developers (including you), but also could be used for the final users.
So while you are writing your readme files please consider a few things:

1. What is about?
    - Your name and student number and course name on the top
    - Describe the content of your project or repository
    - Explain things the users would have a hard time understanding right away
2. What steps need to be taken?
    - Any specific steps for running your application, what is the main class?
    - Is there any requirements or dependencies?
    - After the installation, how they compile or run the code?
3. Execution examples
    - You could provide examples of execution with code and screenshots
    

other things you could add:

- Table of content
- Test cases
- Know bugs
- Things that have not been working or complete

### References and tutorials for Readme (Markdown)
- **IntelliJ IDEA MarkDown guide**. jetbrains.com/help/idea/markdown.html
- **Choose an open source license**. Github. Available at: https://choosealicense.com/
- **Getting started with writing and formatting on Github**. Github. Available at: https://help.github.com/articles/getting-started-with-writing-and-formatting-on-github/
- **Markdown here cheatsheet**. Markdown Here Wiki. Available at: https://github.com/adam-p/markdown-here/wiki/Markdown-Here-Cheatsheet
- **Markdown quick reference**. Wordpress. Available at: https://en.support.wordpress.com/markdown-quick-reference/
- **readme-template**. Dan Bader. Github. Available at: https://github.com/dbader/readme-template
- Writing READMEs. **Udacity**. Available at: https://classroom.udacity.com/courses/ud777/
