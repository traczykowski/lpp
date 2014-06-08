package pl.npe.lpp;

import org.apache.log4j.Logger;
import pl.npe.lpp.cmdline.LppCmdLine;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 21.05.14
 * Time: 15:24
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] argv){
        logger.info("LPP v" + LppVersion.getVersion());
        new LppCmdLine().preprocess(argv);
    }

}
