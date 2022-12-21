import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        int compareTitle = title1.compareTo(title2);
        if (compareTitle == 0) {
            double depth1 = qe1.getDepth();
            double depth2 = qe2.getDepth();
            return Double.compare(depth1, depth2);
        }
        return compareTitle;
    }
}
