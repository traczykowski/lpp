package pl.npe.lpp.directive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:50
 */
@RunWith(MockitoJUnitRunner.class)
public class ReplaceExpanderTest {

    private final Expander expander = new ReplaceExpander();

    @Spy
    private ProcessingContext contextMock;

    @Test
    public void testExpand() throws Exception {
        String from = "source";
        String to = "target";
        assertTrue(expander.expand(Arrays.asList(from, to), contextMock).isEmpty());
        assertEquals(to, contextMock.getReplacements().get(from));
    }
}
