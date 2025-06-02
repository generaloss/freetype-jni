package generaloss.freetype.freetype;

import generaloss.freetype.types.FTPos;
import generaloss.freetype.FTStruct;

public class FTBitmapSize extends FTStruct { // struct done.

    public FTBitmapSize(long pointer) {
        super(pointer);
    }


    // FT_Short height;
    private static native short getHeight(long pointer);

    public short getHeight() {
        return getHeight(super.pointer);
    }

    // FT_Short width;
    private static native short getWidth(long pointer);

    public short getWidth() {
        return getWidth(super.pointer);
    }

    // FT_Pos size;
    private static native long getSize(long pointer);

    public float getSize() {
        final long raw = getSize(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos x_ppem;
    private static native long getXppem(long pointer);

    public float getXppem() {
        final long raw = getXppem(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos y_ppem;
    private static native long getYppem(long pointer);

    public float getYppem() {
        final long raw = getYppem(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTBitmapSize newInstance() {
        return new FTBitmapSize(newStruct());
    }

}
