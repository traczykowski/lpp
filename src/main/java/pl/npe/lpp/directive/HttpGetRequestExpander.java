package pl.npe.lpp.directive;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;
import pl.npe.lpp.preprocessor.source.ProcessingContext;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 01.11.13
 * Time: 10:43
 */
public class HttpGetRequestExpander extends AbstractExpander{

    @Override
    protected void validateParams(List<String> params, ProcessingContext context) throws ExpanderException {
        if(params.size() != 1){
            throw new ExpanderException(context, "HttpGetRequestExpander directive requires one parameter");
        }

        validateURL(params.get(0), context);
    }

    @Override
    protected String expandDirective(List<String> params, ProcessingContext context) throws CannotReadFromFileException, UnknownDirectiveException, ExpanderException {
        String url = params.get(0);
        HttpGet get = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        try{
            HttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200){
                throw new ExpanderException(context, "Making GET request to " + url + " returned error status code: " + statusCode);
            }else {
                return getResponse(response);
            }
        }catch (IOException e){
            throw new ExpanderException(context, "Cannot make GET request to: " + url);
        }
    }

    String getResponse(HttpResponse response) throws IOException {
        StringWriter writer = new StringWriter();
        HttpEntity httpEntity = response.getEntity();
        IOUtils.copy(httpEntity.getContent(), writer, getCharset(httpEntity).name());
        return writer.toString();
    }

    Charset getCharset(HttpEntity httpEntity){
        ContentType contentType = ContentType.getOrDefault(httpEntity);
        Charset charset = contentType.getCharset();
        return charset != null ? charset : Charset.defaultCharset();
    }


    void validateURL(String value, ProcessingContext context) throws ExpanderException {
        try {
            new URL(value);
        } catch (MalformedURLException e) {
            throw new ExpanderException(context, "HttpGetRequestExpander directive requires valid url passed as parameter");
        }
    }



}
