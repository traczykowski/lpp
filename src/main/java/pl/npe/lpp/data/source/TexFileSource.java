package pl.npe.lpp.data.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:10
 */
class TexFileSource implements TexSource {

    private final Path path;
    private BufferedReader reader;

    public TexFileSource(Charset charset, Path path) throws IOException {
        this.path = path;
        reader = Files.newBufferedReader(path, charset);
    }

    @Override
    public String readLine() throws IOException {
        String line =  reader.readLine();
        if(line == null){
            reader.close();
        }
        return line;
    }

    @Override
    public String getSourceName() {
        return path.toFile().getAbsolutePath();
    }
}
