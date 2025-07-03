package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTFixed;
import generaloss.freetype.types.FTPos;
import generaloss.freetype.types.PosType;

public class FTSizeMetrics extends FTStruct { // struct done.

    public FTSizeMetrics(long pointer) {
        super(pointer);
    }


    // FT_UShort x_ppem;
    private static native int getXPpem(long pointer);

    public int getXPpem() {
        return getXPpem(super.pointer);
    }

    // FT_UShort y_ppem;
    private static native int getYPpem(long pointer);

    public int getYPpem() {
        return getYPpem(super.pointer);
    }

    // FT_Fixed x_scale;
    private static native int getXScale(long pointer);

    public float getXScale() {
        final int raw = getXScale(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed y_scale;
    private static native int getYScale(long pointer);

    public float getYScale() {
        final int raw = getYScale(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Pos ascender;
    private static native int getAscender(long pointer);

    public float getAscender() {
        final int raw = getAscender(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos descender;
    private static native int getDescender(long pointer);

    public float getDescender() {
        final int raw = getDescender(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos height;
    private static native int getHeight(long pointer);

    public float getHeight() {
        final int raw = getHeight(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos max_advance;
    private static native int getMaxAdvance(long pointer);

    public float getMaxAdvance() {
        final int raw = getMaxAdvance(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

}