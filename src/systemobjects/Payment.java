package systemobjects;

import data.DataManager;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * The Payment class records a single transaction in which a customer provides
 * a payment for a billing cycle.
 * 
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class Payment implements Serializable {
    
    public enum PaymentType { CASH, CHECK }
    
    //MARK: FIELDS
    
    private PaymentType paymentType;
    private double amount;
    private Date dateFiled;
    private Date dateDue;
    private String checkNumber;
    private UUID paymentID, userID;
    private UUID billID, customerID;
    
    //MARK: CONSTRUCTOR
    
    /**
     * Creates a new Payment
     * @param paymentType the method of payment used by the customer
     * @param amount the dollar amount paid by the customer
     * @param dateDue the date on which payment was due
     * @param checkNumber the check number, if check was used
     * @param paymentID the UUID of this payment
     * @param billID the UUID of the bill this payment is for
     * @param customerID the UUID of the paying customer
     * @param userID the UUID of the user filing the payment
     */
    public Payment(PaymentType paymentType, double amount, Date dateDue, 
            String checkNumber, UUID paymentID, UUID billID, UUID customerID, UUID userID) {
        this.paymentType = paymentType;
        this.amount = amount;
        dateFiled = new Date();
        this.dateDue = dateDue;
        this.checkNumber = checkNumber;
        this.paymentID = paymentID;
        this.billID = billID;
        this.customerID = customerID;
        this.userID = userID;
    }
    
    //MARK: GETTERS/SETTERS
    
    /**
     * Get the type of payment
     * @return the method of payment used by the customer, 
     * either CASH or CHECK
     */
    public PaymentType getPaymentType() { return paymentType; }
    /**
     * Set the type of payment (to correct an error upon initial filing)
     * @param paymentType the method of payment used by the customer, either CASH or CHECK
     */
    public void setPaymentType(PaymentType paymentType) { this.paymentType = 
            paymentType; }
    
    /**
     * Get the dollar amount paid
     * @return the dollar amount paid by the customer as a double
     */
    public double getAmount() { return amount; }
    /**
     * Set the dollar amount paid (to correct an error upon initial filing)
     * @param amount the dollar amount paid by the customer as a double
     */
    public void setAmount(double amount) { this.amount = amount; }
    
    /**
     * Get the Date on which this payment was filed
     * @return the Date on which this payment was filed
     */
    public Date getDateFiled() { return dateFiled; }
    
    /**
     * Get the Date on which this payment was due
     * @return the Date on which this payment was due
     */
    public Date getDateDue() { return dateDue; }
    /**
     * Set the Date on which this payment was due (to correct an error upon 
     * initial filing)
     * @param dateDue the Date on which this payment was due
     */
    public void setDateDue(Date dateDue) { this.dateDue = dateDue; }
    
    /**
     * Get the check number used in this payment
     * @return the check number used in this payment, null if check was not the
     * method of payment
     */
    public String getCheckNumber() { return checkNumber; }
    /**
     * Set the check number used in this payment (to correct an error upon
     * initial filing)
     * @param checkNumber the check number used in this payment, null if check
     * was not the method of payment
     */
    public void setCheckNumber(String checkNumber) { this.checkNumber =
            checkNumber; }
    
    /**
     * Get the ID of this payment
     * @return the UUID of this payment 
     */
    public UUID getPaymentID() { return paymentID; }
    
    /**
     * Get the ID of the bill this payment is for
     * @return the UUID of the bill
     */
    public UUID getBillID() { return billID; }
    /**
     * Set the ID of the bill this payment is for (to correct an error upon
     * initial filing)
     * @param billID the UUID of the bill
     */
    public void setBillID(UUID billID) { this.billID = billID; }
    
    /**
     * Get the ID of the paying customer
     * @return the UUID of the paying customer
     */
    public UUID getCustomerID() { return customerID; }
    /**
     * Set the ID of the paying customer (to correct an error upon initial 
     * filing)
     * @param customerID the UUID of the paying customer 
     */
    public void setCustomerID(UUID customerID) { this.customerID = customerID; }
    
    /**
     * Get the ID of the user who filed this payment
     * @return the UUID of the user who filed this payment
     */
    public UUID getUserID() { return userID; }
    
    //MARK: INFORMATION
    
    /**
     * Get a textual representation of the payment data
     * @return a String representation of this payment
     */
    @Override
    public String toString() {
        DecimalFormat decFormat = new DecimalFormat("#,###,##0.00");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
        String str = "PAYMENT-------------------------------------------\n";
        str += "Payment ID: " + paymentID.toString() + "\n";
        str += "Payment Type: " + paymentType + "\n";
        str += "Check No.: ";
        if(checkNumber == null)
            str += "N/A\n";
        else
            str += checkNumber + "\n";
        str += "Amount Paid: $" + decFormat.format(amount) + "\n";
        str += "Customer: " + DataManager.getCustomerByID(customerID) + "\n";
        //str += "Account No.: " + DataManager.getAccountNumberByUUID(customerID) + "\n";
        str += "Date Due: " + dateFormat.format(dateDue) + "\n";
        str += "Date Filed: " + dateFormat.format(dateFiled) + "\n";
        str += "Filed By: " + DataManager.getUserByID(userID) + "\n";
        str += "--------------------------------------------------";
        return str;
    }
}