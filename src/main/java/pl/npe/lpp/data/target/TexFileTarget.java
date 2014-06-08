package pl.npe.lpp.data.target;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:41
 */
public class TexFileTarget implements TexTarget{

    private final Path targetPath;
    UserChoiceResolver choiceResolver = new UserChoiceResolver();

    public TexFileTarget(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public boolean save(byte[] data) throws IOException {
        if(Files.exists(targetPath)){
            return handleExistingOutputFile(targetPath, data);
        }else {
            Path output = Files.createFile(targetPath);
            Files.write(output, data);
            return true;
        }
    }

    void write(Path dstPath, byte[] data) throws IOException {
        Files.write(dstPath, data, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

    boolean handleExistingOutputFile(Path dstPath, byte[] data) throws IOException {
        if(choiceResolver.overwriteFile()){
            write(dstPath, data);
            return true;
        }else {
            return false;
        }
    }

}
