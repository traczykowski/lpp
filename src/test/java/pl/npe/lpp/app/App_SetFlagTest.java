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
 * Time: 11:22
 */
public class App_SetFlagTest {

    private static final String PATH_PREFIX = "src/test/resources/test/setflag/";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get("result.lpp"));
    }

    @Test
    public void testSetFlag_FlagSet() throws Exception {
        String[] args = {"-file", PATH_PREFIX + "setflag.tex","-output","result.lpp"};
        App.main(args);
        AppTestUtil.assertEquality(Paths.get(PATH_PREFIX + "setflag_expected1.tex"), Paths.get("result.lpp"));
    }
}
