package pl.npe.lpp.preprocessor.source;

import pl.npe.lpp.preprocessor.LppParams;
import pl.npe.lpp.preprocessor.line.LinePreprocessorChain;
import pl.npe.lpp.data.source.TexSource;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 12:04
 */
public class ProcessingContext {

    private TexSource texSource;

    private boolean includeText;

    private Set<String> flags = new LinkedHashSet<>();

    private Map<String, String> replacements = new HashMap<>();

    private LinePreprocessorChain chain;

    private int lineNumber;

    private boolean inherited;

    private Charset charset;

    private ProcessingContext() {
    }

    public boolean isIncludeText() {
        return includeText;
    }

    public void setIncludeText(boolean includeText) {
        this.includeText = includeText;
    }

    public boolean setFlag(String flag){
        return flags.add(flag);
    }
    public boolean unsetFlag(String flag){
        return flags.remove(flag);
    }

    public boolean isFlagSet(String flag){
        return flags.contains(flag);
    }

    public void defineReplaceString(String from, String to){
        replacements.put(from, to);
    }

    public Map<String, String> getReplacements(){
        return Collections.unmodifiableMap(replacements);
    }

    public LinePreprocessorChain getChain() {
        return chain;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void incrementLineNumber(){
        lineNumber++;
    }

    public Set<String> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    public boolean isInherited() {
        return inherited;
    }

    public Charset getCharset() {
        return charset;
    }

    public TexSource getTexSource() {
        return texSource;
    }

    public static class Builder{

        private final ProcessingContext newContext;

        public Builder(ProcessingContext context, TexSource texSource) {
            this.newContext = new ProcessingContext();
            this.newContext.flags = context.flags;
            this.newContext.chain = context.chain;
            this.newContext.charset = context.charset;
            this.newContext.replacements = context.replacements;
            this.newContext.texSource = texSource;
            this.newContext.inherited = true;
        }

        public Builder(LppParams lppParams){
            this.newContext = new ProcessingContext();
            if(!lppParams.getFlags().isEmpty()) {   //LppParams can have unmodifiable collection
                this.newContext.flags = lppParams.getFlags();
            }
            this.newContext.chain = new LinePreprocessorChain();
            this.newContext.charset = lppParams.getCharset();
            this.newContext.texSource = lppParams.getTexSource();
            this.newContext.inherited = false;
        }

        public Builder(TexSource texSource, Charset charset){
            this.newContext = new ProcessingContext();
            this.newContext.chain = new LinePreprocessorChain();
            newContext.texSource = texSource;
            newContext.charset = charset;
            newContext.inherited = false;
        }

        public Builder chain(LinePreprocessorChain chain){
            this.newContext.chain = chain;
            return this;
        }

        public ProcessingContext build(){
            return newContext;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Flags: ")
                .append(this.getFlags())
                .append("\n")
                .append("Line number: ")
                .append(this.getLineNumber())
                .append("\n")
                .append("Source name: ")
                .append(this.getTexSource().getSourceName())
                .append("\n")
                .append("Replacements: ")
                .append(this.getReplacements())
                .append("\n")
                .append("Chain: ")
                .append(this.getChain())
                .append("\n")
                .append("Charset: ")
                .append(this.getCharset())
                .append("\n")
                .append("Is inherited: ")
                .append(this.isInherited())
                .append("\n")
                .append("Include text?: ")
                .append(this.isIncludeText())
                .append("\n")
                .toString();
    }
}
