import edu.duke.*;
import java.util.*;
import java.io.File;

/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles()
    {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f)
    {
        FileResource resource = new FileResource(f);
        for (String word : resource.words())
        {
            if (!map.containsKey(word))
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                map.put(word, list);
            }
            else
            {
                ArrayList<String> list = map.get(word);
                if (!list.contains(f.getName()))
                {
                    list.add(f.getName());
                }
            }
        }
    }

    public void buildWordFileMap ()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }

    public ArrayList<String> wordsInNumFiles (int number)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String word : map.keySet())
        {
            if (map.get(word).size() == number)
            {
                list.add(word);
            }
        }
        return list;
    }

    public void printFilesIn(String word)
    {
        for (String w : map.keySet())
        {
            if (w.equals(word))
            {
                System.out.println(map.get(w));
            }
        }
    }

    public void tester()
    {
        buildWordFileMap();
        System.out.println("Number of keys: " + map.size());
        int max = 0;
        // for (String word : map.keySet())
        // {
        //     if (map.get(word).size() > max)
        //     {
        //         max = map.get(word).size();
        //     }
        // }
        System.out.println("Maximum number of files any word appears in: " + max);
        ArrayList<String> list = wordsInNumFiles(4);
        System.out.println("Words that appear in 4 files: " + list.size());
        // for (String word : list)
        // {
        //     System.out.println(word);
        // }
        System.out.println("Files for word \"laid\": ");
        printFilesIn("laid");
    }
}
