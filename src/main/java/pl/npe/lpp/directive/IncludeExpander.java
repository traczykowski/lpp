package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.line.LineProcessorFactory;
import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;
import pl.npe.lpp.preprocessor.source.SourceProcessor;
import pl.npe.lpp.preprocessor.source.SourceProcessorFactory;
import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.source.TexSourceFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 15:32
 */
class IncludeExpander extends AbstractExpander {

    SourceProcessorFactory sourceProcessorFactory = new SourceProcessorFactory();
    TexSourceFactory texSourceFactory = new TexSourceFactory();
    LineProcessorFactory lineProcessorFactory = new LineProcessorFactory();

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        int paramsSize = params.size();
        if(paramsSize !=  2 && paramsSize != 3){
            throw new ExpanderException(context, "Invalid parameter count: expected = 2 or 3 passed = " + paramsSize);
        }

        if(paramsSize == 3) {
            try {
                Charset.forName(params.get(2));
            } catch (IllegalArgumentException e) {
                throw new ExpanderException(context, "Invalid charset: " + params.get(2));
            }
        }
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) throws CannotReadFromFileException, UnknownDirectiveException, ExpanderException {
        ProcessingContext newContext = getContext(params, context);
        return expand(newContext);
    }

    ProcessingContext getContext(List<String> params, ProcessingContext context) throws ExpanderException {
        Boolean inheritContext = Boolean.valueOf(params.get(1));
        TexSource texSource;
        try {
            Path path = getPath(params.get(0), context);
            texSource = texSourceFactory.build(context.getCharset(), path);
        } catch (IOException e) {
            throw new ExpanderException(context, e.getMessage());
        }
        if(inheritContext){
            return new ProcessingContext.Builder(context, texSource).build();
        }else {
            Charset charset = getCharset(params);
            return new ProcessingContext.Builder(texSource, charset).build();
        }
    }

    String expand(ProcessingContext context) throws UnknownDirectiveException, CannotReadFromFileException, ExpanderException {
        SourceProcessor sourceProcessor = sourceProcessorFactory.build();
        return sourceProcessor.processSource(context, lineProcessorFactory.build());
    }


    Charset getCharset(List<String> params){
        if(params.size() == 3){
            return Charset.forName(params.get(2));
        }else {
            return Charset.defaultCharset();
        }
    }

    Path getPath(String param, ProcessingContext context) throws ExpanderException {
        try {
            return Paths.get(param).toAbsolutePath();
        }catch (RuntimeException e){
            throw new ExpanderException(context, "Cannot get file: " + param);
        }
    }
}
