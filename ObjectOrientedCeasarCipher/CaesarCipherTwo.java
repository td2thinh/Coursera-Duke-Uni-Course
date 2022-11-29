
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo (int key1, int key2)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey1 = key1;
        mainKey2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt (String input)
    {
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
    
    public String decrypt(String input)
    {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        
        return cc.encrypt(input);
    }
}
