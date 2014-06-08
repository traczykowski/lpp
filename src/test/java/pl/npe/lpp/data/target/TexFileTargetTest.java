package pl.npe.lpp.data.target;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 12:30
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Scanner.class)
public class TexFileTargetTest extends TexFileTarget {

    @Mock
    private UserChoiceResolver choiceResolverMock;

    private static final Path RESULT_PATH = Paths.get("texwritertest.tex.lpp");

    public TexFileTargetTest() {
        super(RESULT_PATH);
    }


    @Before
    public void setUp() throws Exception {
        super.choiceResolver = choiceResolverMock;
        reset(choiceResolverMock);
    }

    @After
    public void tearDown() throws Exception {
        if(Files.exists(RESULT_PATH)){
            Files.delete(RESULT_PATH);
        }
    }

    @Test
    public void testWriteFileWithOverwrite() throws Exception {
        String content = "the content";
        Files.createFile(RESULT_PATH);
        when(choiceResolverMock.overwriteFile()).thenReturn(true);
        assertTrue(super.save(content.getBytes()));
    }

    @Test
    public void testWriteFileWithoutOverwrite() throws Exception {
        String content = "the content";
        verify(choiceResolverMock, never()).overwriteFile();
        assertTrue(super.save(content.getBytes()));
    }

    @Test
    public void testWriteFileUserDisbandedOperation() throws Exception {
        String content = "the content";
        Files.createFile(RESULT_PATH);
        when(choiceResolverMock.overwriteFile()).thenReturn(false);
        assertFalse(super.save(content.getBytes()));
    }


}
