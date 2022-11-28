import edu.duke.*;
import java.io.File;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt (String input, int key)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shiftedAlphabet = new StringBuilder(alphabet.substring(key) + alphabet.substring(0, key));
        
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++)
        {
            int index = alphabet.indexOf(Character.toUpperCase(sb.charAt(i)));
            if (index >= 0)
            {
                if (Character.isLowerCase(sb.charAt(i)))
                {
                    sb.setCharAt(i,  Character.toLowerCase(shiftedAlphabet.charAt(index)));
                }
                else
                {
                    sb.setCharAt(i, shiftedAlphabet.charAt(index));
                }
            }
        }
        
        return new String(sb);
    }
    
    public String encryptTwoKeys (String input, int key1, int key2)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shiftedAlphabet1 = new StringBuilder(alphabet.substring(key1) + alphabet.substring(0, key1));
        StringBuilder shiftedAlphabet2 = new StringBuilder(alphabet.substring(key2) + alphabet.substring(0, key2));
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++)
        {
            int index = alphabet.indexOf(Character.toUpperCase(sb.charAt(i)));
            if (index >= 0)
            {
                if (i % 2 == 0)
                {
                    if (Character.isLowerCase(sb.charAt(i)))
                    {
                        sb.setCharAt(i,  Character.toLowerCase(shiftedAlphabet1.charAt(index)));
                    }
                    else
                    {
                        sb.setCharAt(i, shiftedAlphabet1.charAt(index));
                    }
                }
                else
                {
                    if (Character.isLowerCase(sb.charAt(i)))
                    {
                        sb.setCharAt(i,  Character.toLowerCase(shiftedAlphabet2.charAt(index)));
                    }
                    else
                    {
                        sb.setCharAt(i, shiftedAlphabet2.charAt(index));
                    }
                }
            }
        }
        
        return new String(sb);
    }
    public void testCaesar ()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
