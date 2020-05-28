package Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class will help to convert all of the times to UTC and translate when
 * necessary
 * 
 * @author colby
 */
public class TimeFunctions {
    
    /**
     * The method will return the current local time translated into UTC for the DB
     * @return - String utcTime - This can be used for DATETIME mySQL objects
     */
    public static String getCurrentDateTimeUTCForDatabase() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(date);
        return utcTime;
    }
    
    public static String createUtcDateTime(int hourValue, String dateValue, String location) {
        String chosenDate = String.format("%s %02d:%s", dateValue, hourValue, "00");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(chosenDate, dateFormatter);
        ZoneId modZoneId;
        switch(location) {
            case "Boise":
                modZoneId = ZoneId.of("America/Denver");
                break;
            case "New York":
                modZoneId = ZoneId.of("America/New_York");
                break;
            default:
                //NOTE: this will NEEEEVER happen
                modZoneId = ZoneId.of("America/Denver");
                break;
        }
        ZonedDateTime ldtZoned = localDateTime.atZone(modZoneId);
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneOffset.UTC);
        localDateTime = utcZoned.toLocalDateTime();
        Timestamp timeStamp = Timestamp.valueOf(localDateTime);
        return timeStamp.toString();
    }
    
    public static int convertTimeComboToTime(String timeValue) {
        switch(timeValue) {
            case "9:00 AM":
                return 9;
            case "10:00 AM":
                return 10;
            case "11:00 AM":
                return 11;
            case "1:00 PM":
                return 13;
            case "2:00 PM":
                return 14;
            case "3:00 PM":
                return 15;
            case "4:00 PM":
                return 16;
            default:
                return -1;
        }
    }
    
    public static String convertTimeToTimeCombo(int hourValue) {
        switch(hourValue) {
            case 1:
                return "1:00 AM";
            case 2:
                return "2:00 AM";
            case 3:
                return "3:00 AM";
            case 4:
                return "4:00 AM";
            case 5:
                return "5:00 AM";
            case 6:
                return "6:00 AM";
            case 7:
                return "7:00 AM";
            case 8:
                return "8:00 AM";
            case 9:
                return "9:00 AM";
            case 10:
                return "10:00 AM";
            case 11:
                return "11:00 AM";
            case 12:
                return "12:00 PM";
            case 13:
                return "1:00 PM";
            case 14:
                return "2:00 PM";
            case 15:
                return "3:00 PM";
            case 16:
                return "4:00 PM";
            case 17:
                return "5:00 PM";
            case 18:
                return "6:00 PM";
            case 19:
                return "7:00 PM";
            case 20:
                return "8:00 PM";
            case 21:
                return "9:00 PM";
            case 22:
                return "10:00 PM";
            case 23:
                return "11:00 PM";
            case 0:
                return "12:00 AM";
            default:
                return null;
        }
    }
}
