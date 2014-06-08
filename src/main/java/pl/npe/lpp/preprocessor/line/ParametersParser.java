package pl.npe.lpp.preprocessor.line;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 19.01.14
 * Time: 16:06
 */
class ParametersParser {

    private ParametersParser() {
    }

    private static final Pattern PATTERN = Pattern.compile("\\s*(([\\w\\s]+)|(\"(?:\\\\\"|[^\"])*\"))\\s*(?:[),])\\s*");

    public static List<String> parseParameters(String data){
        Matcher matcher = PATTERN.matcher(data);
        List<String> params = new ArrayList<>();
        while (matcher.find()){
            String param = matcher.group(1);
            if(param.trim().isEmpty()){
                continue;
            }
            if(param.startsWith("\"")){
                param = param.substring(1, param.length()-1);
            }
            params.add(param.replaceAll("\\\\\"", "\""));
        }
        return params;
    }
}
