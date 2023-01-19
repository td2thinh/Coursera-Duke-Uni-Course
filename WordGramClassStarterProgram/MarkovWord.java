import java.util.*;
import edu.duke.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString()).append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int index = start; index < words.length - target.length(); index++) {
            if (words[index].equals(target.wordAt(0))) {
                boolean targetFound = true;
                for (int k = 1; k < target.length(); k++) {
                    if (!words[index + k].equals(target.wordAt(k))) {
                        targetFound = false;
                        break;
                    }
                }
                if (targetFound) {
                    return index;
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int counter = 0;
        while (counter < myText.length - kGram.length()) {
            int foundKey = indexOf(myText, kGram, counter);
            if (foundKey == -1) {
                break;
            }
            if (foundKey + kGram.length() >= myText.length - 1) {
                break;
            }
            String next = myText[foundKey + kGram.length()];
            follows.add(next);
            counter = foundKey + kGram.length();
        }
        return follows;
    }

}
