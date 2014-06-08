package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 19:35
 */
@SuppressWarnings("ALL")
public class ParamCountValidator {

    static void validateParametersCount(List<String> params, int expectedCount, ProcessingContext context) throws ExpanderException {
        if(params.size() != expectedCount){
            throw new ExpanderException(context, "Invalid parameter count: expected = " + expectedCount + " passed = " + params.size());
        }
    }
}
