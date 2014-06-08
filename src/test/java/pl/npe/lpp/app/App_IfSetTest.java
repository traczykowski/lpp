package pl.npe.lpp.app;

import org.junit.After;
import org.junit.Test;
import pl.npe.lpp.App;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 10:53
 */
public class App_IfSetTest {

    private static final String PATH_PREFIX = "src/test/resources/test/ifset/";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get("result.lpp"));
    }

    @Test
    public void testIfSet_FlagSet() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "ifset.tex","-output","result.lpp","-flags","default,flag"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "ifset_expected1.tex"), Paths.get("result.lpp"));
    }

    @Test
    public void testIfSet_FlagNotSet() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "ifset.tex","-output","result.lpp","-flags","default"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "ifset_expected2.tex"), Paths.get("result.lpp"));
    }
}
