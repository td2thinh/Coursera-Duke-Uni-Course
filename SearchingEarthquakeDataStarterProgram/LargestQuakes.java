import java.util.*;
import edu.duke.*;

/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes() {
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        // System.out.println("Largest quake: " + list.get(indexOfLargest(list)));
        ArrayList<QuakeEntry> largest = getLargest(list, 50);
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
        System.out.println("Largest earthquakes found: " + largest.size());
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int maxIndex = 0;
        for (int i = 0; i < quakeData.size(); i++) {
            QuakeEntry qe = quakeData.get(i);
            if (qe.getMagnitude() > quakeData.get(maxIndex).getMagnitude()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        if (quakeData.size() < howMany) {
            ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>(quakeData);
            return ret;
        }
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
}
