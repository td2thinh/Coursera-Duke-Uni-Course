import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts)
    {
        for (String word : resource.words()) 
        {
            int letterCount = 0;
            for (int index = 0; index < word.length(); index++) 
            {
                if (!Character.isLetter(word.charAt(index)) && index == word.length() - 1)
                {
                    break;
                }
                letterCount++;
            }
            
            if (letterCount >= counts.length) 
            {
                counts[counts.length] += 1;
            }
            
            else 
            {
                counts[letterCount] += 1;
            }
        }
    }
    
    public int indexOfMax(int[] values)
    {
        int max = values[0];
        int index = 0;;
        for (int i = 1; i < values.length; i++)
        {
            if (values[i] > max)
            {
                index = i;
                max = values[i];
            }
        }
        return index;
    }
    
    public void testCountWordLengths ()
    {
        FileResource file = new FileResource();
        int[] counts = new int[31];
        countWordLengths(file, counts);
        for (int i = 0; i < counts.length; i++) 
        {
           if (counts[i] > 0)
           {
            System.out.println("Word of length " + i + ":\t" + counts[i]);
           }
        }
        
        System.out.println("Index of max: " + indexOfMax(counts));
        
    }
}
