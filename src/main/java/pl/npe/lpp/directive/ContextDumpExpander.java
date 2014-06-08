package pl.npe.lpp.directive;

import org.apache.log4j.Logger;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 22:00
 */
public class ContextDumpExpander extends AbstractExpander {

    private static final Logger logger = Logger.getLogger(ContextDumpExpander.class);

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {

    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) throws CannotReadFromFileException, UnknownDirectiveException, ExpanderException {
        logger.info(context);
        return "";
    }
}
