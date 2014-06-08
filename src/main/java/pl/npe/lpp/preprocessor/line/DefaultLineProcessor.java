package pl.npe.lpp.preprocessor.line;

import pl.npe.lpp.directive.DirectiveExpander;
import pl.npe.lpp.directive.ExpanderException;
import pl.npe.lpp.directive.UnknownDirectiveException;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 18:30
 */
class DefaultLineProcessor implements LineProcessor{

    private static final Pattern DIRECTIVE = Pattern.compile("(?<!\\\\)%.*?lpp\\[\\s*(\\w+)");

    private static final Pattern COMMENT = Pattern.compile("(?<!\\\\)%");

    DirectiveExpander expander = new DirectiveExpander();

    @Override
    public String processLine(String line, ProcessingContext context) throws UnknownDirectiveException, ExpanderException, CannotReadFromFileException {

        if(lineContainsDirective(line)){
            return handleLineWithDirectiveUsage(line, context);
        }else if(context.isIncludeText()){
            return handleIncludableWithoutDirective(line, context);
        }else {
            return handleNotIncludableWithoutDirective();
        }

    }

    String handleIncludableWithoutDirective(String line, ProcessingContext context){
        return preprocessUsingChain(line,context)+"\n";
    }

    String preprocessUsingChain(String line, ProcessingContext context){
        return context.getChain().preprocessLine(line, context);
    }


    String handleNotIncludableWithoutDirective(){
        return "";
    }

    boolean lineContainsDirective(String line){
        Matcher matcher = DIRECTIVE.matcher(line);
        return matcher.find();
    }

    private String handleLineWithDirectiveUsage(String line, ProcessingContext context) throws UnknownDirectiveException, ExpanderException, CannotReadFromFileException {
        Matcher comment = COMMENT.matcher(line);
        comment.find();
        StringBuilder result = new StringBuilder("");
        if(context.isIncludeText()){
            result.append(preprocessUsingChain(line.substring(0,comment.start()), context));
        }

        String directiveUsages = line.substring(comment.start());
        DirectiveUsages usages = new DirectiveUsages(directiveUsages);
        for(DirectiveUsage usage : usages){
            result.append(expander.expand(usage.getDirective(), usage.getParams(), context));
        }

        return result.toString()+"\n";
    }
}
