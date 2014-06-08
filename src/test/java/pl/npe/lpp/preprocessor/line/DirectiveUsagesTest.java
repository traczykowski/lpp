package pl.npe.lpp.preprocessor.line;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 15:28
 */
public class DirectiveUsagesTest {

    @Test
    public void test1() throws Exception {
        DirectiveUsages usages = new DirectiveUsages("%this is a comment  lpp [replace (\"section\",\"subsection\")]     lpp[CONTEXTDUMP( )] %lpp[ include(\"src/test/resources/test/include/include_part.tex\" , true) ]");
        List<DirectiveUsage> directiveUsages = new ArrayList<>();
        for(DirectiveUsage usage : usages){
            directiveUsages.add(usage);
        }

        DirectiveUsage usage1 = new DirectiveUsage("replace", Arrays.asList("section", "subsection"));
        DirectiveUsage usage2 = new DirectiveUsage("CONTEXTDUMP", Collections.<String>emptyList());
        DirectiveUsage usage3 = new DirectiveUsage("include", Arrays.asList("src/test/resources/test/include/include_part.tex", "true"));
        List<DirectiveUsage> expected = new ArrayList<>();
        expected.add(usage1);
        expected.add(usage2);
        expected.add(usage3);
        assertEquals(expected, directiveUsages);
    }

    @Test
    public void test2() throws Exception {
        DirectiveUsages usages = new DirectiveUsages("%this is a comment    ");
        List<DirectiveUsage> directiveUsages = new ArrayList<>();
        for(DirectiveUsage usage : usages){
            directiveUsages.add(usage);
        }

        assertEquals(Collections.<DirectiveUsage>emptyList(), directiveUsages);
    }
}
