package pl.npe.lpp;

import pl.npe.lpp.preprocessor.source.ProcessingContext;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 31.10.13
 * Time: 19:24
 */
public class LPPException extends Exception{

    private String file;
    private Integer line;

    protected LPPException(ProcessingContext context, String message) {
        super(message);
        file = context.getTexSource().getSourceName();
        line = context.getLineNumber();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[")
                .append(file)
                .append("]{")
                .append(line)
                .append("}:")
                .append(getMessage())
                .toString();
    }
}
