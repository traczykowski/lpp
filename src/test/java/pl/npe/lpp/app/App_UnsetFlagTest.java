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
 * Time: 13:44
 */
public class App_UnsetFlagTest {

    private static final String PATH_PREFIX = "src/test/resources/test/unsetflag/";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get("result.lpp"));
    }

    @Test
    public void testUnsetFlag_FlagSet() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "unsetflag.tex","-output","result.lpp"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "unsetflag_expected1.tex"), Paths.get("result.lpp"));
    }
}
