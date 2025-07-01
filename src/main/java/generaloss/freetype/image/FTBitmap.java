package generaloss.freetype.image;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

import java.nio.ByteBuffer;

public class FTBitmap extends FTStruct {

    public FTBitmap(long pointer) {
        super(pointer);
    }

    public FTBitmap() {
        this(createPointer());
    }

    static {
        FreeType.init();
    }

    private static native long createPointer();

    private static native void freePointer(long pointer);

    public void free() {
        freePointer(this.pointer);
        super.destroyPointer();
    }


    // unsigned int rows;
    private static native long getRows(long pointer);

    public long getRows() {
        return getRows(super.pointer);
    }

    private static native void setRows(long pointer, long rows);

    public void setRows(long rows) {
        setRows(super.pointer, rows);
    }

    // unsigned int width;
    private static native long getWidth(long pointer);

    public long getWidth() {
        return getWidth(super.pointer);
    }

    private static native void setWidth(long pointer, long width);

    public void setWidth(long width) {
        setWidth(super.pointer, width);
    }

    // int pitch;
    private static native int getPitch(long pointer);

    public int getPitch() {
        return getPitch(super.pointer);
    }

    private static native void setPitch(long pointer, int pitch);

    public void setPitch(int pitch) {
        setPitch(super.pointer, pitch);
    }

    // unsigned char* buffer;
    private static native ByteBuffer getBuffer(long pointer);

    public ByteBuffer getBuffer() {
        if(this.getRows() == 0)
            return ByteBuffer.allocateDirect(0);
        return getBuffer(super.pointer);
    }

    private static native void setBuffer(long pointer, ByteBuffer buffer);

    public void setBuffer(ByteBuffer buffer) {
        setBuffer(super.pointer, buffer);
    }

    private static native void setBuffer(long pointer, long size);

    public void setBuffer(long size) {
        setBuffer(super.pointer, size);
    }

    public void setBuffer(long rows, int pitch) {
        final long size = (rows * pitch);
        this.setBuffer(size);
    }

    public void setBuffer() {
        this.setBuffer(this.getRows(), this.getPitch());
    }

    // unsigned short num_grays;
    private static native int getNumGrays(long pointer);

    public int getNumGrays() {
        return getNumGrays(super.pointer);
    }

    private static native void setNumGrays(long pointer, int num_grays);

    public void setNumGrays(int numGrays) {
        setNumGrays(super.pointer, numGrays);
    }

    // unsigned char pixel_mode;
    private static native short getPixelMode(long pointer);

    public FTPixelMode getPixelMode() {
        final short raw = getPixelMode(super.pointer);
        return FTPixelMode.byValue(raw);
    }

    private static native void setPixelMode(long pointer, int pixel_mode);

    public void setPixelMode(FTPixelMode pixelMode) {
        setPixelMode(super.pointer, pixelMode.value);
    }

    // unsigned char palette_mode;
    private static native short getPaletteMode(long pointer);

    public short getPaletteMode() {
        return getPaletteMode(super.pointer);
    }

    // void* palette;
    private static native long getPalettePointer(long pointer);

    public long getPalettePointer() {
        return getPalettePointer(super.pointer);
    }

}