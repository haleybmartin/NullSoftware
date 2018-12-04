package systemobjects;

import data.DataManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Customer
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class Customer implements Serializable {
    
    //MARK: FIELDS
    
    private ArrayList<UUID> bills, payments;
    private double balance;
    private int accountNumber;
    private static int nextAccountNumber = 0;
    private int meterNumber;
    private String address, name, phone;
    private UUID customerID, meterID;
    
    //MARK: CONSTRUCTOR
    
    public Customer(int meterNumber, String address, String name, String phone, 
            UUID customerID, UUID meterID) {
        bills = new ArrayList<>();
        payments = new ArrayList<>();
        balance = 0;
        accountNumber = nextAccountNumber;
        nextAccountNumber++;
        this.meterNumber = meterNumber;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.customerID = customerID;
        this.meterID = meterID;
    }
    
    //MARK: ACTIONS
    
    public void makePayment(UUID paymentID) {
        payments.add(paymentID);
        Payment payment = DataManager.getPaymentByID(paymentID);
        UUID billID = payment.getBillID();
        DataManager.getBillByID(billID).makePayment(paymentID);
    }
    
    public void addBill(UUID billID) {
        bills.add(billID);
    }
    
    //MARK: GETTERS/SETTERS
    
    public ArrayList<UUID> getBills() { return bills; }
    
    public ArrayList<UUID> getPayments() { return payments; }
    
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    
    public int getAccountNumber() { return accountNumber; }
    
    public int getMeterNumber() { return meterNumber; }
    public void setMeterNumber(int meterNumber) { this.meterNumber = 
            meterNumber; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public UUID getCustomerID() { return customerID; }
}