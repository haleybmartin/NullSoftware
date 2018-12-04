package main;

import data.DataManager;
import java.util.UUID;
import systemobjects.User;

/**
 * Session
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class Session {
    
    //MARK: FIELDS
    
    private User activeUser;
    
    //MARK: CONSTRUCTORS
    
    public Session() {
        User admin = new User("admin", "Haley Martin", User.UserAccountType.SECRETARY, UUID.randomUUID());
        DataManager.addUser(admin, "admin");
        User meterboy = new User("meter", "Skylar Smith", User.UserAccountType.METER_READER, UUID.randomUUID());
        DataManager.addUser(meterboy, "meter");
        User president = new User("pres", "Rachelle LaChance", User.UserAccountType.PRESIDENT, UUID.randomUUID());
        DataManager.addUser(president, "pres");
        User temp = new User("temp", "Brendan Michael", User.UserAccountType.TEMP, UUID.randomUUID());
        DataManager.addUser(temp, "temp");
    }
    
    public Session(User activeUser) {
        this.activeUser = activeUser;
    }
    
    
}
