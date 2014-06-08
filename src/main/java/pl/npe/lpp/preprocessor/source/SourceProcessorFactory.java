package pl.npe.lpp.preprocessor.source;

import pl.npe.lpp.preprocessor.source.SourceProcessor;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 20:06
 */
public class SourceProcessorFactory {

    public SourceProcessor build(){
        return new DefaultSourceProcessor();
    }
}
