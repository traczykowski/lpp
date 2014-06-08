package pl.npe.lpp.cmdline;

import org.apache.commons.cli.*;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.source.TexSourceFactory;
import pl.npe.lpp.data.target.TexTarget;
import pl.npe.lpp.data.target.TexTargetFactory;
import pl.npe.lpp.preprocessor.LppParams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 04.06.14
 * Time: 17:59
 */
class CmdLineParamsParser {

    private final Options options = LppOption.getOptions();
    private final TexSourceFactory texSourceFactory = new TexSourceFactory();
    private final TexTargetFactory texTargetFactory = new TexTargetFactory();

    LppParams parse(String[] args) throws ParseException, IOException {
        CommandLineParser parser = new BasicParser();
        CommandLine line = parser.parse(options, args);
        Path rootFile = getRootFile(line);
        Charset charset = getCharset(line);
        TexSource texSource = texSourceFactory.build(charset, rootFile);
        TexTarget texTarget = texTargetFactory.build(getTargetFile(line, rootFile));
        return new LppParams.Builder(texSource, texTarget)
                .charset(charset)
                .flags(getFlags(line))
                .build();
    }

    Charset getCharset(CommandLine commandLine) throws ParseException {
        String charset=null;
        try {
            charset = commandLine.getOptionValue(LppOption.CHARSET.getOptionName());
            return charset != null ? Charset.forName(charset) : Charset.defaultCharset();
        }catch (UnsupportedCharsetException e){
            throw new ParseException("Unsupported charset: " + charset);
        }

    }

    Path getRootFile(CommandLine commandLine) throws ParseException {
        Path rootFile = Paths.get(commandLine.getOptionValue(LppOption.ROOT_FILE.getOptionName()));
        if(isRootFileValid(rootFile)){
            return rootFile;
        }else {
            throw new ParseException("File: " + rootFile.toFile().getAbsolutePath() + " not exists or is not readable");
        }
    }

    Path getTargetFile(CommandLine commandLine, Path rootFile) throws ParseException {
        String custom = commandLine.getOptionValue(LppOption.OUTPUT_FILE.getOptionName());
        Path path;
        if(custom == null){
            path =  Paths.get(rootFile.toFile().getAbsolutePath() + ".lpp");
        }else {
            path = Paths.get(custom).toAbsolutePath();
        }

        if(!isTargetFileValid(path)){
            throw new ParseException("File: " + path.toFile().getAbsolutePath() + " cannot be written");
        }
        return path;
    }

    Set<String> getFlags(CommandLine commandLine){
        String arg = commandLine.getOptionValue(LppOption.FLAGS.getOptionName());
        if(arg == null){
            return Collections.emptySet();
        }else {
            String[] flags = arg.split(",");
            return new HashSet<>(Arrays.asList(flags));
        }
    }

    boolean isRootFileValid(Path path){
        return Files.exists(path) && Files.isReadable(path) && !Files.isDirectory(path);
    }

    boolean isTargetFileValid(Path path){
        return !Files.exists(path) || Files.isWritable(path);
    }

}
