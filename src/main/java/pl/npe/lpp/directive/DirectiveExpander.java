package pl.npe.lpp.directive;

import org.apache.log4j.Logger;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 13:23
 */
public class DirectiveExpander {

    private static final Logger logger = Logger.getLogger(DirectiveExpander.class);

    public String expand(String identifier, List<String> params, ProcessingContext context) throws UnknownDirectiveException, ExpanderException, CannotReadFromFileException {

        Directive directive = Directive.getByIdentifier(identifier, context);
        logger.info(getExpandMessage(context, directive, params));
        return directive.expand(params, context);
    }

    String getExpandMessage(ProcessingContext context, Directive directive, List<String> params){
        return new StringBuilder("Expanding: [")
                .append(context.getTexSource().getSourceName())
                .append("]:[")
                .append(context.getLineNumber())
                .append("]")
                .append(directive)
                .append("(")
                .append(params)
                .append(")")
                .toString();

    }
}
