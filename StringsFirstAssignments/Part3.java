
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean  twoOccurrences (String stringa, String stringb)
    {
        int count = 0;
        int firstOcc = stringb.indexOf(stringa);
        if(firstOcc == -1)
        {
            return false;
        }
        else if (stringb.indexOf(stringa, firstOcc) != -1)
        {
            return true;
        }
        
        return false;
    }
    
    public String lastPart (String stringa, String stringb)
    {
        int firstOcc = stringb.indexOf(stringa);
        if(firstOcc == -1)
        {
            return stringb;
        }
        else 
        {
            return stringb.substring(firstOcc + stringa.length());
        }
    }
    public void testing ()
    {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
    
    public static void main (String args[])
    {
        Part3 example = new Part3();
        example.testing();
    }
}
