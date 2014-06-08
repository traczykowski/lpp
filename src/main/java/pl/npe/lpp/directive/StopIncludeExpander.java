package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:38
 */
class StopIncludeExpander extends AbstractExpander {

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        ParamCountValidator.validateParametersCount(params, 0, context);
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) {
        context.setIncludeText(false);
        return "";
    }
}
