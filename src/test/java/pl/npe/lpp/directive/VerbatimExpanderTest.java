package pl.npe.lpp.directive;

import org.junit.Test;
import pl.npe.lpp.directive.Expander;
import pl.npe.lpp.directive.VerbatimExpander;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 14:24
 */
public class VerbatimExpanderTest {

    private final Expander expander = new VerbatimExpander();

    @Test
    public void testExpand() throws Exception {
        String param = "verbatim text";
        assertEquals(param, expander.expand(Arrays.asList(param), null));
    }
}
