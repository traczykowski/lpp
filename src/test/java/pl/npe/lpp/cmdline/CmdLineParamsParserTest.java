package pl.npe.lpp.cmdline;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.npe.lpp.preprocessor.LppParams;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 04.06.14
 * Time: 18:04
 */


@RunWith(MockitoJUnitRunner.class)
public class CmdLineParamsParserTest {

    private final CmdLineParamsParser parser = new CmdLineParamsParser();

    @Mock
    private CommandLine commandLine;

    private static final Path FILE_PATH = Paths.get("dummy.tex");
    private static final String SOURCE_FILE = FILE_PATH.toFile().getAbsolutePath();

    @Test
    public void testParseSuccess() throws Exception {
        String charset = "US-ASCII";
        String rootFile = "dummy.tex";
        String flags = "flag1,flag2";
        String[] args = {"lpp", "-charset", charset, "-file", rootFile, "-flags", flags};
        LppParams params = parser.parse(args);
        assertEquals(SOURCE_FILE, params.getTexSource().getSourceName());
        assertEquals(charset, params.getCharset().name());
        assertEquals(new HashSet<>(Arrays.asList("flag1", "flag2")), params.getFlags());
    }

    @Test
    public void testParseSuccessWithDefaultCharset() throws Exception {
        String charset = Charset.defaultCharset().name();
        String rootFile = "dummy.tex";
        String[] args = {"-file", rootFile};
        LppParams params = parser.parse(args);
        assertEquals(SOURCE_FILE, params.getTexSource().getSourceName());
        assertEquals(charset, params.getCharset().name());
    }

    @Test(expected = MissingOptionException.class)
    public void testParseFailureMissingRootFile() throws Exception {
        String[] args = {"lpp", "-charset", "UTF-8"};
        parser.parse(args);
    }

    @Test
    public void testGetFlagsSingle() throws Exception {
        String arg = "theflag";
        when(commandLine.getOptionValue(LppOption.FLAGS.getOptionName())).thenReturn(arg);
        Set<String> flags = parser.getFlags(commandLine);
        assertEquals(new HashSet<>(Arrays.asList(arg)), flags);
    }

    @Test
    public void testGetFlagsMultiple() throws Exception {
        String flag1 = "theflag";
        String flag2 = "anotherflag";
        String arg = flag1 + "," + flag2;
        when(commandLine.getOptionValue(LppOption.FLAGS.getOptionName())).thenReturn(arg);
        Set<String> flags = parser.getFlags(commandLine);
        assertEquals(new HashSet<>(Arrays.asList(flag1, flag2)), flags);
    }

    @Test
    public void testGetFlagsNone() throws Exception {
        when(commandLine.getOptionValue(LppOption.FLAGS.getOptionName())).thenReturn(null);
        Set<String> flags = parser.getFlags(commandLine);
        assertEquals(Collections.<String>emptySet(), flags);
    }

    @Test
    public void testGetCharsetCustom() throws Exception {
        String arg = "US-asCII";
        when(commandLine.getOptionValue(LppOption.CHARSET.getOptionName())).thenReturn(arg);
        Charset charset = parser.getCharset(commandLine);
        assertEquals(Charset.forName(arg), charset);
    }

    @Test
    public void testGetCharsetDefault() throws Exception {
        when(commandLine.getOptionValue(LppOption.CHARSET.getOptionName())).thenReturn(null);
        Charset charset = parser.getCharset(commandLine);
        assertEquals(Charset.defaultCharset(), charset);
    }

    @Test(expected = ParseException.class)
    public void testGetCharsetInvalid() throws Exception {
        String arg = "abc";
        when(commandLine.getOptionValue(LppOption.CHARSET.getOptionName())).thenReturn(arg);
        parser.getCharset(commandLine);
    }

    @Test
    public void testIsRootFileValidTrue() throws Exception {
        assertTrue(parser.isRootFileValid(FILE_PATH));
    }

    @Test
    public void testIsRootFileValidFalse() throws Exception {
        assertFalse(parser.isRootFileValid(Paths.get("notexisting.tex")));
    }

    @Test
    public void testGetRootFileOk() throws Exception {
        when(commandLine.getOptionValue(LppOption.ROOT_FILE.getOptionName())).thenReturn("dummy.tex");
        assertEquals(FILE_PATH, parser.getRootFile(commandLine));
    }

    @Test(expected = ParseException.class)
    public void testGetRootFileError() throws Exception {
        when(commandLine.getOptionValue(LppOption.ROOT_FILE.getOptionName())).thenReturn("notexisting.tex");
         parser.getRootFile(commandLine);
    }

    @Test
    public void testGetTargetFileDefault() throws Exception {
        when(commandLine.getOptionValue(LppOption.OUTPUT_FILE.getOptionName())).thenReturn(null);
        Path expected = Paths.get(FILE_PATH.toFile().getAbsolutePath() + ".lpp");
        assertEquals(expected, parser.getTargetFile(commandLine, FILE_PATH));
    }

    @Test
    public void testGetTargetFileCustom() throws Exception {
        Path expected = Paths.get("custom_file.tex");
        when(commandLine.getOptionValue(LppOption.OUTPUT_FILE.getOptionName())).thenReturn(expected.toFile().toString());
        assertEquals(expected.toAbsolutePath(), parser.getTargetFile(commandLine, FILE_PATH));
    }

    @Test
    public void testIsOverwriteTrue() throws Exception {
        when(commandLine.hasOption(LppOption.OVERWRITE.getOptionName())).thenReturn(true);
        assertTrue(parser.isOverwrite(commandLine));
    }

    @Test
    public void testIsOverwriteFalse() throws Exception {
        when(commandLine.hasOption(LppOption.OVERWRITE.getOptionName())).thenReturn(false);
        assertFalse(parser.isOverwrite(commandLine));
    }
}
