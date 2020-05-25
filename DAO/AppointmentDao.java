package DAO;

import Models.Appointment;
import Models.User;
import Utils.DBConnection;
import Utils.TimeFunctions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author colby
 */
public class AppointmentDao {
    
    /**
     * Returns an ObservableList of appointments,
     * @return 
     */
    public static ObservableList<Appointment> getAllAppointments() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String appointmentQuery = "SELECT * FROM appointment;";
            ResultSet appointmentResult = connection.executeQuery(appointmentQuery);
            
            ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
            
            while(appointmentResult.next()) {
                int appointmentId = appointmentResult.getInt("appointmentId");
                int custId = appointmentResult.getInt("customerId");
                int userId = appointmentResult.getInt("userId");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                Appointment appointment = new Appointment(appointmentId, custId, userId, location, contact, type, start, end);
                appointmentList.add(appointment);
            }
            
            return appointmentList;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    /**
     * Returns either the weekly or monthly appointments.
     * @param isMonth - true -> month, false -> weeks
     * @return list of all appointments
     */
    public static ObservableList<Appointment> getWeeklyMonthlyAppointments(boolean isMonth) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            LocalDate begin = LocalDate.now();
            LocalDate endTime = isMonth ? LocalDate.now().plusMonths(1) : LocalDate.now().plusWeeks(1);
            
            String appointmentQuery = "SELECT * FROM appointment WHERE start >= '"+ begin +"' AND end <= '"+ endTime +"' ORDER BY start ASC;";
            ResultSet appointmentResult = connection.executeQuery(appointmentQuery);
            
            ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
            
            while(appointmentResult.next()) {
                int appointmentId = appointmentResult.getInt("appointmentId");
                int custId = appointmentResult.getInt("customerId");
                int userId = appointmentResult.getInt("userId");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                Appointment appointment = new Appointment(appointmentId, custId, userId, location, contact, type, start, end);
                appointmentList.add(appointment);
            }
            
            return appointmentList;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    /**
     * 
     * @param appointmentId - the specified appointment to find
     * @return an Appointment object if one is found, else null
     */
    public static Appointment getAppointmentById(int appointmentId) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String appointmentQuery = "SELECT * FROM appointment WHERE appointmentId=" + appointmentId + ";";
            ResultSet appointmentResult = connection.executeQuery(appointmentQuery);
            
            if(appointmentResult.next()) {
                int custId = appointmentResult.getInt("customerId");
                int userId = appointmentResult.getInt("userId");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                Appointment appointment = new Appointment(appointmentId, custId, userId, location, contact, type, start, end);
                return appointment;
            }
            
            return null;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static String checkAppointmentIn15Minutes() {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = currentTime.atZone(zoneId);
            LocalDateTime utcStart = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            LocalDateTime utcEnd = utcStart.plusMinutes(15);
            
            Statement connection = DBConnection.getConnection().createStatement();
            String appointmentQuery = "SELECT * FROM appointment a INNER JOIN customer c USING(customerId) WHERE start BETWEEN '" + 
                                        utcStart+"' AND '"+ utcEnd +"';";
            ResultSet appointmentResult = connection.executeQuery(appointmentQuery);
            if(appointmentResult.next()) {
                String result = String.format("%s has an appointment with %s regarding %s within the next 15 minutes", 
                                                appointmentResult.getString("contact"), appointmentResult.getString("customerName"), appointmentResult.getString("type"));
                return result;
            }
            
            return null;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static boolean deleteAppointmentById(int appointmentId) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String appointmentQuery = "DELETE FROM appointment WHERE appointmentId="+ appointmentId +";";
            connection.execute(appointmentQuery);
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean updateAppointment(Appointment appointment, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentUtcTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String appointmentQuery = "UPDATE appointment SET customerId="+ appointment.getCustomerId() +
                                        ", userId="+ appointment.getUserId() +", title='"+ appointment.getTitle() +
                                        "', description='"+ appointment.getDescription() +"', location='"+ appointment.getLocation() +
                                        "', contact='"+ appointment.getContact() +"', type='"+ appointment.getType() +"', url='"+ appointment.getUrl() +
                                        "', start='"+ appointment.getStart()+"', end='"+ appointment.getEnd()+"', lastUpdate='"+ currentUtcTime +
                                        "', lastUpdateBy='"+ user.getUserName() +"' WHERE appointmentId="+ appointment.getAppointmentId() +";";
            connection.execute(appointmentQuery);
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean insertAppointment(Appointment appointment, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentUtcTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String appointmentQuery = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)"
                                    + " VALUES ("+ appointment.getCustomerId()+", "+ user.getUserId() +", '"+ appointment.getTitle() +"', '"+ appointment.getDescription() +"', '"+ appointment.getLocation() +"', '"+ appointment.getContact() +"', '"+ appointment.getType() +"', '"+ appointment.getUrl() +"', '"+ appointment.getStart() +"', '"+ appointment.getEnd() +"', '"+ currentUtcTime +"', '"+ user.getUserName() +"', '"+ currentUtcTime +"', '"+ user.getUserName() +"');";
            connection.execute(appointmentQuery);
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean verifyAppointmentExists(Appointment appointment) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM appointment WHERE customerId="+ appointment.getCustomerId() +" AND userId="+ appointment.getUserId() +" AND location='"+ appointment.getLocation()+
                                    "' AND contact='"+ appointment.getContact() +"' AND type='"+ appointment.getType()+"' AND start='"+ appointment.getStart() +"' AND end='"+ appointment.getEnd()+"';";
            ResultSet customerResult = connection.executeQuery(customerQuery);
            
            return customerResult.next();
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean verifyDuplicateAppointmentTimeExists(Appointment appointment) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM appointment WHERE start='"+ appointment.getStart() +"' AND location='"+ appointment.getLocation() +"';";
            ResultSet customerResult = connection.executeQuery(customerQuery);
            
            return customerResult.next();
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
}
