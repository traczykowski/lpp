package pl.npe.lpp.preprocessor.line;

import org.junit.Test;
import pl.npe.lpp.preprocessor.line.LinePathResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.03.14
 * Time: 19:25
 */
public class LinePathResolverTest {

    @Test
    public void testResolvePath() throws Exception {
        String source = "\\includegraphics[height=60mm]{dummy.tex}qwerty";
        Path path = Paths.get("dummy.tex").toAbsolutePath();
        String expected = "\\includegraphics[height=60mm]{"+path.toFile()+"}qwerty";
        assertEquals(expected, LinePathResolver.INSTANCE.preprocessLine(source, null));
    }

    @Test
    public void testResolvePathFromAbsolute() throws Exception {
        Path path = Paths.get("dummy.tex").toAbsolutePath();
        String source = path.toFile().getAbsolutePath();
        assertEquals(source, LinePathResolver.INSTANCE.preprocessLine(source, null));
    }
}
