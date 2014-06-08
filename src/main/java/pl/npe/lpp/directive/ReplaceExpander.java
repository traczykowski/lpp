package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:47
 */
class ReplaceExpander extends AbstractExpander {

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        ParamCountValidator.validateParametersCount(params, 2, context);
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) {
        context.defineReplaceString(params.get(0), params.get(1));
        return "";
    }
}
