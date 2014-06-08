package pl.npe.lpp.preprocessor.line;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.directive.DirectiveExpander;
import pl.npe.lpp.preprocessor.line.LineProcessor;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 11:04
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultLineProcessorTest extends DefaultLineProcessor {

    private ProcessingContext context;

    @Mock
    private DirectiveExpander expanderMock;

    @Mock
    private TexSource texSourceMock;

    @Mock
    private LinePreprocessorChain linePreprocessorChainMock;

    @Before
    public void setUp() throws Exception {
        context = new ProcessingContext.Builder(texSourceMock, Charset.defaultCharset()).chain(linePreprocessorChainMock).build();
        super.expander = expanderMock;
        when(linePreprocessorChainMock.preprocessLine(anyString(), eq(context))).then(
                new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                        return (String)invocationOnMock.getArguments()[0];
                    }
                }
        );
        when(expanderMock.expand(anyString(), anyList(), any(ProcessingContext.class))).thenReturn("EXP");
    }

    @Test
    public void testProcessLine_SingleTextWithInclusion() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text element";
        when(linePreprocessorChainMock.preprocessLine(rawLine, context)).thenReturn(rawLine);
        String line = super.processLine(rawLine, context);
        assertEquals(rawLine+"\n", line);
    }

    @Test
    public void testProcessLine_SingleTextWithoutInclusion() throws Exception {
        context.setIncludeText(false);
        String rawLine = "single text element";
        String line = super.processLine(rawLine, context);
        assertTrue(line.isEmpty());
    }

    @Test
    public void testProcessLine_SingleTextWithInlusionAndChaining() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text element";
        String line = super.processLine(rawLine, context);
        assertEquals(rawLine+"\n", line);
        verify(linePreprocessorChainMock).preprocessLine(rawLine, context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndComment() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text element %comment";
        when(linePreprocessorChainMock.preprocessLine(rawLine, context)).thenReturn(rawLine);
        String line = super.processLine(rawLine, context);
        assertEquals(rawLine+"\n", line);
        verify(linePreprocessorChainMock).preprocessLine(rawLine, context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndDirective() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text element % comment lpp[verbatim(EXP,\"arg\")]";
        String line = super.processLine(rawLine, context);
        assertEquals("single text element EXP\n", line);
        verify(expanderMock).expand("verbatim", Arrays.asList("EXP", "arg"), context);
        verify(linePreprocessorChainMock).preprocessLine("single text element ", context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndDirective2() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text \\% element % comment lpp[verbatim(EXP)]";
        String line = super.processLine(rawLine, context);
        assertEquals("single text \\% element EXP\n", line);
        verify(expanderMock).expand("verbatim", Arrays.asList("EXP"), context);
        verify(linePreprocessorChainMock).preprocessLine("single text \\% element ", context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndDirectives() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text \\% element % comment lpp[verbatim(EXP)] bleble lpp[verbatim(\"EXP\")]";
        String line = super.processLine(rawLine, context);
        assertEquals("single text \\% element EXPEXP\n", line);
        verify(expanderMock, times(2)).expand("verbatim", Arrays.asList("EXP"), context);
        verify(linePreprocessorChainMock).preprocessLine("single text \\% element ", context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndDirective3() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text \\% element % comment lpp[verbatim()] bleble]";
        String line = super.processLine(rawLine, context);
        assertEquals("single text \\% element EXP\n", line);
        verify(linePreprocessorChainMock).preprocessLine("single text \\% element ", context);
    }

    @Test
    public void testProcessLine_SingleTextWithInclusionAndDirective4() throws Exception {
        context.setIncludeText(true);
        String rawLine = "single text \\% element % comment lpp[replace(first,second)] bleble]";
        String line = super.processLine(rawLine, context);
        assertEquals("single text \\% element EXP\n", line);
        verify(expanderMock).expand("replace", Arrays.asList("first", "second"), context);
        verify(linePreprocessorChainMock).preprocessLine("single text \\% element ", context);
    }

    @Test
    public void testLineContainsDirectiveTrue() throws Exception {
        assertTrue(super.lineContainsDirective("sdjfhsfdh % sdfs  lpp[include(\"src/test/resources/test/include/include_part.tex\", true)]"));
    }

    @Test
    public void testLineContainsDirectiveFalse() throws Exception {
        assertFalse(super.lineContainsDirective("sdjfhsfdh  sdfs  lpp[include(\"src/test/resources/test/include/include_part.tex\", true)]"));
        assertFalse(super.lineContainsDirective("sdjfhsfdh  sdfs \\% lpp[include(\"src/test/resources/test/include/include_part.tex\", true)]"));
        assertFalse(super.lineContainsDirective("sdjfhsfdh  sdfs (\"src/test/resources/test/include/include_part.tex\", true)"));
    }

    @Test
    public void testHandleNotIncludableWithoutDirective() throws Exception {
        assertEquals("", super.handleNotIncludableWithoutDirective());
    }

    @Test
    public void testHandleIncludableWithoutDirective() throws Exception {
        String line = "this is example line without any replacements and paths to resolve";
        String result = super.handleIncludableWithoutDirective(line, context);
        assertEquals(line+ "\n", result);
        verify(linePreprocessorChainMock).preprocessLine(line, context);
    }

}
