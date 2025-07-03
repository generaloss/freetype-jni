package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.PosType;

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
    private static native int getSize(long pointer);

    public float getSize() {
        final int raw = getSize(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos x_ppem;
    private static native int getXPpem(long pointer);

    public float getXPpem() {
        final int raw = getXPpem(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos y_ppem;
    private static native int getYPpem(long pointer);

    public float getYPpem() {
        final int raw = getYPpem(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

}
