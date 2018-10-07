package fps.subskipper.util;

import java.io.File;

public class FindFilePath {

    public File found;

    public String findFile(String startDirectory, String filename){
        return findFile(new File[]{new File(startDirectory)}, filename);
    }

    public String findFile(File[] startDirectory, String filename){
        for (File file : startDirectory) {
            if (file.getName().equals(filename)) {
                found = file;
                return file.getAbsolutePath();
            }
            else if(!file.getName().equals(filename) && file.isDirectory()) {

                findFile(file.listFiles(), filename);
            }
        }
        return null;
    }
}
