package data;

/**
 *
 * @author Haley Martin for Null Software
 * @version 1.0
 */
public class MinimumBill {
    private static String[][] billingTable = { {"5/8", "A", "31.54"}, 
                                        {"3/4", "B", "45.81"}, 
                                        {"1", "F", "76.35"}, 
                                        {"2", "K", "244.32"}, 
                                        {"3", "N", "488.64"}, 
                                        {"4", "R", "763.50"} };
    
    public static String[][] getBillingTable() { return billingTable; }
}
