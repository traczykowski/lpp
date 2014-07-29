package pl.npe.lpp.preprocessor;

import org.apache.log4j.Logger;
import pl.npe.lpp.LPPException;
import pl.npe.lpp.preprocessor.line.LineProcessorFactory;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.SourceProcessor;
import pl.npe.lpp.preprocessor.source.SourceProcessorFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 10:33
 */
public class Lpp {

    private static final Logger logger = Logger.getLogger(Lpp.class);

    SourceProcessorFactory sourceProcessorFactory = new SourceProcessorFactory();
    LineProcessorFactory lineProcessorFactory = new LineProcessorFactory();

    public boolean preprocess(LppParams params){
        Charset charset = params.getCharset();
        SourceProcessor processor = sourceProcessorFactory.build();
        try {
            ProcessingContext context = new ProcessingContext.Builder(params).build();
            String processedData = processor.processSource(context, lineProcessorFactory.build());
            return params.getTexTarget().save(processedData.getBytes(charset), params);
        } catch (IOException e) {
            logger.info("Cannot create output file");
            return false;
        } catch (LPPException e) {
            logger.error(e) ;
            return false;
        }

    }



}
