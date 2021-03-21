package fps.subskipper.recognitionManualParser.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
@Slf4j
public class ConstantsRecogTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setConstants(){

        /*
           public static String RESOURCES_PATH;
    public static String SHIPLIST_PATH;
    public static String SCAF_ROOT_PATH;
    public static String SCAF_NAMES_PATH;
    public static String RECOGNITION_MANUALS_TARGET_PATH;
         */

        ConstantsRecog constants = new ConstantsRecog();
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources", constants.RESOURCES_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\shipList.xml", constants.SHIPLIST_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\SCAF for TMO_2", constants.SCAF_ROOT_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\SCAF for TMO_2\\Data\\Roster\\Names.cfg", constants.SCAF_NAMES_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\target\\recognitionManuals", constants.RECOGNITION_MANUALS_TARGET_PATH);

        log.info(" \n{}\n {}\n {}\n {}\n {}\n", constants.RESOURCES_PATH, constants.SHIPLIST_PATH, constants.SCAF_ROOT_PATH, constants.SCAF_NAMES_PATH, constants.RECOGNITION_MANUALS_TARGET_PATH);

        File file = new File( constants.RESOURCES_PATH);
        assertTrue(file.exists());
        file = new File(constants.SHIPLIST_PATH);
        assertTrue(file.exists());
        file = new File(constants.SCAF_ROOT_PATH);
        assertTrue(file.exists());
        file = new File(constants.SCAF_NAMES_PATH);
        assertTrue(file.exists());
        file = new File(constants.RECOGNITION_MANUALS_TARGET_PATH);
    }
}
