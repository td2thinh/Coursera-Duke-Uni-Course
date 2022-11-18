
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
        {
            return result;
        }
        int endIndex = dna.indexOf("TAA", startIndex);
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
        String[] dnaArray = {"ATCCCGGCCCGAT",  "ATGCACTGTGTGGCTGGGTTT", "ATCCCGGCCCGAT", "ATGCCAATCCTGTGCTAATT", "ATGTTGGCCACGTAGAATAA"};
        for (String dna : dnaArray){
            System.out.println("The DNA: " + dna);
            System.out.println("Gene: " + findSimpleGene(dna));
        }
    }
    public static void main(String[] args){
        Part1 example = new Part1();
        example.testSimpleGene();
	
	}
}

