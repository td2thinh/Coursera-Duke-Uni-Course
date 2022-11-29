
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch)
    {
        String vowels = "aeiou";
        if (vowels.indexOf(Character.toLowerCase(ch)) == -1)
        {
            return false;
        }
        return true;
    }
    
    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder phraseSB = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++)
        {
            if (isVowel(phraseSB.charAt(i)))
            {
                phraseSB.setCharAt(i, ch);
            }
        }
        
        String result = new String(phraseSB);
        
        return result;
    }
    
    public String emphasize (String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++)
        {
            if (sb.charAt(i) == ch)
            {
                if (i % 2 == 0)
                {
                    sb.setCharAt(i, '*');
                }
                else
                {
                    sb.setCharAt(i, '+');
                }
            }
        }
        
        return new String(sb);
    }
}
