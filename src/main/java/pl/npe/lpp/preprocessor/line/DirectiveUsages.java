package pl.npe.lpp.preprocessor.line;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 15:03
 */
public class DirectiveUsages implements Iterable<DirectiveUsage>{

    private final String line;

    public DirectiveUsages(String line) {
        this.line = line;
    }

    @Override
    public Iterator<DirectiveUsage> iterator() {
        return new DirectiveUsageIterator(line);
    }

    private final static class DirectiveUsageIterator implements Iterator<DirectiveUsage>{

        private static final String COMPLEX_PARAM = "(?:\")(\\\\\"|[^\\\"])*(?:\")";
        private static final String PARAM_LIST = "(\\s*((\\w+)|("+COMPLEX_PARAM+"))\\s*[,)])*";
        private static final Pattern DIRECTIVE_USE = Pattern.compile("lpp\\s*\\[\\s*(\\w+)\\s*\\((\\s*\\)|"+PARAM_LIST+")\\s*\\]");

        private final Matcher matcher;

        private DirectiveUsageIterator(String line) {
            matcher = DIRECTIVE_USE.matcher(line);
        }

        private boolean nextInvoked;
        private boolean nextAvailable;

        @Override
        public boolean hasNext() {
            nextInvoked = true;
            return (nextAvailable = matcher.find());
        }

        @Override
        public DirectiveUsage next() {
            if(!nextInvoked){
                nextAvailable = matcher.find();
            }else {
                nextInvoked = false;
            }

            if(nextAvailable) {
                String directive = matcher.group(1);
                String params = matcher.group(2);
                return new DirectiveUsage(directive, ParametersParser.parseParameters(params));
            }else{
                return null;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
