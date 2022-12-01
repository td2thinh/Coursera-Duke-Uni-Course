
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("There are " + la.countUniqueIPs() + " unique IPs");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        String date = "Mar 24";
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(date);
        System.out.println("There are " + uniqueIPs.size() + " unique IPs on " + date);
        for (String ip : uniqueIPs)
        {
            System.out.println(ip);
        }
    }

    public void testCountUniqueIPsInRange ()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int low = 200;
        int high = 299;
        ArrayList<String> IPs = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + IPs.size() + " unique IPs in the range " + low + " to " + high);
    }
}
