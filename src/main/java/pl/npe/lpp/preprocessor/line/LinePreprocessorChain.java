package pl.npe.lpp.preprocessor.line;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 15:09
 */
public class LinePreprocessorChain {

    Set<LinePreprocessor> chain = new LinkedHashSet<>();

    public boolean registerPreprocessor(LinePreprocessor preprocessor){
        return chain.add(preprocessor);
    }

    public boolean unregisterPreprocessor(LinePreprocessor preprocessor){
        return chain.remove(preprocessor);
    }

    String preprocessLine(String line, ProcessingContext context){
        String result = line;
        for(LinePreprocessor preprocessor : chain){
            result = preprocessor.preprocessLine(result, context);
        }
        return result;
    }

    @Override
    public String toString() {
        return chain.toString();
    }
}
