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
 * Date: 20.05.14
 * Time: 19:43
 */
@RunWith(MockitoJUnitRunner.class)
public class ParamCountValidatorTest {

    @Spy
    private ProcessingContext contextMock;

    @Mock
    private TexSource texSourceMock;

    @Before
    public void setUp() throws Exception {
        when(texSourceMock.getSourceName()).thenReturn("source");
    }

    @Test
    public void testValidateTrue() throws Exception {
        ParamCountValidator.validateParametersCount(Arrays.asList(""), 1, contextMock);
    }

    @Test(expected = ExpanderException.class)
    public void testValidateFalse() throws Exception {
        ParamCountValidator.validateParametersCount(Arrays.asList(""), 2, new ProcessingContext.Builder(contextMock, texSourceMock).build());
    }
}
