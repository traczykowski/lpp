package pl.npe.lpp.preprocessor.line;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 18:30
 */
public class LineProcessorFactory {

    public LineProcessor build(){
        return new DefaultLineProcessor();
    }
}
