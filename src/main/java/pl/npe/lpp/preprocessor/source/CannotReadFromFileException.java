package pl.npe.lpp.preprocessor.source;


import pl.npe.lpp.LPPException;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 31.10.13
 * Time: 21:05
 */
@SuppressWarnings("WeakerAccess")
public class CannotReadFromFileException extends LPPException {


    public CannotReadFromFileException(ProcessingContext context, String message) {
        super(context, message);
    }
}
