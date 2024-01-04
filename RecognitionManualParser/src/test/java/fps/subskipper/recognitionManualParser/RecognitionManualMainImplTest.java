package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecognitionManualMainImplTest {
    RecognitionManualMainImpl recognitionManualMainImpl = new RecognitionManualMainImpl();

    @Test
    public void testPublishRecognitionManualShort() throws Exception {
        recognitionManualMainImpl.publishRecognitionManualShort(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), "filename", true, true);
    }

    @Test
    public void testPublishRecognitionManualLong() throws Exception {
        recognitionManualMainImpl.publishRecognitionManualLong(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), "filename", true);
    }
}
