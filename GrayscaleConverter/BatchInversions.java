
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
	//for each pixel in outImage
	for (Pixel pixel: outImage.pixels()) {
	   //look at the corresponding pixel in inImage
	   Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
	   //Inverting pixels
	   pixel.setRed(255 - inPixel.getRed());
	   pixel.setGreen(255 - inPixel.getGreen());
	   pixel.setBlue(255 - inPixel.getBlue());
	}
	//outImage is your answer
	return outImage;
    }
    

    public void selectAndConvert () {
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource inImage = new ImageResource(f);
		ImageResource inverted = makeInversion(inImage);
		String fname = inImage.getFileName();
		String newName = "inverted_" + fname;
		inverted.setFileName(newName);
		inverted.save();
	}
}    
}
