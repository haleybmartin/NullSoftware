package main;

import data.DataManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import systemobjects.Payment;

import userinterface.*;
import utilities.*;
/**
 * Valley Falls Water System Application Runner
 * @author Haley Martin for Null Software
 */
public class ValleyFallsWaterSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session session = new Session();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}
