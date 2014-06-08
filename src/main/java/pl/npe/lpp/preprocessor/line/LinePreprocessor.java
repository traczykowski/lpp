package pl.npe.lpp.preprocessor.line;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 15:02
 */
@SuppressWarnings("WeakerAccess")
public interface LinePreprocessor {

    String preprocessLine(String line, ProcessingContext context);
}
