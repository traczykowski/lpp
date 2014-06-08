package pl.npe.lpp.data.target;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 15:40
 */
public interface TexTarget {

    boolean save(byte[] data) throws IOException;
}
