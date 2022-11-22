import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
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
        FileResource fr = new FileResource("us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
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
        FileResource fr = new FileResource("us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
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
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender)
    {
        int oldRank = getRank(year, name, gender);
        String newName = getName(newYear, oldRank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public int yearOfHighestRank (String name, String gender)
    {
        int year = 0, result = 0;
        int highestRank = Integer.MAX_VALUE;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            year = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
            int currentRank = getRank(year, name, gender);
            
            if (currentRank <= highestRank)
            {
                highestRank = currentRank;
                result = year;
            }
        }
        return result;
    }
    
    public double getAverageRank (String name, String gender)
    {
        int result = 0, count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            int year = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
            int currentRank = getRank(year, name, gender);
            result += currentRank;
            count += 1;
        }
        return result * 1.0 / count;
    }
        
    public int getTotalBirthsRankedHigher (int year, String name, String gender)
    {
        int nameRank = getRank(year, name, gender);
        int totalBirths = 0;
        int rank;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            String currentName = rec.get(0);
            if (rec.get(1).equals(gender) && getRank(year, currentName, gender) < nameRank)
            {
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        
        return totalBirths;
    }
    
    public void testTotalBirths ()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);    
    }
    
    public void testGetRank()
    {
        String name = "Frank";
        System.out.println("Rank for " + name + " " + getRank(1971, name, "M"));
    }
    
    public void testGetName()
    {
        int rank = 450;
        System.out.println("Name of rank " + rank + ": " + getName(1982, rank, "M"));
    }
    
    public void testNewName()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testGetYear()
    {
        String name = "Mich";
        System.out.println("Year of highest rank for " + name + " is: " + yearOfHighestRank(name, "M"));
    }
    
    public void testAvgRank()
    {
        String name = "Robert";
        System.out.println("Average rank for " + name + " : " + getAverageRank(name, "M"));
    }
    
    public void testTotalBirthsHigher()
    {
        String name = "Drew";
        System.out.println("Total Births ranked higher than " + name + ": " + getTotalBirthsRankedHigher(1990, name, "M"));
    }
}
