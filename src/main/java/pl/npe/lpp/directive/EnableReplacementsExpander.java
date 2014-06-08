package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;
import pl.npe.lpp.preprocessor.line.LineStringReplacer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:59
 */
public class EnableReplacementsExpander extends AbstractExpander {

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        ParamCountValidator.validateParametersCount(params, 1, context);
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) {
        LinePreprocessorChain chain = context.getChain();
        if(Boolean.valueOf(params.get(0))){
            chain.registerPreprocessor(LineStringReplacer.INSTANCE);
        }else {
            chain.unregisterPreprocessor(LineStringReplacer.INSTANCE);
        }
        return "";
    }
}
