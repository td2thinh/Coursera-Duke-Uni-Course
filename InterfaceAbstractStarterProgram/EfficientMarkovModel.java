import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int markovNum;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        markovNum = N;
        map = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    private void buildMap() {
        for (int i = 0; i < myText.length() - (markovNum - 1); i++) {

            String current = myText.substring(i, i + markovNum);
            // System.out.println(current);
            String follow = "";
            if (i + markovNum < myText.length())
                follow = myText.substring(i + markovNum, i + markovNum + 1);

            if (map.containsKey(current)) {
                map.get(current).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(current, list);
            }
        }

    }

    public void printHashMapInfo() {
        System.out.println(map);
        System.out.println("The size of the map is " + map.size());
        int maxSize = 0;
        String maxkey = "";
        for (String key : map.keySet()) {
            if (map.get(key).size() > maxSize) {
                maxSize = map.get(key).size();
                maxkey = key;
            }
        }
        System.out.println(map.get(maxkey));
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        follows = map.get(key);
        return follows;
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String current = myText.substring(index, index + markovNum);
        sb.append(current);
        for (int k = 0; k < numChars - markovNum; k++) {
            ArrayList<String> follows = getFollows(current);
            if (follows == null) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            current = current.substring(1) + next;
        }
        return sb.toString();
    }

    public String toString() {
        return "EfficientMarkovModel  of order " + markovNum;
    }
}