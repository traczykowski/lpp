package pl.npe.lpp.preprocessor.line;

import org.apache.log4j.Logger;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.directive.DirectiveExpander;
import pl.npe.lpp.directive.ExpanderException;
import pl.npe.lpp.directive.UnknownDirectiveException;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 12:04
 */
public interface LineProcessor {

    public String processLine(String line, ProcessingContext context) throws UnknownDirectiveException, ExpanderException, CannotReadFromFileException ;
}
