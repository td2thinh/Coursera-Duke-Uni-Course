
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
 public int findStopCodon (String dna, int startIndex, String stopCodon)
    {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        if (stopIndex == -1)
        {
            return dna.length();
        }
        else if ((stopIndex - startIndex) % 3 != 0)
        {
            return dna.length();
        }
        
        return stopIndex;
        
    }
    
    public String findGene(String dna, int start)
    {
        int startIndex = dna.indexOf("ATG", start);
        if (startIndex == -1)
        {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length())
        {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public int countGenes (String dna)
    {
        int start = 0, count = 0;
        String gene;
        while (true)
        {
            gene = findGene(dna, start);
            if (gene.isEmpty())
            {
                break;
            }
            else
            {
                count += 1;
            }
            start += gene.length() + 1;
        }
        return count;
    }
    
    public void testFindAllGenes(){
        System.out.println("Genes: " + countGenes("ATGTAAGATGCCCTAGT"));
    }
}
