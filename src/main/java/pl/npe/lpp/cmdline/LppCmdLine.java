package pl.npe.lpp.cmdline;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import pl.npe.lpp.preprocessor.Lpp;
import pl.npe.lpp.preprocessor.LppParams;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 16:07
 */
public class LppCmdLine {

    private static final Logger logger = Logger.getLogger(LppCmdLine.class);

    CmdLineParamsParser cmdLineParamsParser = new CmdLineParamsParser();
    private final Lpp lpp = new Lpp();

    public boolean preprocess(String[] args)  {
        try {
            LppParams params = cmdLineParamsParser.parse(args);
            return lpp.preprocess(params);
        }catch (ParseException e) {
            logger.error(e.getMessage());
            usage(LppOption.getOptions());
            return false;
        } catch (IOException e) {
            logger.error(e,e);
            return false;
        }
    }

    private void usage(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "lpp", options );
    }
}
