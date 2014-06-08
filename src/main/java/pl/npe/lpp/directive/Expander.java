package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 13:09
 */
public interface Expander {

    String expand(List<String> params, ProcessingContext context) throws ExpanderException, CannotReadFromFileException, UnknownDirectiveException;
}
