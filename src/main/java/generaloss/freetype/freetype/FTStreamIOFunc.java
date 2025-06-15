package generaloss.freetype.freetype;

import java.io.IOException;

@FunctionalInterface
public interface FTStreamIOFunc {

    long read(long offset, byte[] buffer, long count) throws IOException;

}
