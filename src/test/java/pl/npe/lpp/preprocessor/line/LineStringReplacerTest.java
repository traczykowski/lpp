package pl.npe.lpp.preprocessor.line;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.line.LineStringReplacer;


import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.03.14
 * Time: 19:01
 */
@RunWith(MockitoJUnitRunner.class)
public class LineStringReplacerTest {

    @Spy
    private ProcessingContext contextMock;

    @Test
    public void testProcessLine() throws Exception {
        String line = "asfashwrh";
        ProcessingContext context = new ProcessingContext.Builder(contextMock, null).build();
        assertEquals(line, LineStringReplacer.INSTANCE.preprocessLine(line, context));
    }

    @Test
    public void testProcessWithReplace() throws Exception {
        String line = "\\section{i'm section}";
        ProcessingContext context = new ProcessingContext.Builder(contextMock, null).build();
        context.defineReplaceString("\\\\section", "\\\\subsection");
        assertEquals("\\subsection{i'm section}", LineStringReplacer.INSTANCE.preprocessLine(line, context));
    }
}
