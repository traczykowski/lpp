package pl.npe.lpp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 19:52
 */
public class LppVersionTest {

    @Test
    public void testGetVersion() throws Exception {
        Assert.assertEquals("1.0", LppVersion.getVersion());

    }
}
