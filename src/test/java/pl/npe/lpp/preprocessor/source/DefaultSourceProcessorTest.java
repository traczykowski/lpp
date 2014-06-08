package pl.npe.lpp.preprocessor.source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.line.LineProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 18:17
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultSourceProcessorTest extends DefaultSourceProcessor {
    private static final String[] args = {"line1", "2line", "abra", "kadabra", "hokus"};

    @Mock
    private TexSource texSourceMock;

    @Mock
    private ProcessingContext contextMock;

    @Mock
    private LineProcessor lineProcessorMock;

    @Before
    public void setUp() throws Exception {

        when(texSourceMock.readLine()).thenReturn(
            args[0],args[1],args[2],args[3],args[4],null
        );

        when(contextMock.getTexSource()).thenReturn(texSourceMock);
        doCallRealMethod().when(contextMock).incrementLineNumber();
        when(contextMock.getLineNumber()).thenCallRealMethod();

        when(lineProcessorMock.processLine(anyString(), eq(contextMock))).thenReturn(args[0],args[1],args[2],args[3],args[4]);
    }

    @Test
    public void testProcessSource() throws Exception {
        String result = super.processSource(contextMock, lineProcessorMock);
        assertEquals(getExpectedResult(), result);
        assertEquals(5, contextMock.getLineNumber());
    }

    String getExpectedResult(){
        StringBuilder sb = new StringBuilder();
        for(String s : args){
            sb.append(s);
        }
        return sb.toString();
    }
}
