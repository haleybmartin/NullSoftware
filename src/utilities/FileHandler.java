package utilities;

import java.io.*;

/**
 * File Reading/Writing Utility
 * @author Haley Martin for Null Software
 */
public class FileHandler {
    public static void saveSerializable(Object obj, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(obj);
            
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Object loadSerializable(String filename) throws FileNotFoundException {
        Object obj = new Object();
        
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            obj = ois.readObject();
            
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) { 
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return obj;
    }
}
