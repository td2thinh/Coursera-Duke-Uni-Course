import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        // REPLACE WITH YOUR CODE
        StringBuilder result = new StringBuilder("");
        StringBuilder sb = new StringBuilder(message);
        for (int i = whichSlice; i < sb.length(); i += totalSlices) {
            result.append(sb.charAt(i));
        }
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        // WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        String slice = "";
        for (int i = 0; i < klength; i++) {
            slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }

        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] words = message.split("\\W+");
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon) {
        String result = "";
        int maxCount = 0;
        int keyLength = 0;
        for (int i = 1; i <= encrypted.length(); i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount) {
                result = decrypted;
                maxCount = count;
                keyLength = i;
            }
        }
        System.out.println("Key length: " + keyLength);
        System.out.println("Max count: " + maxCount);
        return result;
    }

    public void breakVigenere() {
        // WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String input = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] langs = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish" };
        for (String lang : langs) {
            FileResource fr2 = new FileResource("dictionaries/" + lang);
            HashSet<String> dictionary = readDictionary(fr2);
            languages.put(lang, dictionary);
        }
        breakForAllLanguages(input, languages);
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            if (word.length() > 0) {
                word = word.toLowerCase();
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (map.containsKey(c)) {
                        map.put(c, map.get(c) + 1);
                    } else {
                        map.put(c, 1);
                    }
                }
            }
        }
        int max = 0;
        char result = ' ';
        for (char c : map.keySet()) {
            if (map.get(c) > max) {
                max = map.get(c);
                result = c;
            }
        }
        return result;
    }

    public void breakForAllLanguages(String encryted, HashMap<String, HashSet<String>> languages) {
        String msg = "";
        String trueLan = "";
        int highest = 0;

        for (String language : languages.keySet()) {
            System.out.println("Language: " + language);
            HashSet<String> dictionary = languages.get(language);
            char mostCommon = mostCommonCharIn(dictionary);
            System.out.println("Most common char: " + mostCommon);
            String decrypted = breakForLanguage(encryted, dictionary, mostCommon);
            int counter = countWords(decrypted, languages.get(language));
            if (counter > highest) {
                msg = decrypted;
                highest = counter;
                trueLan = language;
            }
        }
        System.out.println(msg);
        System.out.println("The most possible language: " + trueLan);
    }
}
