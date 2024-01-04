package ship.data.reader.sh4.image.processor;

import java.awt.image.BufferedImage;

public interface IImageProcessor {
	
	//Returns "nodata" image if image file not found.
	BufferedImage readDdsFileToBufferedImage(String file);
	
	String bufferedImageToB64Png(BufferedImage image);

	String readDdsFileToB64Png(String file);
	
	BufferedImage b64PngToBufferedImage(String b64Image);
	
}
