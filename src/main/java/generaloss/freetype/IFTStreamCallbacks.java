package generaloss.freetype;

import java.io.IOException;
import java.io.InputStream;

public interface IFTStreamCallbacks {

    int read(byte[] buffer, int size) throws IOException;
    
    long skip(long n) throws IOException;

    void seek(long offset) throws IOException;

    boolean eof() throws IOException;

    static IFTStreamCallbacks create(InputStream stream) {
        try{
            return new RandomAccessFTStream(stream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}