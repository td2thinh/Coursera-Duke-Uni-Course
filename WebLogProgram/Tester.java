
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
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
        la.readFile("weblog2_log");
        System.out.println("There are " + la.countUniqueIPs() + " unique IPs");
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String date = "Sep 24";
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(date);
        System.out.println("There are " + uniqueIPs.size() + " unique IPs on " + date);
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 400;
        int high = 499;
        ArrayList<String> IPs = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + IPs.size() + " unique IPs in the range " + low + " to " + high);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("The most number of visits by an IP is " + max);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> IPs = la.iPsMostVisits(counts);
        System.out.println("The IPs with the most visits are:");
        for (String ip : IPs) {
            System.out.println(ip);
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        String day = la.dayWithMostIPVisits(counts);
        System.out.println("The day with the most IP visits is " + day);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println("The IPs for each day are:");
        for (String day : counts.keySet()) {
            System.out.println(day + ": " + counts.get(day));
        }
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        String day = "Sep 30";
        ArrayList<String> IPs = la.iPsWithMostVisitsOnDay(counts, day);
        System.out.println("The IPs with the most visits on " + day + " are:");
        for (String ip : IPs) {
            System.out.println(ip);
        }
    }

}
