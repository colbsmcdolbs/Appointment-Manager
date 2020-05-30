# Appointment Manager

## Project Overview

The purpose of this project is an MVP of an Appointment Manager that a business would need in order to schedule appointments with customers.

It contains the features to:

1. Verify login credentials stored in a MySQL database
2. Translate the Login Page between English and Spanish based on a user's system locale
3. Add, Modify, and Delete Customers and Appointments into MySQL
4. Translate appointment times into their proper timezone based on appointment location
5. Translate appointment times into current user's system timezone
6. Generate reports for users by calling queries to the database
7. View the appointments upcoming in the current week and month
8. Generate an Alert if an appointment is scheduled within 15 minutes of a user logging in
9. Logging successful and failed login attempts into "logging.txt"
10. Maintaining a Session and auditing all actions done by current user in database


## Requirements

* Java 8 JDK
* Java IDE (Instructions provided for Netbeans 8.2)


## How to Build

This project requires the external libraries of JavaFX and MySQL JDBC, so it cannot (easily) be compiled via the command line. JavaFX is included by default with Java 8, so you should only have to import the JDBC Driver.

### Get Newest Release

Download the latest release from the releases tab.


### Import Project into IDE

Import the project into an IDE of your choice (further instructions will be given for Netbeans 8.2)

In Netbeans. File -> Import Project -> From Zip... -> (Select the .zip file you downloaded) -> Import


### Add the External Library for JDBC

Right Click on Project (Coffee Cup on left side of screen) -> Properties -> Libraries -> Add Library... -> (Find "MYSQL JDBC DRIVER") Select "Add Library"


### Run Project

Click the Green Play button at the top of the IDE to run the project. Use the following test credentials to login.

* Username: test
* Password: test