package pl.npe.lpp.directive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.source.TexSourceFactory;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.SourceProcessor;
import pl.npe.lpp.preprocessor.source.SourceProcessorFactory;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 20:11
 */
@RunWith(MockitoJUnitRunner.class)
public class IncludeExpanderTest extends IncludeExpander{

    @Mock
    private SourceProcessorFactory sourceProcessorFactoryMock;

    @Mock
    private SourceProcessor sourceProcessorMock;

    @Mock
    private Path path;

    @Mock
    private ProcessingContext.Builder builder;

    @Mock
    private ProcessingContext contextMock;

    @Mock
    private TexSource texSourceMock;

    @Mock
    private TexSourceFactory texSourceFactoryMock;

    @Before
    public void setUp() throws Exception {
        super.sourceProcessorFactory = sourceProcessorFactoryMock;
        when(sourceProcessorFactoryMock.build()).thenReturn(sourceProcessorMock);

        when(texSourceMock.getSourceName()).thenReturn("source");

        super.texSourceFactory = texSourceFactoryMock;
        when(texSourceFactoryMock.build(any(Charset.class), eq(path))).thenReturn(texSourceMock);

    }

    @Test(expected = ExpanderException.class)
    public void testValidateParamsBadArgCount() throws Exception {
        super.validateParams(Arrays.asList("a"), new ProcessingContext.Builder(contextMock, texSourceMock).build());
    }

    @Test(expected = ExpanderException.class)
    public void testValidateParamsInvalidCharset() throws Exception {
        super.validateParams(Arrays.asList("a", "b", "c"), new ProcessingContext.Builder(contextMock, texSourceMock).build());
    }

    @Test
    public void testExpandDirective() throws Exception {

        super.expandDirective(Arrays.asList(".", "true", "UTF-8"), contextMock);
        verify(sourceProcessorFactoryMock).build();
    }

    @Test
    public void testGetCharsetUTF16() throws Exception {
        assertEquals(Charset.forName("UTF-16"), super.getCharset(Arrays.asList("","", "UTF-16")));
    }

    @Test
    public void testGetCharsetDefault() throws Exception {
        assertEquals(Charset.defaultCharset(), super.getCharset(Arrays.asList("","")));
    }

    @Test
    public void testGetContext_Inherited() throws Exception {
        List<String> params = Arrays.asList("dummy.tex", "true", Charset.defaultCharset().name());
        ProcessingContext context = super.getContext(params, contextMock);
        assertTrue(context.isInherited());
    }

    @Test
    public void testGetContext_NotInherited() throws Exception {
        List<String> params = Arrays.asList("dummy.tex", "false", Charset.defaultCharset().name());
        ProcessingContext context = super.getContext(params, contextMock);
        assertFalse(context.isInherited());
    }
}
