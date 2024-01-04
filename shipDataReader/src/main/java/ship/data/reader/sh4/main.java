package ship.data.reader.sh4;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.Base64;

import javax.imageio.ImageIO;

@Deprecated
public class main {

	public static void main(String[] args) throws IOException {
		
//		 String path = "D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\JPGunBoat01\\JPGunBoat01_sil.dds";
//		 System.out.println(imgToBase64String(createDDSLabelO(path), "png"));
		

	}

    private static BufferedImage createDDSLabelO(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte [] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        int [] pixels = DDSReader.read(buffer, DDSReader.ARGB, 0);
        int width = DDSReader.getWidth(buffer);
        int height = DDSReader.getHeight(buffer);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);    
        return image;
    }
    
    
    public static String imgToBase64String(final BufferedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (final OutputStream b64os = Base64.getEncoder().wrap(os)) {
            ImageIO.write(img, formatName, b64os);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        return os.toString();
    }
    
   
	
	
}