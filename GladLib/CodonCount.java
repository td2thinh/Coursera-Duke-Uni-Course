import java.util.*; 
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> map;

    public CodonCount()
    {
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna)
    {
        map.clear();
        for (int i = start; i < dna.length() - 2; i += 3)
        {
            String codon = dna.substring(i, i + 3);
            if (!map.containsKey(codon))
            {
                map.put(codon, 1);
            }
            else
            {
                map.put(codon, map.get(codon) + 1);
            }
        }
    }

    public String getMostCommonCodon()
    {
        int max = 0;
        String mostCommonCodon = "";
        for (String codon : map.keySet())
        {
            if (map.get(codon) > max)
            {
                max = map.get(codon);
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts (int start, int end)
    {
        for (String codon : map.keySet())
        {
            if (map.get(codon) >= start && map.get(codon) <= end)
            {
                System.out.println(codon + "\t" + map.get(codon));
            }
        }
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i++)
        {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(7, 10);
            System.out.println();
        }
    }
}
