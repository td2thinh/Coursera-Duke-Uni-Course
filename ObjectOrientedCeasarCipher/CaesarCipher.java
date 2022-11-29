
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public String encrypt(String input)
    {
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
        
        return sb.toString();
    }
    public String decrypt(String input)
    {
        CaesarCipher cc = new CaesarCipher(26 - mainKey); 
        
        return cc.encrypt(input);
    }
}
