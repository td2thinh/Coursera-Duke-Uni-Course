import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    public void printMap() {
        buildMap();
        System.out.println("the total key number is: " + map.size());
        int max = 0;
        for (WordGram s : map.keySet()) {
            if (map.get(s).size() > max) {
                max = map.get(s).size();
            }
        }
        System.out.println("The maximum number of keys is: " + max);
    }

    public void buildMap() {
        map = new HashMap<WordGram, ArrayList<String>>();
        for (int k = 0; k < myText.length - myOrder; k++) {
            WordGram kGram = new WordGram(myText, k, myOrder);
            String follow = myText[k + kGram.length()];
            if (map.containsKey(kGram)) {
                map.get(kGram).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(kGram, list);
            }
        }
        WordGram kGram = new WordGram(myText, myText.length - myOrder, myOrder);
        if (!(map.containsKey(kGram))) {
            ArrayList<String> list = new ArrayList<String>();
            map.put(kGram, list);
        }
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i <= words.length - target.length(); i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }

}