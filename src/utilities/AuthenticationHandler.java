package utilities;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Account Authentication Utility
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class AuthenticationHandler {
    
    private static final String SALT = "nullsoftware";
    private static final String FILENAME = "authmap.ser";
    
    public static void addPassword(String username, String password) {
        HashMap<String, String> authMap = loadPasswords();
        if (authMap.get(username) != null) {
            //This userID already exists in the database
            return;
        }
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        authMap.put(username, hashedPassword);
        savePasswords(authMap);
    }
    
    public static boolean authenticatePassword(String username, String password) {
        HashMap<String, String> authMap = loadPasswords();
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        String storedHash = authMap.get(username);
        if (hashedPassword.equals(storedHash))
            return true;
        return false;
    }
    
    private static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            for(int i = 0; i < hashedBytes.length; i++) {
                byte b = hashedBytes[i];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }
    
    private static HashMap<String, String> loadPasswords() {
        HashMap<String, String> authMap = new HashMap<>();
        try {
            authMap = (HashMap)FileHandler.loadSerializable(FILENAME);
        } catch (FileNotFoundException e) {
            //Save an empty hashmap to the file
            savePasswords(authMap);
        }
        return authMap;
    }
    
    private static void savePasswords(HashMap<String, String> authMap) {
        FileHandler.saveSerializable(authMap, FILENAME);
    }
}