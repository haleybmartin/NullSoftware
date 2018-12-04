package utilities;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Meter Reader Route Generation Utility
 * @author Haley Martin for Null Software
 */
public class RouteGenerator {
    
    public static ArrayList<String> generateRoute(ArrayList<String> addresses, String origin) throws IOException {
        ArrayList<String> route = new ArrayList<>();
        
        double[] distances = new double[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            distances[i] = calculateDistance(origin, addresses.get(i));
        }
        int index = minIndex(distances);
        route.add(addresses.get(index));
        addresses.remove(index);
        
        while(addresses.size() > 0) {
            distances = new double[addresses.size()];
            for (int i = 0; i < addresses.size(); i++) {
                distances[i] = calculateDistance(route.get(route.size()-1), addresses.get(i));
            }
            index = minIndex(distances);
            route.add(addresses.get(index));
            addresses.remove(index);
        }
        
        return route;
    }
    
    private static double calculateDistance(String origin, String destination) throws IOException {
        //gives answer in feet
        origin = origin.replace(' ', '+');
        destination = destination.replace(' ', '+');
        String searchURL = "https://www.google.com/search?q="+origin+"+to+"+destination;
        
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36").get();
        String html = doc.toString();
        
        int index = html.indexOf(" (<span>");
        
        String line = html.substring(index+8, html.indexOf("</span>", index));
        
        String number = line.substring(0, line.indexOf("&"));
        double distance = Double.parseDouble(number);
        String unit = line.substring(line.indexOf(";")+1);
        
        if (unit.equals("mi")) {
            distance = distance * 5280;
        }
        return distance;
    }
    
    private static int minIndex(double[] values) {
        int index = 0;
        double min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
                index = i;
            }
        }
        return index;
    }
}
