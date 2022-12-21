import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows.size());
    }
}
