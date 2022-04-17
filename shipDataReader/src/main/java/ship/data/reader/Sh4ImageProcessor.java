package ship.data.reader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;


public class Sh4ImageProcessor {

	

	public BufferedImage read(File file, int imageIndex) throws IOException{
	    Iterator<ImageReader> iterator = ImageIO.getImageReadersBySuffix("dds");
	    if (iterator.hasNext()){
	        ImageReader imageReader = iterator.next();
	        imageReader.setInput(new FileImageInputStream(file));
	        int max = imageReader.getNumImages(true);
	        if (imageIndex < max) return imageReader.read(imageIndex);
	    }
	    return null;
	}
	
	
	public static String encodeToString(BufferedImage image, String type) {
		
	    String imageString = null;
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();

	    try {
	        ImageIO.write(image, type, bos);
	        byte[] imageBytes = bos.toByteArray();

	        Base64.Encoder encoder = Base64.getEncoder();
	        imageString = encoder.encodeToString(imageBytes);

	        bos.close();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return imageString;
	}
	
	
	public static BufferedImage decodeToImage(String imageString) {
	    BufferedImage image = null;
	    byte[] imageByte;
	    try {
	        Base64.Decoder decoder = Base64.getDecoder();
	        imageByte = decoder.decode(imageString);
	        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	        image = ImageIO.read(bis);
	        bis.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return image;
	}

}
