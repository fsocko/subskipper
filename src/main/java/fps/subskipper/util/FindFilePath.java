package fps.subskipper.util;

import java.io.File;

public class FindFilePath {

    public File found;

    public String findFilepathRecursively (String startDirectory, String filename){
        return findFilepathRecursively(new File[]{new File(startDirectory)}, filename);
    }

    public String findFilepathRecursively (File[]startDirectory, String filename){
        for (File file : startDirectory) {
            if (file.isFile() && file.getName().contains(filename)) {
               // System.out.println("SUCCESS!:" + file.getAbsolutePath());
                found = file;
                return file.getAbsolutePath();
            }
            else if(file.isDirectory()) {
               // System.out.println("DIRECTORY:" + file.getAbsolutePath());
                findFilepathRecursively(file.listFiles(), filename);
            }
        }
        return null;
    }
}
