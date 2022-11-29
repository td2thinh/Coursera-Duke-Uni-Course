import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
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
    
    public String halfOfString (String message, int start)
    {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2){
            char a = message.charAt(i);
            half.append(a);
        }
        return half.toString();
    }
    public int getKey(String s)
    {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    public String breakCaesarCipher(String encrypted){
        String oddHalf = halfOfString(encrypted, 0);
        String evenHalf = halfOfString(encrypted, 1);
        int dkey1 = getKey(oddHalf);
        int dkey2 = getKey(evenHalf);
        System.out.println(dkey1 + "\t" + dkey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - dkey1, 26 - dkey2);
        return cc.encrypt(encrypted);
    }
    
    public void simpleTests ()
    {
        String file = new FileResource().asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        System.out.println("Encrypted string: " + cc.encrypt(file));
        System.out.println("Decrypted string: " + cc.decrypt(cc.encrypt(file)));
        System.out.println("Decrypted string using Autobreaker: " + breakCaesarCipher(cc.encrypt(file)));
    }
}
