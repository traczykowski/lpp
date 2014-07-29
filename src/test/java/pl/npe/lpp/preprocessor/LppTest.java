package pl.npe.lpp.preprocessor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.target.TexTarget;
import pl.npe.lpp.preprocessor.line.LineProcessor;
import pl.npe.lpp.preprocessor.line.LineProcessorFactory;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.SourceProcessor;
import pl.npe.lpp.preprocessor.source.SourceProcessorFactory;

import java.nio.charset.Charset;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 11:04
 */
@RunWith(MockitoJUnitRunner.class)
public class LppTest extends Lpp {

    private LppParams lppParams;

    @Mock
    private SourceProcessorFactory sourceProcessorFactoryMock;

    @Mock
    private SourceProcessor sourceProcessorMock;

    @Mock
    private ProcessingContext processingContextMock;

    @Mock
    private TexSource texSourceMock;

    @Mock
    private TexTarget texTargetMock;

    @Mock
    private LineProcessorFactory lineProcessorFactoryMock;

    @Before
    public void setUp() throws Exception {
        super.sourceProcessorFactory = sourceProcessorFactoryMock;
        super.lineProcessorFactory = lineProcessorFactoryMock;
        lppParams = new LppParams.Builder(texSourceMock, texTargetMock).charset(Charset.defaultCharset()).build();
    }

    @Test
    public void testPreprocessSuccess() throws Exception {
        when(sourceProcessorFactoryMock.build()).thenReturn(sourceProcessorMock);
        when(sourceProcessorMock.processSource(any(ProcessingContext.class), any(LineProcessor.class))).thenReturn("");
        when(texTargetMock.save(eq(new byte[0]), eq(lppParams))).thenReturn(true);
        assertTrue(super.preprocess(lppParams));
    }

}
