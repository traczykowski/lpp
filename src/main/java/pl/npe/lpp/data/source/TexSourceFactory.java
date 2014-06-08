package pl.npe.lpp.data.source;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:18
 */
public class TexSourceFactory {

    public TexSource build(Charset charset, Path path) throws IOException {
        return new TexFileSource(charset, path);
    }
}
