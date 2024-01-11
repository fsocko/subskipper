package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.CoreApp;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static fps.subskipper.util.Constants.*;

//TODO: parameterise SCAF path and/or UBOOT path

/**
 * Simple main application
 *
 * Params:
 *
 */
@Slf4j
public class App extends CoreApp {

    final RecognitionManualMainImpl recognitionManualMain = new RecognitionManualMainImpl();



    private static char manualType = 's'; //l for long, s for short, b for both.
    private static boolean isImperial = false;
    private static boolean isAOB = false;
    private static File recognitionManualTargetPath;

    public App() throws IOException {
    }




    public static void main(String... args) {

        try {
            RecognitionManualMainImpl recognitionManualMain = new RecognitionManualMainImpl();
            System.out.println(SCAF_ROOT_PATH);
            final Ships shipList = recognitionManualMain.loadShipsToMemory(new File(SCAF_ROOT_PATH));


            String manualTargetPath = RECOGNITION_MANUAL_TARGET_PATH + SFS + generateManualName();

            switch(manualType){
                case 'l':
                case 'L':
                    recognitionManualMain.publishRecognitionManualLong(shipList, manualTargetPath, isImperial);
                    break;
                case 's':
                case 'S':
                    recognitionManualMain.publishRecognitionManualShort(shipList, manualTargetPath, isImperial, false);
                    break;
                default:
                    recognitionManualMain.publishRecognitionManualShort(shipList, manualTargetPath, isImperial, false);
                    recognitionManualMain.publishRecognitionManualLong(shipList, manualTargetPath, isImperial);
                break;
            }
            System.out.println("SUCCESS: Published recognition manual: " + manualTargetPath);

        } catch(Exception e){
            log.error("Threw Exception when generating recognition manual:", e);
        }
    }

    private static String generateManualName() {

        StringBuffer manualName = new StringBuffer("recognitionManual_");

        if (Character.toLowerCase(manualType) == 's') {
            manualName.append("short_");
        } else {
            manualName.append("long_");
        }
        if (isImperial) {
            manualName.append("imperial");
        } else {
            manualName.append("metric");
        }
        if(Character.toLowerCase(manualType) == 'l' && isAOB){
            manualName.append("_withAobTable");
        }
        manualName.append(".html");
        return manualName.toString();
    }
}


