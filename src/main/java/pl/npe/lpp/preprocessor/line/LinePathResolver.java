package pl.npe.lpp.preprocessor.line;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.03.14
 * Time: 18:50
 */
public enum LinePathResolver implements LinePreprocessor{
    INSTANCE;

    @Override
    public String preprocessLine(String line, ProcessingContext context) {
        if(line.contains("\\includegraphics")){
            return resolvePath(line);
        }else {
            return line;
        }
    }

    String resolvePath(String line){
        String pathStr = line.substring(line.indexOf('{')+1, line.lastIndexOf('}'));
        Path path = Paths.get(pathStr);
        if(!path.isAbsolute()){
            return line.substring(0, line.indexOf('{')+1) + path.toAbsolutePath() + line.substring(line.lastIndexOf('}'));
        }else {
            return line;
        }
    }


    @Override
    public String toString() {
        return "PATH_RESOLVER";
    }
}
