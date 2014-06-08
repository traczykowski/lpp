package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 16:08
 */
abstract class AbstractExpander implements Expander{

    public String expand(List<String> params, ProcessingContext context) throws ExpanderException, CannotReadFromFileException, UnknownDirectiveException {
        validateParams(params, context);
        return expandDirective(params, context);
    }

    protected abstract void validateParams(List<String> params, ProcessingContext context) throws ExpanderException;

    protected abstract String expandDirective(List<String> params, ProcessingContext context) throws CannotReadFromFileException, UnknownDirectiveException, ExpanderException;

}
