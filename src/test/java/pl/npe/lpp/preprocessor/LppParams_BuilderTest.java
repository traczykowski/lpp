package pl.npe.lpp.preprocessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.target.TexTarget;
import pl.npe.lpp.preprocessor.LppParams;


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 04.06.14
 * Time: 18:03
 */
@RunWith(MockitoJUnitRunner.class)
public class LppParams_BuilderTest {

    @Mock
    private TexSource texSourceMock;

    @Mock
    private TexTarget texTargetMock;

    @Test
    public void testBuild() throws Exception {
        LppParams.Builder builder = new LppParams.Builder(texSourceMock, texTargetMock);
        String charset = "UTF-8";
        builder.charset(Charset.forName(charset));
        Set<String> flags = new HashSet<>(Arrays.asList("flag1", "flag2"));
        builder.flags(flags);
        LppParams params = builder.build();
        assertEquals(texSourceMock, params.getTexSource());
        assertEquals(texTargetMock, params.getTexTarget());
        assertEquals(charset, params.getCharset().name());
        assertEquals(flags, params.getFlags());
    }
}
