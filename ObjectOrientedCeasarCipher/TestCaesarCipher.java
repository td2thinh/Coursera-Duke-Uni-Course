import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnoqprstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values)
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
    
    public String breakCaesarCipher(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        String message = cc.decrypt(encrypted);
        return message;
    }
    
    public void simpleTests ()
    {
        String file = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        System.out.println("Encrypted string: " + cc.encrypt(file));
        System.out.println("Decrypted string: " + cc.decrypt(cc.encrypt(file)));
        System.out.println("Decrypted string using Autobreaker: " + breakCaesarCipher(cc.encrypt(file)));
    }
}
