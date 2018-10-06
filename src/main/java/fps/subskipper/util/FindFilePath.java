package fps.subskipper.util;

import java.io.File;

public class FindFilePath {

    public static void main(String[] args) {
        System.out.println("RETURNED AS EXPECTED: " +
                new FindFilePath().findFilepathRecursively (System.getProperty("user.dir"), "FindFilePath.java"));
    }

        public String findFilepathRecursively (String startDirectory, String filename){
            return findFilepathRecursively(new File[]{new File(startDirectory)}, filename);
        }

        public String findFilepathRecursively (File[]startDirectory, String filename){
            for (File file : startDirectory) {
                if (file.getName().contains(filename)) {
                    System.out.println("SUCCESS!:" + file.getAbsolutePath());
                    return file.getAbsolutePath();
                }
                else if (file.isDirectory()) {
                    findFilepathRecursively(file.listFiles(), filename);
                }
            }
            return null;
        }
    }
