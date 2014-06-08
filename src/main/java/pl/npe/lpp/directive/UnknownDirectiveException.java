package pl.npe.lpp.directive;


import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.LPPException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 31.10.13
 * Time: 18:53
 */
public class UnknownDirectiveException extends LPPException {

    public UnknownDirectiveException(ProcessingContext context, String message) {
        super(context, message);
    }
}
