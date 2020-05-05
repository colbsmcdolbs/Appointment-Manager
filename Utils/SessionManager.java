package Utils;

import Models.User;

/**
 *
 * @author colby
 */
public class SessionManager {
    
    private static User currentUser = null;
    
    public static void setSessionUser(User user) {
        currentUser = user;
    }
    
    public static User getSessionUser() {
        return currentUser;
    }
    
    public static void destroySession() {
        currentUser = null;
    }
}
