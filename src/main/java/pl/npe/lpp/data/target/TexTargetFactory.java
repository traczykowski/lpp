package pl.npe.lpp.data.target;

import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:50
 */
public class TexTargetFactory {

    public TexTarget build(Path path){
        return new TexFileTarget(path);
    }
}
