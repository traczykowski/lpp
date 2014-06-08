package pl.npe.lpp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 19:50
 */
public class LppVersion {

    private static final String VERSION;

    static {
        Properties properties = new Properties();
        try (InputStream is = LppVersion.class.getResourceAsStream("/version.properties")){
            properties.load(is);
            VERSION = properties.getProperty("lpp.version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVersion(){
        return VERSION;
    }
}
