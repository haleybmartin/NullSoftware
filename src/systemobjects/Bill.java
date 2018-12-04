package systemobjects;

import data.DataManager;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class Bill implements Serializable {
    
    //MARK: FIELDS
    
    private boolean isPaid;
    private Date dateDue;
    private double amountCharged;
    private double amountDue, leakAdjustment, lateFee;
    private UUID billID;
    private UUID customerID, paymentID;
    
    //MARK: CONSTRUCTOR
    
    /**
     * Creates a new Bill
     * @param dateDue the date on which payment of this Bill is due
     * @param amountCharged the dollar amount charged for this Bill
     * @param billID the UUID of this Bill
     * @param customerID the UUID of the Customer being billed
     */
    public Bill(Date dateDue, double amountCharged, UUID billID, UUID customerID) {
        isPaid = false;
        this.dateDue = dateDue;
        this.amountCharged = amountCharged;
        amountDue = amountCharged;
        leakAdjustment = 0;
        lateFee = 0;
        this.billID = billID;
        this.customerID = customerID;
        this.paymentID = null;
    }
    
    //MARK: ACTIONS
    
    /**
     * Performs all necessary adjustments when a customer pays their bill
     * @param paymentID the UUID of the payment made for this bill
     */
    public void makePayment(UUID paymentID) {
        this.paymentID = paymentID;
        Payment payment = DataManager.getPaymentByID(paymentID);
        amountDue = amountDue - payment.getAmount();
        if(amountDue <= 0)
            isPaid = true;
        else
            isPaid = false;
    }
    
    /**
     * Credit the bill in the case of a water leak
     * @param adjustmentAmount the dollar amount by which to adjust the amount
     * due
     */
    public void applyLeakAdjustment(double adjustmentAmount) {
        amountDue -= adjustmentAmount;
        leakAdjustment += adjustmentAmount;
        if(amountDue <= 0)
            isPaid = true;
        else
            isPaid = false;
    }
    
    /**
     * Apply a late fee to this bill
     * @param feeAmount the dollar amount by which to adjust the amount due
     */
    public void applyLateFee(double feeAmount) {
        amountDue += feeAmount;
        lateFee += feeAmount;
        if(amountDue <= 0)
            isPaid = true;
        else
            isPaid = false;
    }
    //MARK: GETTERS/SETTERS
    
    /**
     * Get the payment status of this bill
     * @return true if this bill has been paid in full, false otherwise
     */
    public boolean getIsPaid() { return isPaid; }
    
    /**
     * Get the date on which this bill was due
     * @return the Date on which this bill was due
     */
    public Date getDateDue() { return dateDue; }
    /**
     * Set the date on which this bill was due (to correct an error made upon
     * initial billing)
     * @param dateDue the Date on which this bill was due
     */
    public void setDateDue(Date dateDue) { this.dateDue = dateDue; }
    
    /**
     * Get the dollar amount charged for this bill
     * @return the dollar amount charged for this bill
     */
    public double getAmountCharged() { return amountCharged; }
    
    /**
     * Get the dollar amount that is due for this bill
     * @return the dollar amount that remains to be paid for this bill
     */
    public double getAmountDue() { return amountDue; }
    /**
     * Set the dollar amount that is due for this bill (to correct an error
     * made upon initial billing, NOT to apply leak adjustments or late fees)
     * @param amountDue the dollar amount that remains to be paid for this bill
     */
    public void setAmountDue(double amountDue) { this.amountDue = amountDue; }
    
    /**
     * Get the UUID for this bill
     * @return the UUID for this bill
     */
    public UUID getBillID() { return billID; }
    
    /**
     * Get the UUID of the customer being billed
     * @return the UUID of the customer being billed
     */
    public UUID getCustomerID() { return customerID; }
    /**
     * Set the UUID of the customer being billed (to correct an error made upon
     * initial billing)
     * @param customerID the UUID of the customer being billed
     */
    public void setCustomerID(UUID customerID) { this.customerID = customerID; }
    
    /**
     * Get the UUID of the payment made for this bill
     * @return the UUID of the payment made for this bill, null if no payment
     * has been made
     */
    public UUID getPaymentID() { return paymentID; }
    /**
     * Set the UUID of the payment made for this bill (to correct an error made
     * upon initial payment). The bill is recalculated with the new paymentID
     * @param paymentID the UUID of the payment made for this bill
     */
    public void setPaymentID(UUID paymentID) {
        this.paymentID = paymentID;
        amountDue = amountCharged;
        amountDue -= leakAdjustment;
        amountDue += lateFee;
        if(this.paymentID == null)
            isPaid = false;
        else
            makePayment(this.paymentID);
    }
    
    /**
     * Get the dollar amount applied to this bill to adjust for leaks
     * @return the dollar amount credited to this bill
     */
    public double getLeakAdjustment() { return leakAdjustment; }
    /**
     * Set the dollar amount applied to this bill to adjust for leaks (to
     * correct an error made when applying leak adjustments). The bill is
     * recalculated
     * @param leakAdjustment the dollar amount to credit this bill
     */
    public void setLeakAdjustment(double leakAdjustment) {
        amountDue += this.leakAdjustment;
        this.leakAdjustment = leakAdjustment;
        amountDue -= this.leakAdjustment;
        if(amountDue <= 0)
            isPaid = true;
        else
            isPaid = false;
    }
    
    /**
     * Get the dollar amount applied to this bill as a late fee
     * @return the dollar amount owed for this bill
     */
    public double getLateFee() { return lateFee; }
    /**
     * Set the dollar amount applied to this bill as a late fee (to correct an
     * error made when assessing late fees). The bill is recalculated
     * @param lateFee the dollar amount for this bill
     */
    public void setLateFee(double lateFee) {
        amountDue -= this.lateFee;
        this.lateFee = lateFee;
        amountDue += this.lateFee;
        if(amountDue <= 0)
            isPaid = true;
        else
            isPaid = false;
    }
    
    //MARK: INFORMATION
}