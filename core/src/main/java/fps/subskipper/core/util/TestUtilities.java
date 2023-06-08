package fps.subskipper.core.util;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class TestUtilities {

    public static File getFileFromResources(String filename) throws URISyntaxException {
        URL res = TestUtilities.class.getClassLoader().getResource(filename);
        File file = Paths.get(res.toURI()).toFile();
        return file;
    }

}
