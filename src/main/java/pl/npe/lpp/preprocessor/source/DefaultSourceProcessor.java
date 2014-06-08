package pl.npe.lpp.preprocessor.source;

import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.directive.ExpanderException;
import pl.npe.lpp.directive.UnknownDirectiveException;
import pl.npe.lpp.preprocessor.line.LineProcessor;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 16:13
 */
class DefaultSourceProcessor implements SourceProcessor{

    @Override
    public String processSource(ProcessingContext context, LineProcessor lineProcessor) throws UnknownDirectiveException, CannotReadFromFileException, ExpanderException {
        StringBuilder builder = new StringBuilder();
        String line;
        TexSource source = context.getTexSource();
        try {
            while ((line = source.readLine()) != null) {
                context.incrementLineNumber();
                builder.append(lineProcessor.processLine(line, context));
            }
        }catch (IOException e){
            throw new CannotReadFromFileException(context, "Cannot read from file: ");
        }
        return builder.toString();
    }
}
