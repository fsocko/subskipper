package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.CoreApp;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
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

    private static char manualType = 's'; //l for long, s for short, b for both.
    private static boolean isImperial = false;
    private static boolean isAOB = false;
    private static File recognitionManualTargetPath;

    public App() throws IOException {
    }


    public static void main(String... args) {

        try {
            selectAndGenerate( ScafOrUboot.UBOOT );

        } catch(Exception e){
            log.error("Threw Exception when generating recognition manual:", e);
        }
    }

    private static void selectAndGenerate(ScafOrUboot scafOrUboot ) throws FileNotFoundException, IOException {
        
        String manualTargetPath = RECOGNITION_MANUAL_TARGET_PATH + SFS + scafOrUboot.getName() + generateManualName(scafOrUboot);

        RecognitionManualMainInterface recognitionManualMain;
        Ships shipList;

        if(scafOrUboot.equals(ScafOrUboot.SCAF)) {
            recognitionManualMain = new RecognitionManualScafImpl();
            shipList = recognitionManualMain.loadShipsToMemory(new File(SCAF_ROOT_PATH));
            recognitionManualMain.publishRecognitionManualLong(shipList, manualTargetPath, isImperial);
            recognitionManualMain.publishRecognitionManualShort(shipList, manualTargetPath, isImperial, false);

        } else if(scafOrUboot.equals(ScafOrUboot.UBOOT)) {
            recognitionManualMain = new RecognitionManualUbootImpl();
            shipList = recognitionManualMain.loadShipsToMemory();
            recognitionManualMain.publishRecognitionManualShort(shipList, manualTargetPath, isImperial, false);
        //Do both
        } else {

        }

        System.out.println("SUCCESS: Published recognition manual for: " + scafOrUboot.toString() + " Path: " + manualTargetPath);
    }


    private static String generateManualName(ScafOrUboot scafOrUboot) {

        StringBuffer manualName = new StringBuffer("recognitionManual_");
        manualName.append(scafOrUboot.getName() + "_");

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


