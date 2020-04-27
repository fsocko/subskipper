package fps.subskipper.recognitionManualParser.util;

import java.io.File;
import java.util.Objects;

public class Find {

    public static File findFile(final String startDirectory, final String filename) {
        File[] startDirectoryArray = new File[]{new File(startDirectory)};
        return findFile(startDirectoryArray, filename);
    }

    /**
     * Recursive method.
     * @param startDirectory
     * @param filename
     * @return Array of Files.
     */
    private static File findFile(final File[] startDirectory, final String filename) {
        for (final File file : startDirectory) {
            if (file.getName().contains(filename)) {
                return file;
            } else if (!file.getName().equals(filename) && file.isDirectory()) {
                final File foundFile = findFile(file.listFiles(), filename);
                if (Objects.nonNull(foundFile)) {
                    return foundFile;
                }
            }
        }
        return null;
    }

}