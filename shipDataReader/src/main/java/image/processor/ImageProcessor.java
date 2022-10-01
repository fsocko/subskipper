package image.processor;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageProcessor {

	// Returns "nodata" image if image file not found.
	BufferedImage readDdsFileToBufferedImage(String file);

	String bufferedImageToB64Png(BufferedImage image);

	BufferedImage b64PngToBufferedImage(String b64Image);

	String ddsPathToB64Image(String ddsPath);
}
