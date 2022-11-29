import edu.duke.*;
import java.util.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay()
    {
        myCharacters = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void update(String person)
    {
        int index = myCharacters.indexOf(person);
        if (index == -1)
        {
            myCharacters.add(person);
            myFreqs.add(1);
        }
        else
        {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);
        }
    }

    public void findAllCharacters()
    {
        myCharacters.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines())
        {
            int index = line.indexOf(".");
            if (index != -1)
            {
                String person = line.substring(0, index);
                update(person);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2)
    {
        for (int i = 0; i < myCharacters.size(); i++)
        {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2)
            {
                System.out.println(myCharacters.get(i) + "\t" + myFreqs.get(i));
            }
        }

    }
    public void tester()
    {
        findAllCharacters();
        for (int i = 0; i < myCharacters.size(); i++)
        {
            if (myFreqs.get(i) > 2)
            {
                System.out.println(myCharacters.get(i) + "\t" + myFreqs.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }


}
