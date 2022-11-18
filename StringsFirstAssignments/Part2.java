
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
            String result = "";
            int startIndex = dna.toUpperCase().indexOf(startCodon);
            if (startIndex == -1)
            {
                return result;
            }
            int endIndex = dna.toUpperCase().indexOf(stopCodon, startIndex);
            if (endIndex == -1){
                return result;
            }
            if ((endIndex - startIndex) % 3 == 0){
            result = dna.substring(startIndex, endIndex + 3);
            return result;
        }
            return result;
        }
    public void testSimpleGene ()
    {
        String[] dnaArray = {"AAATGCCCTAACTAGATTAAGAAACC",  "ATGCACTGTGTGGCTGGGTTT", "ATCCCGGCCCGAT", "ATGCCAATCCTGTGCTAATT", "ATGTTGGCCACGTAGAATAA", "ATGGGTTAAGTC", "gatgctataat"};
        for (String dna : dnaArray){
            System.out.println("The DNA: " + dna);
            System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
        }
    }
    public static void main(String[] args){
        Part2 example = new Part2();
        example.testSimpleGene();
    
    }
}
