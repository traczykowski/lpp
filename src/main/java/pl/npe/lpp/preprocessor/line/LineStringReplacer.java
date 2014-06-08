package pl.npe.lpp.preprocessor.line;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.03.14
 * Time: 18:51
 */
public enum LineStringReplacer implements LinePreprocessor{
    INSTANCE;

    @Override
    public String preprocessLine(String line, ProcessingContext context) {
        Map<String, String> replaceStrings = context.getReplacements();
        for(String source : replaceStrings.keySet()){
            line = line.replaceAll(source, replaceStrings.get(source));
        }
        return line;
    }

    @Override
    public String toString() {
        return "STRING_REPLACER";
    }
}
