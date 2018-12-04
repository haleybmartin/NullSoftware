package systemobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class MeterReading implements Serializable {
    
    //MARK: FIELDS
    
    private Date dateOfReading;
    private char meterType;
    private int currentReading, previousReading;
    private UUID readingID, meterID, customerID;
    
    //MARK: CONSTRUCTOR
    
    public MeterReading(Date dateOfReading, char meterType, int currentReading, int previousReading, UUID readingID, 
            UUID meterID, UUID customerID) {
        this.dateOfReading = dateOfReading;
        this.meterType = meterType;
        this.currentReading = currentReading;
        this.previousReading = previousReading;
        this.readingID = readingID;
        this.meterID = meterID;
        this.customerID = customerID;
    }
    
    //MARK: GETTERS/SETTERS
    
    public int getUsage() { return (currentReading - previousReading); }
    
    public Date getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(Date dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public char getMeterType() {
        return meterType;
    }

    public void setMeterType(char meterType) {
        this.meterType = meterType;
    }

    public int getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    public int getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(int previousReading) {
        this.previousReading = previousReading;
    }

    public UUID getReadingID() {
        return readingID;
    }

    public void setReadingID(UUID readingID) {
        this.readingID = readingID;
    }

    public UUID getMeterID() {
        return meterID;
    }

    public void setMeterID(UUID meterID) {
        this.meterID = meterID;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public void setCustomerID(UUID customerID) {
        this.customerID = customerID;
    }
}
