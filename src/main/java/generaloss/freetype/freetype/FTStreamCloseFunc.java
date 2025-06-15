package generaloss.freetype.freetype;

import java.io.IOException;

@FunctionalInterface
public interface FTStreamCloseFunc {

    void close() throws IOException;

}
