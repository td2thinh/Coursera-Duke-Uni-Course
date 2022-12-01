
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines())
        {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
     }

     public int countUniqueIPs()
     {
            ArrayList<String> uniqueIPs = new ArrayList<String>();
            for (LogEntry le : records)
            {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr))
                {
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
     
     public void printAllHigherThanNum (int num)
     {
        for (LogEntry le : records)
        {
            if (le.getStatusCode() > num)
            {
                System.out.println(le);
            }
        }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday)
     {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String ipAddr = le.getIpAddress();
            Date accessTime = le.getAccessTime();
            String accessDate = accessTime.toString();
            if (accessDate.contains(someday) && !uniqueIPs.contains(ipAddr))
            {
                uniqueIPs.add(ipAddr);
            }
        }

        return uniqueIPs;
     }

     public ArrayList<String> countUniqueIPsInRange (int low, int high)
     {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String ipAddr = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr))
            {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
     }  
}
