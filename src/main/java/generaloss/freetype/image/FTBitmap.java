package generaloss.freetype.image;

import generaloss.freetype.FTStruct;

import java.nio.ByteBuffer;

public class FTBitmap extends FTStruct {

    public FTBitmap(long pointer) {
        super(pointer);
    }


    // unsigned int rows;
    private static native int getRows(long pointer);

    public int getRows() {
        return getRows(super.pointer);
    }

    // unsigned int width;
    private static native int getWidth(long pointer);

    public int getWidth() {
        return getWidth(super.pointer);
    }

    // int pitch;
    private static native int getPitch(long pointer);

    public int getPitch() {
        return getPitch(super.pointer);
    }

    // unsigned char* buffer;
    private static native ByteBuffer getBuffer(long pointer);

    public ByteBuffer getBuffer() {
        if(this.getRows() == 0)
            return ByteBuffer.allocateDirect(1);
        return getBuffer(super.pointer);
    }

    // unsigned short num_grays;
    private static native int getNumGray(long pointer);

    public int getNumGray() {
        return getNumGray(super.pointer);
    }

    // unsigned char pixel_mode;
    private static native int getPixelMode(long pointer);

    public FTPixelMode getPixelMode() {
        final int raw = getPixelMode(super.pointer);
        return FTPixelMode.byValue(raw);
    }

    // unsigned char palette_mode;

    // void* palette;


    private static native long newStruct();

    public static FTBitmap newInstance() {
        return new FTBitmap(newStruct());
    }

}