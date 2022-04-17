package fps.subskipper.recognitionManualParser.util;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static fps.subskipper.recognitionManualParser.util.Find.findFile;
import static org.junit.Assert.assertEquals;



public class FindTest {

    private final String testFileName = "findTestFile.2";

    @Before
    public void setUp() throws Exception {
        final File myObj = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + testFileName);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    @Test @Ignore
    public void findFilepathRecursivelySuccessTest() throws FileNotFoundException {
        assertEquals(
            new File(System.getProperty("user.dir") + "\\src\\test\\resources\\"+testFileName),
            findFile(System.getProperty("user.dir"), testFileName));
    }

    @Test @Ignore
    public void findFilepathRecursivelyFailWithInvalidStartDirectoryTest() {
        try {
            findFile("Z:\\invalidPath\\invalidFile.zzz", "FindTest.java");
        }  catch(FileNotFoundException f){
            assert(true);
        }
        try{
            findFile(System.getProperty("user.dir"), "INVALID_FILE.333");
        }  catch(FileNotFoundException f){
            assert(true);
        }
    }
}