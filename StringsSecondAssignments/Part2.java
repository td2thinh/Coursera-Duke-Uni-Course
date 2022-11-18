
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        
        int countOcc = 0;
        int start = 0;
        int indexOcc;
        while (true)
        {
            indexOcc = stringb.indexOf(stringa, start + 1);
            if(indexOcc == -1)
            {
                break;
            }
            start += indexOcc;
            countOcc += 1;
        }
        
        return countOcc;
   }
   public void testHowMany()
   {
       System.out.println(howMany("AA", "ATAAAA"));
       System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
   }
}
