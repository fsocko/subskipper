package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;

/**
 * Simple main application
 *
 * Params:
 *
 */
@CommandLine.Command(description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
        name = "checksum", mixinStandardHelpOptions = true, version = "checksum 3.0")

public class App implements Callable<Integer> {

    public App() throws IOException {
    }

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    RecognitionManualMainImpl recognitionManualMain = new RecognitionManualMainImpl();
    final Ships shipList = recognitionManualMain.loadShipsToMemory();

    @CommandLine.Option(names = {"-l", "--long"}, description = "Long manual (default is short)")
    private boolean isLongManual = true;

    @CommandLine.Option(names = {"-i", "--imperial"}, description = "Converts measurements to imperial.")
    private boolean isImperial;

    @CommandLine.Option(names = {"-a", "--aob"}, description = "Prints aspects at common AOB values (Short manual only)")
    private boolean isAOB;

    @CommandLine.Parameters(index = "0", description = "Recognition Manual Target path. Filename is generated.")
    private File targetPath;

    private String generateManualName(){

        StringBuffer manualType = new StringBuffer("recognitionManual_");

        if (isLongManual == false) {
            manualType.append("short_");
        } else {
            manualType.append("long_");
        }
        if (isImperial == true) {
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



//    public static void main(String[] args) {
//
//        System.out.println(ConstantsRecog.COMMAND_LINE_USAGE_MESSAGE);
//        //TODO:
//        //Output path -- Default to Target/Generate filename based on attributes. - Change title based on attributes i.e. not always SCAF
//        //Imperial
//        //AobTable (Short Only)
//
//        //Command example: Short or Long S/L - (default short), TargetPath (default to const), imperial? (optional, default false) AobTable? (optional, default to false)
//
//        Boolean isShortManual = Optional.ofNullable(args[0].trim().toUpperCase()).equals("S") ? true : false;
//        Optional<String> targetPath = Optional.ofNullable(args[1].trim()).orElseGet(ConstantsRecog.RECOGNITION_MANUAL_SHORT_FILENAME);
//        Optional<String> isImperial = Optional.ofNullable(args[2].trim().toUpperCase()).equals("I") ? true : false;
//        Optional<String> printAobTable = Optional.ofNullable(args[3]);
//    }


    public void publishShortRecognitionManual (String manualTargetPath, Boolean imperial, Boolean aobTable) throws
            IOException {
        recognitionManualMain.publishRecognitionManualShort(shipList, ConstantsRecog.RESOURCES_PATH + "\\" + ConstantsRecog.RECOGNITION_MANUAL_SHORT_FILENAME, false, false);
    }

    public void publishLongRecognitionManual (String manualTargetPath, Boolean imperial) throws IOException {
        recognitionManualMain.publishRecognitionManualLong(shipList, ConstantsRecog.RESOURCES_PATH + "\\recogLm.html", false);
    }
}


