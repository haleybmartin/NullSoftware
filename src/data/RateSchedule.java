/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author hbmar
 */
public class RateSchedule {
    private static double[][] rateTable = { {5000, 10.90}, 
                                     {5000, 10.55}, 
                                     {10000, 10.00}, 
                                     {10000, 9.45}, 
                                     {0, 8.60} };
    
    public static double[][] getRateTable() { return rateTable; }
}
