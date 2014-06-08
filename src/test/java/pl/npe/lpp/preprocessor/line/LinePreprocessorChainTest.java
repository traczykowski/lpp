package pl.npe.lpp.preprocessor.line;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LinePathResolver;
import pl.npe.lpp.preprocessor.line.LinePreprocessor;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 15:12
 */
@RunWith(MockitoJUnitRunner.class)
public class LinePreprocessorChainTest extends LinePreprocessorChain {

    @Mock
    private LinePreprocessor linePreprocessorMock1;

    @Mock
    private LinePreprocessor linePreprocessorMock2;

    @Before
    public void setUp() throws Exception {
        super.chain.clear();

        when(linePreprocessorMock1.preprocessLine(anyString(), any(ProcessingContext.class))).thenReturn("linePreprocessorMock1");
        when(linePreprocessorMock2.preprocessLine(anyString(), any(ProcessingContext.class))).thenReturn("linePreprocessorMock2");
    }

    @Test
    public void testRegisterPreprocessor() throws Exception {
        assertTrue(super.registerPreprocessor(LinePathResolver.INSTANCE));
        assertEquals(1, super.chain.size());
    }

    @Test
    public void testUnregisterPreprocessor() throws Exception {
        assertTrue(super.registerPreprocessor(LinePathResolver.INSTANCE));
        assertTrue(super.unregisterPreprocessor(LinePathResolver.INSTANCE));
        assertEquals(0, super.chain.size());
    }

    @Test
    public void testPreprocessLine() throws Exception {
        String expected = "linePreprocessorMock2";
        super.registerPreprocessor(linePreprocessorMock1);
        super.registerPreprocessor(linePreprocessorMock2);
        assertEquals(expected, super.preprocessLine("", null));
        verify(linePreprocessorMock1).preprocessLine("", null);
        verify(linePreprocessorMock2).preprocessLine("linePreprocessorMock1", null);
    }
}
