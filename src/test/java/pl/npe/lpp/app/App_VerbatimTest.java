package pl.npe.lpp.app;

import org.junit.After;
import org.junit.Test;
import pl.npe.lpp.App;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 11:17
 */
public class App_VerbatimTest {

    private static final String PATH_PREFIX = "src/test/resources/test/verbatim/";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get("result.lpp"));
    }

    @Test
    public void testVerbatim() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "verbatim.tex","-output","result.lpp","-flags","default"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "verbatim_expected1.tex"), Paths.get("result.lpp"));
    }
}
