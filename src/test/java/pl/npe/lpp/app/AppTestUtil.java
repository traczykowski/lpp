package pl.npe.lpp.app;

import org.junit.Assert;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 11:12
 */
public class AppTestUtil {

    public static void assertEquality(Path expected, Path result) throws IOException {
        byte[] encoded = Files.readAllBytes(result);
        String resultStr =  new String(encoded, Charset.defaultCharset());

        encoded = Files.readAllBytes(expected);
        String expectedStr =  new String(encoded, Charset.defaultCharset());

        Assert.assertEquals(expectedStr, resultStr);
    }
}
