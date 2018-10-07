package fps.subskipper.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindFilePathTest {

    @Test
        public void findFilepathRecursivelyTest (){
            FindFilePath findFile = new FindFilePath();
            findFile.findFile(System.getProperty("user.dir"), "FindFilePathTest.java");
            assertEquals("D:\\002-git\\SubSkipper\\src\\test\\java\\fps\\subskipper\\util\\FindFilePathTest.java",
                    findFile.found.getAbsolutePath());

        findFile.findFile("D:\\002-git\\SubSkipper", "util");
        assertEquals("D:\\002-git\\SubSkipper\\target\\test-classes\\fps\\subskipper\\util",
                findFile.found.getAbsolutePath());

    }
}


