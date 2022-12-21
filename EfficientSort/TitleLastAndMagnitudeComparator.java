import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        int lastSpace1 = title1.lastIndexOf(" ");
        int lastSpace2 = title2.lastIndexOf(" ");
        String lastWord1 = title1.substring(lastSpace1 + 1);
        String lastWord2 = title2.substring(lastSpace2 + 1);
        int compareLastWord = lastWord1.compareTo(lastWord2);
        if (compareLastWord == 0) {
            double mag1 = qe1.getMagnitude();
            double mag2 = qe2.getMagnitude();
            return Double.compare(mag1, mag2);
        }
        return compareLastWord;
    }
}
