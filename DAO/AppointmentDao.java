package DAO;

import Models.Appointment;
import Models.Customer;
import Models.User;
import Utils.DBConnection;
import Utils.TimeFunctions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                String title = appointmentResult.getString("title");
                String description = appointmentResult.getString("description");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String url = appointmentResult.getString("url");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                Appointment appointment = new Appointment(appointmentId, custId, userId, title, description, location, contact, type, url, start, end);
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
                String title = appointmentResult.getString("title");
                String description = appointmentResult.getString("description");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String url = appointmentResult.getString("url");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                Appointment appointment = new Appointment(appointmentId, custId, userId, title, description, location, contact, type, url, start, end);
                return appointment;
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
            String appointmentQuery = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createBy, lastUpdate, lastUpdateBy)"
                                    + " VALUES ("+ appointment.getCustomerId()+", "+ user.getUserId() +", '"+ appointment.getTitle() +"', '"+ appointment.getDescription() +"', '"+ appointment.getLocation() +"', '"+ appointment.getContact() +"', '"+ appointment.getType() +"', '"+ appointment.getUrl() +"', '"+ appointment.getStart() +"', '"+ appointment.getEnd() +"', '"+ currentUtcTime +"', '"+ user.getUserName() +"', '"+ currentUtcTime +"', '"+ user.getUserName() +"');";
            connection.execute(appointmentQuery);
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
}
