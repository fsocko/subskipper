package fps.subskipper.recognitionManualParser.util;

import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.RECOGNITION_MANUALS_TARGET_PATH;
import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.RESOURCES_PATH;
import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.SCAF_NAMES_PATH;
import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.SCAF_ROOT_PATH;
import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.SHIPLIST_PATH;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConstantsRecogTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setConstants(){

        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources", RESOURCES_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\shipList.xml", SHIPLIST_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\SCAF for TMO_2", SCAF_ROOT_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\src\\main\\resources\\SCAF for TMO_2\\Data\\Roster\\Names.cfg", SCAF_NAMES_PATH);
        Assert.assertEquals("D:\\002-git\\SubSkipper\\RecognitionManualParser\\target\\recognitionManuals", RECOGNITION_MANUALS_TARGET_PATH);

        //log.info(" \n{}\n {}\n {}\n {}\n {}\n", RESOURCES_PATH, SHIPLIST_PATH, SCAF_ROOT_PATH, SCAF_NAMES_PATH, RECOGNITION_MANUALS_TARGET_PATH);

    }
}
