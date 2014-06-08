package pl.npe.lpp.directive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Arrays;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:26
 */
@RunWith(MockitoJUnitRunner.class)
public class SetFlagExpanderTest {

    @Spy
    private ProcessingContext contextMock;

    private final Expander expander = new SetFlagExpander();

    @Test
    public void testExpand() throws Exception {
        String flag = "the flag";
        assertTrue(expander.expand(Arrays.asList(flag), contextMock).isEmpty());
        assertTrue(contextMock.isFlagSet(flag));
    }
}
