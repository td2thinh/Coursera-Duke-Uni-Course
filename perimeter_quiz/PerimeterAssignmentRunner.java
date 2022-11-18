import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numOfPoints = 0;
        for (Point point : s.getPoints()){
            numOfPoints += 1;
        }
        return numOfPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perim = getPerimeter(s);
        double result = perim / getNumPoints(s);
        return result;
    }

    public double getLargestSide(Shape s) {
        // Put code here
         // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        double largestSide = 0;
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            if (currDist >= largestSide){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for (Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if (currX >= largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPrim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length >= largestPrim){
                largestPrim = length;
            }
        }
        return largestPrim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largestPrim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length >= largestPrim){
                largestPrim = length;
            }
            temp = f;
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numOfPoints = getNumPoints(s);
        System.out.println("shape has " + numOfPoints + " points.");
        double averageSideLength = getAverageLength(s);
        System.out.println("Average Side Length: " + averageSideLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side: " + largestSide);
        double getLargestX = getLargestX(s);
        System.out.println("Largest X: " + getLargestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File with largest perimeter " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
