package pl.npe.lpp.data.source;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 14:59
 */
public interface TexSource {

    String readLine() throws IOException;

    String getSourceName();

}
