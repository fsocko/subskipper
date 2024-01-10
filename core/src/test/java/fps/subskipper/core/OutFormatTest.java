package fps.subskipper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

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
        String result = outFormat.builder().formatValueToDegree(271d);
        Assertions.assertEquals("271°", result);

        result = outFormat.builder().formatValueToDegree(370d);
        Assertions.assertEquals("010°", result);

        result = outFormat.builder().formatValueToDegree(-20d);
        Assertions.assertEquals("340°", result);

        result = outFormat.builder().formatValueToDegree(-400d);
        Assertions.assertEquals("040°", result);

    }

    @Test
    void testHourOut() {
        String result = outFormat.formatValueToHours(2456565747L);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}