package pl.npe.lpp.directive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:38
 */
@RunWith(MockitoJUnitRunner.class)
public class StopIncludeExpanderTest {

    @Spy
    private ProcessingContext contextMock;

    private final Expander expander = new StopIncludeExpander();

    @Test
    public void testExpand() throws Exception {
        assertTrue(expander.expand(Collections.<String>emptyList(), contextMock).isEmpty());
        assertFalse(contextMock.isIncludeText());
    }
}
