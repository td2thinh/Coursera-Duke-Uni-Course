import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1) {
                break;
            }
            if (start + key.length() >= myText.length()) {
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + key.length();
        }
        return follows;
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++) {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
}