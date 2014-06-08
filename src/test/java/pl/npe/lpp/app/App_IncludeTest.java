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
 * Time: 13:53
 */
public class App_IncludeTest {

    private static final String PATH_PREFIX = "src/test/resources/test/include/";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get("result.lpp"));
    }

    @Test
    public void testInclude_nherited() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "include_inherited.tex","-output","result.lpp","-flags","default"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "include_expected1.tex"), Paths.get("result.lpp"));
    }

    @Test
    public void testInclude_NotInherited() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "include_notinherited.tex","-output","result.lpp","-flags","default"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "include_expected2.tex"), Paths.get("result.lpp"));
    }
}
