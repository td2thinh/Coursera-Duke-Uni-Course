
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            Date accessTime = le.getAccessTime();
            String accessDate = accessTime.toString();
            if (accessDate.contains(someday) && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }

        return uniqueIPs;
    }

    public ArrayList<String> countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (String ip : counts.keySet()) {
            int count = counts.get(ip);
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> ips = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            int count = counts.get(ip);
            if (count == max) {
                ips.add(ip);
            }
        }
        return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            Date accessTime = le.getAccessTime();
            String accessDate = accessTime.toString();
            String date = accessDate.substring(4, 10);
            if (!ipsForDays.containsKey(date)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ipAddr);
                ipsForDays.put(date, ips);
            } else {
                ArrayList<String> ips = ipsForDays.get(date);
                ips.add(ipAddr);
                ipsForDays.put(date, ips);
            }
        }
        return ipsForDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts) {
        String day = "";
        int max = 0;
        for (String date : counts.keySet()) {
            ArrayList<String> ips = counts.get(date);
            int count = ips.size();
            if (count > max) {
                max = count;
                day = date;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String date) {
        ArrayList<String> ips = counts.get(date);
        HashMap<String, Integer> countsPerIP = new HashMap<String, Integer>();
        for (String ip : ips) {
            if (!countsPerIP.containsKey(ip)) {
                countsPerIP.put(ip, 1);
            } else {
                countsPerIP.put(ip, countsPerIP.get(ip) + 1);
            }
        }
        return iPsMostVisits(countsPerIP);
    }

}
