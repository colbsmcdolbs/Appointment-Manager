package Models;

import Utils.TimeFunctions;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author colby
 */
public class Appointment {
    
    private int appointmentId;
    private int customerId;
    private int userId;
    private String title;
    private String description, location, contact, type;
    private String url;
    private String start;
    private String end;

    public Appointment(int appointmentId, int customerId, int userId, String location, String contact, String type, String start, String end) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = "Wealth Management Appointment";
        this.description = "An appointment with our wealth management services.";
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = "AppointmentManagerApplication";
        this.start = start;
        this.end = end;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    public String getStartTimeValue() {
        Timestamp timestamp = Timestamp.valueOf(this.start);
        ZonedDateTime zoneDateTime;
        ZoneId zoneId;
        LocalTime localTime;
        if(this.location.equals("New York")) {
            zoneId = ZoneId.of("America/New_York");
            zoneDateTime = timestamp.toLocalDateTime().atZone(zoneId);
            localTime = zoneDateTime.toLocalTime().minusHours(4);
        } 
        else {
            zoneId = ZoneId.of("America/Phoenix");
            zoneDateTime = timestamp.toLocalDateTime().atZone(zoneId);
            localTime = zoneDateTime.toLocalTime().minusHours(7);
        } 
        int hour = Integer.parseInt(localTime.toString().split(":")[0]);
        String time = TimeFunctions.convertTimeToTimeCombo(hour);
        return time;
    }
    
    public LocalDate getDateValue() {
        Timestamp timestamp = Timestamp.valueOf(this.start);
        ZoneId zoneId;
        if(this.location.equals("New York")) {
            zoneId = ZoneId.of("America/New_York");
        } 
        else if(this.location.equals("Boise")) {
            zoneId = ZoneId.of("America/Phoenix");
        } 
        else {
            //NOTE: This wont happen evahh
            zoneId = ZoneId.of("America/Phoenix");
        }
        ZonedDateTime zoneDateTime = timestamp.toLocalDateTime().atZone(zoneId);
        LocalDate localDate = zoneDateTime.toLocalDate();
        return localDate;
    }
}
