package pl.npe.lpp.directive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 17:20
 */
@RunWith(MockitoJUnitRunner.class)
public class DirectiveTest {

    @Mock
    private TexSource texSourceMock;

    private ProcessingContext context;

    @Before
    public void setUp() throws Exception {
        context = new ProcessingContext.Builder(texSourceMock, null).build();
    }

    @Test
    public void testGetByIdentifierOK() throws Exception {
        assertEquals(Directive.SET_FLAG, Directive.getByIdentifier("SETFLAG", context));
        assertEquals(Directive.SET_FLAG, Directive.getByIdentifier("SET_FLAG", context));
        assertEquals(Directive.SET_FLAG, Directive.getByIdentifier("setFlag", context));
        assertEquals(Directive.SET_FLAG, Directive.getByIdentifier("setflag", context));
        assertEquals(Directive.SET_FLAG, Directive.getByIdentifier("_set_fl_ag", context));
    }

    @Test(expected = UnknownDirectiveException.class)
    public void testGetByIdentifierFailed() throws Exception {
        Directive.getByIdentifier("sflag", context);
    }
}
