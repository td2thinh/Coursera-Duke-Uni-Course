import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Export {
    
    public String countryInfo (CSVParser parser, String country)
    {
        for (CSVRecord record : parser)
        {
            
            if (record.get("Country").contains(country))
            {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        
        }
            return "Not Found";
    }
    public void listExportersTwoProducts (CSVParser parser, String exportItem2, String exportItem1)
    {
         for (CSVRecord record : parser)
        {
            
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2))
            {
                System.out.println( record.get("Country"));
            }
        
        }
   
        }
        public int numberOfExporters (CSVParser parser, String exportItem)
    {
        int count = 0;
         for (CSVRecord record : parser)
        {
            
            if (record.get("Exports").contains(exportItem))
            {
                count += 1;
            }
        
        }
   return count;
        }
        public void bigExporters (CSVParser parser, String amount)
        {
              for (CSVRecord record : parser)
        {
            
            if (record.get("Value (dollars)").length() > amount.length())
            {
                System.out.println( record.get("Country") + " " + record.get("Value (dollars)"));
            }
        
        }}
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
}

}
