package pl.npe.lpp.preprocessor.source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.LppParams;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 19:22
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessingContext_BuilderTest {

    @Mock
    private Path path;

    @Mock
    private TexSource texSourceMock;


    @Test
    public void testBuildNew() throws Exception {
        ProcessingContext context = new ProcessingContext.Builder(texSourceMock, Charset.defaultCharset()).build();
        assertEquals(texSourceMock, context.getTexSource());
        assertNotNull(context.getReplacements());
        assertNotNull(context.getChain());
        assertEquals(Charset.defaultCharset(), context.getCharset());
        assertFalse(context.isIncludeText());
        assertFalse(context.isInherited());
    }

    @Test
    public void testBuildFromExisting() throws Exception {
        ProcessingContext oldContext = new ProcessingContext.Builder(texSourceMock, Charset.defaultCharset()).build();
        ProcessingContext context = new ProcessingContext.Builder(oldContext, texSourceMock).build();
        assertEquals(texSourceMock, context.getTexSource());
        assertEquals(oldContext.getReplacements(), context.getReplacements());
        assertEquals(oldContext.getChain(), context.getChain());
        assertEquals(oldContext.getFlags(), context.getFlags());
        assertEquals(Charset.defaultCharset(), context.getCharset());
        assertFalse(context.isIncludeText());
        assertTrue(context.isInherited());
    }

    @Test
    public void testBuildFromLppParams() throws Exception {
        Charset charset = Charset.forName("us-ascii");
        Set<String> flags = new HashSet<>(Arrays.asList("flag1","flag2"));
        LppParams params = new LppParams.Builder(texSourceMock, null)
                .charset(charset)
                .flags(flags)
                .build();
        ProcessingContext context = new ProcessingContext.Builder(params).build();
        assertEquals(charset, context.getCharset());
        assertEquals(params.getFlags(), context.getFlags());
        assertEquals(params.getTexSource(), context.getTexSource());
        assertFalse(context.isIncludeText());
        assertFalse(context.isInherited());
        assertNotNull(context.getChain());
        assertEquals(0, context.getLineNumber());
        assertTrue(context.getReplacements().isEmpty());
    }
}
