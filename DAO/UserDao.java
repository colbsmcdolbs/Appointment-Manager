package DAO;

import Models.User;
import Utils.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author colby
 */
public class UserDao {
    
    public static User findUser(String username, String password) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String userQuery = "SELECT * FROM user WHERE userName='" + username + "' AND password='" + password + "';" ;
            ResultSet userResult = connection.executeQuery(userQuery);
            
            if(userResult.next()) {
                try{
                    User currentUser = new User(userResult.getInt("userId"), userResult.getString("userName"));
                    connection.close();
                    return currentUser;
                }
                catch(Exception e) {
                    System.err.println(e.getLocalizedMessage());
                    return null;
                }
            }
            else {
                return null;
            }
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
}
