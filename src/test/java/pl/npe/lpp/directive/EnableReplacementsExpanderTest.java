package pl.npe.lpp.directive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;
import pl.npe.lpp.preprocessor.line.LineStringReplacer;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:59
 */
@RunWith(MockitoJUnitRunner.class)
public class EnableReplacementsExpanderTest {

    private final Expander expander = new EnableReplacementsExpander();

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
        verify(chainMock).registerPreprocessor(LineStringReplacer.INSTANCE);
    }

    @Test
    public void testExpandFalse() throws Exception {
        String param = "false";
        assertTrue(expander.expand(Arrays.asList(param), contextMock).isEmpty());
        verify(chainMock).unregisterPreprocessor(LineStringReplacer.INSTANCE);
    }
}
