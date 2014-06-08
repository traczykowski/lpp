package pl.npe.lpp.data.target;

import org.junit.Test;
import pl.npe.lpp.data.target.UserChoiceResolver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 13:54
 */
public class UserChoiceResolverTest {

    private final UserChoiceResolver resolver = new UserChoiceResolver();

    @Test
    public void testOverWriteTrue() throws Exception {
        assertTrue(resolver.decode((int)'y'));
        assertTrue(resolver.decode((int)'Y'));
    }

    @Test
    public void testOverWriteFalse() throws Exception {
        assertFalse(resolver.decode((int) 'n'));
        assertFalse(resolver.decode((int)'t'));
    }
}
