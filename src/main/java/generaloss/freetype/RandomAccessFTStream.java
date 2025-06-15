package generaloss.freetype;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RandomAccessFTStream implements IFTStreamCallbacks {

    private final byte[] data;
    private int position;

    public RandomAccessFTStream(InputStream input) throws IOException {
        this.data = readAllBytes(input);
        this.position = 0;
    }

    private static byte[] readAllBytes(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] chunk = new byte[4096];
        int bytesRead;
        while ((bytesRead = input.read(chunk)) != -1) {
            buffer.write(chunk, 0, bytesRead);
        }
        return buffer.toByteArray();
    }

    @Override
    public int read(byte[] buffer, int size) {
        int length = Math.min(Math.min(buffer.length, size), data.length - position);
        if (length <= 0)
            return 0;

        System.arraycopy(data, position, buffer, 0, length);
        position += length;
        System.out.println("read " + length);
        return length;
    }

    @Override
    public void seek(long offset) {
        this.position = (int) offset;
        System.out.println("seek to " + offset);
    }

    @Override
    public long skip(long n) {
        long skip = Math.min(n, data.length - position);
        if (skip < 0) skip = 0;
        position += (int) skip;
        System.out.println("seek forward to " + position);
        return skip;
    }

    @Override
    public boolean eof() {
        return position >= data.length;
    }

    public int getSize() {
        return data.length;
    }

}