package pl.npe.lpp.directive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:32
 */
@RunWith(MockitoJUnitRunner.class)
public class IfSetExpanderTest {

    private final Expander expander = new IfSetExpander();
    private final Expander setFlagExpander = new SetFlagExpander();

    @Spy
    private ProcessingContext contextMock;

    @Test
    public void testExpandFalse() throws Exception {
        String flag = "the flag";
        assertTrue(expander.expand(Arrays.asList(flag), contextMock).isEmpty());
        assertFalse(contextMock.isIncludeText());
    }

    @Test
    public void testExpandTrue() throws Exception {
        String flag = "the flag";
        List<String> params = Arrays.asList(flag);
        setFlagExpander.expand(params, contextMock);
        assertTrue(expander.expand(params, contextMock).isEmpty());
        assertTrue(contextMock.isIncludeText());
    }
}
