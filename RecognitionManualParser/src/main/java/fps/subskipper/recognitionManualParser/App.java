package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;

import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.*;

/**
 * Simple main application
 *
 * Params:
 *
 */
@CommandLine.Command(description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
        name = "checksum", mixinStandardHelpOptions = true, version = "checksum 3.0")
@Slf4j
public class App implements Callable<Integer> {

    public App() throws IOException {
    }

    RecognitionManualMainImpl recognitionManualMain = new RecognitionManualMainImpl();
    final Ships shipList = recognitionManualMain.loadShipsToMemory();

    @CommandLine.Option(names = {"-l", "--long"}, description = "Long manual (default is short)")
    private boolean isLongManual = true;

    @CommandLine.Option(names = {"-i", "--imperial"}, description = "Converts measurements to imperial.")
    private boolean isImperial = false;

    @CommandLine.Option(names = {"-a", "--aob"}, description = "Prints aspects at common AOB values (Short manual only)")
    private boolean isAOB;

    @CommandLine.Parameters(index = "0", description = "Recognition Manual Target path. Filename is generated.")
    private File targetPath;

    private String generateManualName(){

        StringBuffer manualType = new StringBuffer("recognitionManual_");

        if (!isLongManual) {
            manualType.append("short_");
        } else {
            manualType.append("long_");
        }
        if (isImperial) {
            manualType.append("imperial_");
        } else {
            manualType.append("metric_");
        }

        if(!isLongManual && isAOB){
            manualType.append("withAobTable");
        }

        manualType.append(".html");

        return manualType.toString();
    }


    @Override
    public Integer call() throws Exception {

        if(targetPath == null){
            targetPath = new File(ConstantsRecog.RECOGNITION_MANUALS_TARGET_PATH);
        }

        String pathWithFile = targetPath + File.separator + generateManualName();

        if(!isLongManual) {
            publishShortRecognitionManual(pathWithFile, isImperial, isAOB);
        } else if(isLongManual){
            publishLongRecognitionManual(pathWithFile, isImperial);
        } else{
            System.err.println("ERROR: Failed to publish recognition manual: " + pathWithFile);
            return new Integer(1);
        }
        System.out.println("SUCCESS: Published recognition manual: " + pathWithFile);
        return new Integer(0);
    }

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public void publishShortRecognitionManual (String manualTargetPath, Boolean isImperial, Boolean isAobTable) throws IOException {
        recognitionManualMain.publishRecognitionManualShort(shipList, ConstantsRecog.RESOURCES_PATH + "\\" + ConstantsRecog.RECOGNITION_MANUAL_SHORT_FILENAME, false, false);
    }

    public void publishLongRecognitionManual (String manualTargetPath, Boolean isImperial) throws IOException {
        recognitionManualMain.publishRecognitionManualLong(shipList, ConstantsRecog.RESOURCES_PATH + "\\" + ConstantsRecog.RECOGNITION_MANUAL_LONG_FILENAME, isImperial);
    }
}


