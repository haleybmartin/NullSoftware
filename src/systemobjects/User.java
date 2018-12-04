package systemobjects;

import java.io.Serializable;
import java.util.UUID;

/**
 * User
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class User implements java.io.Serializable {
    
    public enum UserAccountType { METER_READER, SECRETARY, PRESIDENT, TEMP }
    
    //MARK: FIELDS
    
    private String username;
    private String name;
    private UserAccountType userType;
    private UUID userID;
    
    //MARK: CONSTRUCTOR
    
    public User(String username, String name, UserAccountType userType, UUID userID) {
        this.username = username;
        this.name = name;
        this.userType = userType;
        this.userID = userID;
    }
    
    //MARK: GETTERS/SETTERS
    
    public String getName() { return name; }
    
    public UserAccountType getUserType() { return userType; }
    
    public UUID getUserID() { return userID; }
    
    public String getUsername() { return username; }
    
    //MARK: GENERAL PERMISSIONS
    
    public void getAccountsOverview() {
        
    }
    
    public void getStatisticsOverview() {
        
    }
    
    //MARK: TEMP WORKER PERMISSIONS
    
    //MARK: PRESIDENT PERMISSIONS
    
    //MARK: METER READER PERMISSIONS
    
    //MARK: SECRETARY PERMISSIONS
}
