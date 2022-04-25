package image.processor;

import static ship.data.reader.DataReaderConstants.NO_DATA_IMAGE_B64;
import static ship.data.reader.DataReaderConstants.PNG_FORMAT_CONSTANT;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import lombok.extern.slf4j.Slf4j;
import ship.data.reader.DDSReader;
import ship.data.reader.DataReaderConstants;

@Slf4j
public class ImageProcessorImpl implements ImageProcessor {

	@Override
	public BufferedImage readDdsFileToBufferedImage(String path) {
		
		if (isFilenameValid(path)) {
			BufferedImage image = createBufferedImageFromDdsFile(path);
			if (image != null) {
				return image;
			}
		}
		return base64StringToBufferedImage(NO_DATA_IMAGE_B64);
	}

	@Override
	public String bufferedImageToB64Png(BufferedImage image) {
		if (image != null) {
			return bufferedImageToBase64String(image, PNG_FORMAT_CONSTANT);
		} else {
			return DataReaderConstants.NO_DATA_IMAGE_B64;
		}

	}

	@Override
	public BufferedImage b64PngToBufferedImage(String b64Image) {
		if (b64Image != null) {
			return base64StringToBufferedImage(b64Image);
		} else {
			return base64StringToBufferedImage(NO_DATA_IMAGE_B64);
		}
	}

	private static BufferedImage createBufferedImageFromDdsFile(String path) {
		try (FileInputStream fis = new FileInputStream(new File(path))) {
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			if (buffer.length > 0) {
				int[] pixels = DDSReader.read(buffer, DDSReader.ARGB, 0);
				int width = DDSReader.getWidth(buffer);
				int height = DDSReader.getHeight(buffer);
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				image.setRGB(0, 0, width, height, pixels, 0, width);
				return image;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Failed to read DDS image file:", e);
			return null;
		}
	}

	private static String bufferedImageToBase64String(final BufferedImage img, final String formatName) {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try (final OutputStream b64os = Base64.getEncoder().wrap(os)) {
			ImageIO.write(img, formatName, b64os);
		} catch (Exception e) {
			log.error("Failed to write B64 text from buffered image:", e);
			return null;
		}
		return os.toString();
	}

	private static BufferedImage base64StringToBufferedImage(final String b64Image) {
		try {
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(b64Image);
			return ImageIO.read(new ByteArrayInputStream(imageBytes));
		} catch (Exception e) {
			log.error("Failed to write BufferedImage from B64 text:", e);
			return null;
		}
	}
	
	//TODO: Move to util?
	private boolean isFilenameValid(String file) {
		  File f = new File(file);
		  try {
		    f.getCanonicalPath();
		    return true;
		  } catch (IOException e) {
		    return false;
		  }
		}

}
