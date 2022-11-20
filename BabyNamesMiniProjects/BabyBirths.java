import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void totalBirths (FileResource fr)
    {
        int totalBirths = 0, totalBoys = 0, totalGirls = 0, boyNames = 0, girlNames = 0, totalNames = 0;
        String currentName = "";
        for (CSVRecord record : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("M"))
            {
                if(!record.get(0).equals(currentName))
                {
                    currentName = record.get(0);
                    boyNames += 1;
                }
                totalBoys += numBorn;
            }
            else 
            {
                 if(!record.get(0).equals(currentName))
                {
                    currentName = record.get(0);
                    girlNames += 1;
                }
                totalGirls += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total girls = " + totalGirls);
        totalNames = boyNames + girlNames;
        System.out.println("Total names = " + totalNames);
        System.out.println("Total boy names = " + boyNames);
        System.out.println("Total girl names = " + girlNames);
    }
    
    public int getRank (int year, String name, String gender)
    {
        int rank = 1;
        FileResource fr = new FileResource("yob" + Integer.toString(year) + "short.csv");
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
                if (rec.get(0).equals(name))
                {
                    return rank;
                }
                rank += 1;
            }
        }
        return -1;
    }
    
    public String getName (int year, int rank, String gender)
    {
        int currentRank = 1;
        FileResource fr = new FileResource("yob" + Integer.toString(year) + "short.csv");
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
                if (currentRank == rank)
                {
                    return rec.get(0);
                }
                currentRank += 1;
            }
        }
        return "No Name";
    }
    
    public void testTotalBirths ()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank()
    {
        String name = "Mason";
        System.out.println("Rank for " + name + " " + getRank(2012, name, "M"));
    }
    
    public void testGetName()
    {
        int rank = 2;
        System.out.println("Name of rank " + rank + ": " + getName(2012, rank, "M"));
    }
}
