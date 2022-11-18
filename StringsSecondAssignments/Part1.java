
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void printAllGenes(String dna)
    {
        int start = 0;
        String gene;
        while (true)
        {
            gene = findGene(dna, start);
            System.out.println(gene);
            if (gene.isEmpty())
            {
                break;
            }
            start += gene.length() + 1;
        }
    }
    public void testFindStopCodon(){
        System.out.println(findGene("ATGTAA", 0));
        System.out.println(findGene("AGTAXATGTTTATTAA", 0 ));
        System.out.println(findGene("ATGAAAAATTGAAGGGTAA", 0));
        System.out.println(findGene("ATGAAAGGGTTTTAGATGTAAATGTAAATGTAA", 0));
    }
    
    public void testFindAllGenes(){
        printAllGenes("AATGCTAACTAGCTGACTAAT");
    }
}
