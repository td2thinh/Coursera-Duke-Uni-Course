import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public static void main (String[] args)
    {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : ur.words())
        {
            int keywordIndex = word.toLowerCase().indexOf("youtube.com");
            if (keywordIndex != -1)
            {
                int startIndex = word.lastIndexOf("\"", keywordIndex);
                System.out.println(startIndex);
                int endIndex = word.indexOf("\"", startIndex + 1);
                System.out.println(endIndex);
                String youtubeLink = word.substring(startIndex + 1, endIndex);
                System.out.println(youtubeLink);
            }
        }
    }
}
