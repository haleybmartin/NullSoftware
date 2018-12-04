package data;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import systemobjects.*;
import utilities.AuthenticationHandler;
import utilities.FileHandler;

/**
 * DataManager
 * @author Haley Martin
 * @version 1.0
 */
public class DataManager {
    
    //MARK: BILLS
    
    private static final String FILENAME_B = "billmap.ser";
    public static Bill getBillByID(UUID billID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //MARK: CUSTOMERS
    
    private static final String FILENAME_C = "custmap.ser";
    public static Customer getCustomerByID(UUID customerID) {
        return null;
    }
    
    //MARK: PAYMENTS
    
    private static final String FILENAME_P = "paymap.ser";
    public static Payment getPaymentByID(UUID paymentID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //MARK: USERS
    
    private static final String FILENAME_U = "usermap.ser";
    
    public static void addUser(User user, String password) {
        HashMap<String, User> userMap = loadUserMap();
        userMap.put(user.getUserID().toString(), user);
        saveUserMap(userMap);
        AuthenticationHandler.addPassword(user.getUsername(), password);
    }
    public static User getUserByID(UUID userID) {
        return null;
    }
    public static User getUserByUsername(String username) {
        HashMap<String, User> userMap = loadUserMap();
        Iterator iter = userMap.entrySet().iterator();
        User user = null;
        while(iter.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)iter.next();
            User tempUser = (User)pair.getValue();
            if(tempUser.getUsername().equals(username)) {
                user = tempUser;
                break;
            }
        }
        return user;
    }
    
    //MARK: METERS
    
    private static final String FILENAME_M = "metermap.ser";
    
    public static void addMeter(Meter meter) {
        HashMap<String, Meter> meterMap = loadMeterMap();
        meterMap.put(meter.getMeterID().toString(), meter);
        saveMeterMap(meterMap);
    }
    
    //MARK: SAVE/LOAD
    
    private static void saveBillMap(HashMap<String, Bill> billMap) {
        FileHandler.saveSerializable(billMap, FILENAME_B);
    }
    private static void saveCustomerMap(HashMap<String, Customer> customerMap) {
        FileHandler.saveSerializable(customerMap, FILENAME_C);
    }
    private static void savePaymentMap(HashMap<String, Payment> paymentMap) {
        FileHandler.saveSerializable(paymentMap, FILENAME_P);
    }
    private static void saveUserMap(HashMap<String, User> userMap) {
        FileHandler.saveSerializable(userMap, FILENAME_U);
    }
    private static void saveMeterMap(HashMap<String, Meter> meterMap) {
        FileHandler.saveSerializable(meterMap, FILENAME_M);
    }
    
    public static HashMap<String, Bill> loadBillMap() {
        HashMap<String, Bill> billMap = new HashMap<>();
        try {
            billMap = (HashMap)FileHandler.loadSerializable(FILENAME_B);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            saveBillMap(billMap);
        }
        return billMap;
    }
    public static HashMap<String, Customer> loadCustomerMap() {
        HashMap<String, Customer> customerMap = new HashMap<>();
        try {
            customerMap = (HashMap)FileHandler.loadSerializable(FILENAME_C);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            saveCustomerMap(customerMap);
        }
        return customerMap;
    }
    public static HashMap<String, Payment> loadPaymentMap() {
        HashMap<String, Payment> paymentMap = new HashMap<>();
        try {
            paymentMap = (HashMap)FileHandler.loadSerializable(FILENAME_P);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            savePaymentMap(paymentMap);
        }
        return paymentMap;
    }
    public static HashMap<String, User> loadUserMap() {
        HashMap<String, User> userMap = new HashMap<>();
        try {
            userMap = (HashMap)FileHandler.loadSerializable(FILENAME_U);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            saveUserMap(userMap);
        }
        return userMap;
    }
    public static HashMap<String, Meter> loadMeterMap() {
        HashMap<String, Meter> meterMap = new HashMap<>();
        try {
            meterMap = (HashMap)FileHandler.loadSerializable(FILENAME_M);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            saveMeterMap(meterMap);
        }
        return meterMap;
    }
}