package pl.npe.lpp.preprocessor.source;

import pl.npe.lpp.directive.ExpanderException;
import pl.npe.lpp.directive.UnknownDirectiveException;
import pl.npe.lpp.preprocessor.line.LineProcessor;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 15:58
 */
public interface SourceProcessor {




//
//    public String processFile() throws CannotReadFromFileException, UnknownDirectiveException, ExpanderException {
//        StringBuilder builder = new StringBuilder();
//        try {
//            BufferedReader reader = Files.newBufferedReader(context.getPath(), charset);
//            String line;
//            while ((line = reader.readLine()) != null){
//                context.incrementLineNumber();
//                builder.append(lineProcessor.processLine(line, context)).append("\n");
//            }
//
//        } catch (IOException e) {
//            throw new CannotReadFromFileException(context, "Cannot read from file: ");
//        }
//        return builder.toString();
//    }

    public String processSource(ProcessingContext context, LineProcessor lineProcessor) throws UnknownDirectiveException, CannotReadFromFileException, ExpanderException;
}
