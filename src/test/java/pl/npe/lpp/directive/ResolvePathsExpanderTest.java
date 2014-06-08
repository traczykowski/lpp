package pl.npe.lpp.directive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LinePathResolver;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:57
 */
@RunWith(MockitoJUnitRunner.class)
public class ResolvePathsExpanderTest {

    private final Expander expander = new ResolvePathsExpander();

    @Mock
    private LinePreprocessorChain chainMock;

    @Spy
    private ProcessingContext contextMock;

    @Before
    public void setUp() throws Exception {
        when(contextMock.getChain()).thenReturn(chainMock);
    }

    @Test
    public void testExpandTrue() throws Exception {
        String param = "true";
        assertTrue(expander.expand(Arrays.asList(param), contextMock).isEmpty());
        verify(chainMock).registerPreprocessor(LinePathResolver.INSTANCE);
    }

    @Test
    public void testExpandFalse() throws Exception {
        String param = "false";
        assertTrue(expander.expand(Arrays.asList(param), contextMock).isEmpty());
        verify(chainMock).unregisterPreprocessor(LinePathResolver.INSTANCE);
    }
}
