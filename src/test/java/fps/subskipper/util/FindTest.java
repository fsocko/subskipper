package fps.subskipper.util;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FindTest {

    @Test
    public void findFilepathRecursivelySuccessTest() {
        Find findFile = new Find();

        //Pass String path as 1st arg.
        assertEquals(new File("D:\\002-git\\SubSkipper\\src\\test\\java\\fps\\subskipper\\util\\FindTest.java"),
                findFile.findFile(System.getProperty("user.dir"), "FindTest.java"));

        //Pass array of files as 1st arg.
        assertEquals(new File("D:\\002-git\\SubSkipper\\src\\test\\java\\fps\\subskipper\\util\\FindTest.java"),
                findFile.findFile(new File[]{new File(System.getProperty("user.dir"))}, "FindTest.java"));

        //Pass directory in query parameter.
        assertEquals(new File("D:\\002-git\\SubSkipper\\src\\main\\java\\fps\\subskipper\\util"),
                findFile.findFile(System.getProperty("user.dir"), "util"));
    }

    @Test
    public void findFilepathRecursivelyFailWithInvalidStartDirectoryTest() {
        Find findFile = new Find();
        assertNull(findFile.findFile("Z:\\invalidPath\\invalidFile.zzz", "FindTest.java"));
    }
}


