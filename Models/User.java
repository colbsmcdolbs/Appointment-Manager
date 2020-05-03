package Models;

import java.util.Date;

/**
 *
 * @author colby
 */
public class User {
    
    private int userId;
    private String userName;
    private String password; //Probably shouldn't be in plaintext, just following the spec

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
