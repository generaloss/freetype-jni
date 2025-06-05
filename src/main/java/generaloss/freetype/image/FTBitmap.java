package generaloss.freetype.image;

import generaloss.freetype.FTStruct;

import java.nio.ByteBuffer;

public class FTBitmap extends FTStruct {

    public FTBitmap(long pointer) {
        super(pointer);
    }


    // unsigned int rows;
    private static native long getRows(long pointer);

    public long getRows() {
        return getRows(super.pointer);
    }

    // unsigned int width;
    private static native long getWidth(long pointer);

    public long getWidth() {
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
    private static native short getPixelMode(long pointer);

    public FTPixelMode getPixelMode() {
        final short raw = getPixelMode(super.pointer);
        return FTPixelMode.byValue(raw);
    }

    // unsigned char palette_mode;
    private static native short getPaletteMode(long pointer);

    @Deprecated
    public short getPaletteMode() {
        return getPaletteMode(super.pointer);
    }

    // void* palette;
    private static native long getPalettePointer(long pointer);

    @Deprecated
    public long getPalettePointer() {
        return getPalettePointer(super.pointer);
    }

}