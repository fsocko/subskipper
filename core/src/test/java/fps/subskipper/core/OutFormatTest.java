package fps.subskipper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static fps.subskipper.util.Constants.UNIT_FOOT;
import static org.mockito.Mockito.*;

class OutFormatTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    OutFormat outFormat;

    @Test
    void testDefaultFormat() {
        String result = outFormat.builder().value(111.55555d).build();
        Assertions.assertEquals("112", result);
    }

    @Test
    void testStringValueFormat() {
        String result = outFormat.builder().value("111.55555").build();
        Assertions.assertEquals("112", result);

        String unitResult = outFormat.builder().value("value|").addUnit("|unitTest").build();
        Assertions.assertEquals("value| |unitTest", unitResult);
    }

    @Test
    void testZeroDP() {
        String result = outFormat.builder().value(111.55555d).zeroDP().build();
        Assertions.assertEquals("112", result);
    }

    @Test
    void testTwoDP() {
        String result = outFormat.builder().value(111.55555d).twoDP().build();
        Assertions.assertEquals("111.56", result);
    }

    @Test
    void testFourDP() {
        String result = outFormat.builder().value(111.55555d).fourDP().build();
        Assertions.assertEquals("111.5556", result);
    }

    @Test
    void testFormatNumberToDegree() {
        String result = outFormat.builder(271d).toDegrees().build();
        Assertions.assertEquals("271°", result);

        result = outFormat.builder(370d).toDegrees().build();
        Assertions.assertEquals("010°", result);

        result = outFormat.builder(-20d).toDegrees().build();
        Assertions.assertEquals("340°", result);

        result = outFormat.builder(-400d).toDegrees().build();
        Assertions.assertEquals("040°", result);
    }

    @Test
    void testHourOut() {
        String result = outFormat.builder().formatValueToHours(1704933986L);
        Assertions.assertEquals("06:35:33", result);
    }
    @Test
    void testAddUnit() {
        String result = outFormat.builder().value(111.55555d).twoDP().addUnit(UNIT_FOOT).build();
        Assertions.assertEquals("111.56 ft", result);
    }

}