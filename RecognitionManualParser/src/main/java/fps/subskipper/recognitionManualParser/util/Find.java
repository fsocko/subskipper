package fps.subskipper.recognitionManualParser.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Find {

    public static File findFile(final String startDirectory, final String targetFilename) throws FileNotFoundException {
        return findFile(new File(startDirectory), targetFilename);
    }

    public static File findFile(final File startDirectory, final String targetFilename) throws FileNotFoundException {
        File[] startDirectoryArray = new File[]{startDirectory};
        File returnedFile = findFile(startDirectoryArray, targetFilename);
        if(returnedFile != null){
            return returnedFile;
        } else{
            throw new FileNotFoundException("The file: " + targetFilename + "was not found in start directory: " + startDirectory);
        }
    }

    private static File findFile(final File[] startDirectory, final String targetFilename) {
        for (final File file : startDirectory) {
            if (file.getName().contains(targetFilename)) {
                return file;
            } else if (!file.getName().equals(targetFilename) && file.isDirectory()) {
                final File foundFile = findFile(file.listFiles(), targetFilename);
                if (Objects.nonNull(foundFile)) {
                    return foundFile;
                }
            }
        }
        return null;
    }
}