package pl.npe.lpp.directive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:29
 */
@RunWith(MockitoJUnitRunner.class)
public class UnsetFlagExpanderTest {

    @Spy
    private ProcessingContext contextMock;

    private final Expander expander = new UnsetFlagExpander();

    @Test
    public void testExpand() throws Exception {
        String flag = "the flag";
        assertTrue(expander.expand(Arrays.asList(flag), contextMock).isEmpty());
        assertFalse(contextMock.isFlagSet(flag));
    }
}
