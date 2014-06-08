package pl.npe.lpp.preprocessor;

import pl.npe.lpp.data.source.TexSource;
import pl.npe.lpp.data.target.TexTarget;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 04.06.14
 * Time: 17:39
 */
public class LppParams {

    private TexSource texSource;

    private TexTarget texTarget;

    private Charset charset;

    private Set<String> flags;

    private LppParams() {

    }

    public TexSource getTexSource() {
        return texSource;
    }

    public TexTarget getTexTarget() {
        return texTarget;
    }

    public Charset getCharset() {
        return charset;
    }

    public Set<String> getFlags() {
        if(flags == null){
            return Collections.emptySet();
        }else {
            return Collections.unmodifiableSet(flags);
        }
    }

    public static class Builder{

        private final LppParams params;

        public Builder(TexSource texSource, TexTarget texTarget) {
            params = new LppParams();
            params.texSource = texSource;
            params.texTarget = texTarget;
        }

        public Builder charset(Charset charset){
            params.charset = charset;
            return this;
        }

        public Builder flags(Set<String> flags){
            params.flags = flags;
            return this;
        }

        public LppParams build(){
            return params;
        }
    }


}
