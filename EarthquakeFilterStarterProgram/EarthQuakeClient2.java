import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        Filter filterMagnitude = new MagnitudeFilter(3.49, 4.51);
        Filter filterDepth = new DepthFilter(-55000.1, -19999.0);
        // Filter filterDist = new DistanceFilter(new Location(35.42, 139.43),
        // 10000000.0);
        // Filter filterPhrase = new PhraseFilter("any", "Japan");

        ArrayList<QuakeEntry> m7 = filter(list, filterMagnitude);
        m7 = filter(m7, filterDepth);

        for (QuakeEntry qe : m7) {
            System.out.println(qe);
        }
        System.out.println("Found " + m7.size() + " quakes that match that criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.99, 4.1));
        maf.addFilter(new DepthFilter(-180000.1, -29999.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        int count = 0;
        for (QuakeEntry qe : list) {
            if (maf.satisfies(qe)) {
                System.out.println(qe);
                count += 1;
            }
        }
        System.out.println("Found " + count + " quakes that match that criteria");

    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(-0.01, 5.1));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        int count = 0;
        for (QuakeEntry qe : list) {
            if (maf.satisfies(qe)) {
                System.out.println(qe);
                count += 1;
            }
        }
        System.out.println("Found " + count + " quakes that match that criteria");

    }

}
