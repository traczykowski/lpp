package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LinePathResolver;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:56
 */
class ResolvePathsExpander extends AbstractExpander {

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        ParamCountValidator.validateParametersCount(params, 1, context);
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) {
        LinePreprocessorChain chain = context.getChain();
        if(Boolean.valueOf(params.get(0))){
            chain.registerPreprocessor(LinePathResolver.INSTANCE);
        }else {
            chain.unregisterPreprocessor(LinePathResolver.INSTANCE);
        }
        return "";
    }
}
