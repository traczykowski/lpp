package pl.npe.lpp.data.target;

import pl.npe.lpp.preprocessor.LppParams;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:40
 */
public interface TexTarget {

    boolean save(byte[] data, LppParams params) throws IOException;
}
