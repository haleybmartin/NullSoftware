package systemobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class Meter implements Serializable {
    
    //MARK: FIELDS
    
    private ArrayList<MeterReading> readings;
    private char meterType;
    private int meterNumber;
    private String address;
    private UUID meterID, customerID;
    
    //MARK: CONSTRUCTOR
    
    public Meter(char meterType, int meterNumber, String address, UUID meterID, UUID customerID) {
        readings = new ArrayList<>();
        this.meterType = meterType;
        this.meterNumber = meterNumber;
        this.address = address;
        this.meterID = meterID;
        this.customerID = customerID;
    }
    
    //MARK: ACTIONS
    
    public void addReading(Date readingDate, int currentReading, UUID readingID) {
        int previousReading = 0;
        if(readings.size() > 0) {
            previousReading = readings.get(readings.size()-1).getCurrentReading();
        }
        MeterReading reading = new MeterReading(readingDate, meterType, currentReading, previousReading, readingID, meterID, customerID);
        readings.add(reading);
    }
    
    //MARK: GETTERS/SETTERS
    
    public String getAddress() { return address; }
    public UUID getMeterID() { return meterID; }
}
