package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    public static Date convertStringtoDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.parse(date);
    }
    
    public static String convertDatetoString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }
    
    
}
