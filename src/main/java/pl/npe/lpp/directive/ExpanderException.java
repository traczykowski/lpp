package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.LPPException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 16:13
 */
@SuppressWarnings("WeakerAccess")
public class ExpanderException extends LPPException {
    protected ExpanderException(ProcessingContext context, String message) {
        super(context, message);
    }
}
