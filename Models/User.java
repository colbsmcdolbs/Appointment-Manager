package Models;

import java.util.Date;

/**
 *
 * @author colby
 */
public class User {
    
    private int userId;
    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
