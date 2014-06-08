package pl.npe.lpp.directive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 20:36
 */
@RunWith(MockitoJUnitRunner.class)
public class HttpGetRequestExpanderTest {

    private final AbstractExpander expander = new HttpGetRequestExpander();

    @Spy
    private ProcessingContext contextMock;

    @Mock
    private TexSource texSourceMock;

    @Before
    public void setUp() throws Exception {
        when(contextMock.getTexSource()).thenReturn(texSourceMock);
    }

    @Test
    public void testValidateParamsOK() throws Exception {
        expander.validateParams(Arrays.asList("http://www.google.pl?a=1"), contextMock);
    }

    @Test(expected = ExpanderException.class)
    public void testValidateParamsBadArgCount() throws Exception {
        expander.validateParams(Arrays.asList("http://www.google.pl", "qwerty"), contextMock);
    }

    @Test(expected = ExpanderException.class)
    public void testValidateParamsInvalidUrl() throws Exception {
        expander.validateParams(Arrays.asList("www.google"), contextMock);
    }
}
