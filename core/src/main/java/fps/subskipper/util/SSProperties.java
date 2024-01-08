package fps.subskipper.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SSProperties {

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "ss.properties";
    Properties catalogProps = new Properties();

    private Properties loadSSProperties() throws IOException {

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        return appProps;
    }


}
