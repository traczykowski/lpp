package pl.npe.lpp.preprocessor.line;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.01.14
 * Time: 16:15
 */
public class ParametersParserTest {

    @Test
    public void testParseParameters() throws Exception {
        List<String> result = ParametersParser.parseParameters("(first,second)");
        assertEquals(Arrays.asList("first","second"), result);

        result = ParametersParser.parseParameters("(first,\"second\")");
        assertEquals(Arrays.asList("first","second"), result);

        result = ParametersParser.parseParameters("(\"firs,t\" , \"second\")");
        assertEquals(Arrays.asList("firs,t","second"), result);

        result = ParametersParser.parseParameters("(\"firs)t\" , \"second\")");
        assertEquals(Arrays.asList("firs)t","second"), result);

        result = ParametersParser.parseParameters("(\"firs\\\"t\" , \"second\")");
        assertEquals(Arrays.asList("firs\"t","second"), result);

        result = ParametersParser.parseParameters("(  )");
        assertTrue( result.isEmpty());
    }
}
