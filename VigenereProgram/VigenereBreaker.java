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

    public void breakVigenere() {
        // WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String input = fr.asString();
        int[] key = tryKeyLength(input, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(input);
        System.out.println(decrypted);
    }

}
