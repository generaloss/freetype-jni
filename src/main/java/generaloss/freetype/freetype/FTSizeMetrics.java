package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTFixed;
import generaloss.freetype.types.FTPos;

public class FTSizeMetrics extends FTStruct { // struct done.

    public FTSizeMetrics(long pointer) {
        super(pointer);
    }


    // FT_UShort x_ppem;
    private static native long getXppem(long pointer);

    public long getXppem() {
        return getXppem(super.pointer);
    }

    // FT_UShort y_ppem;
    private static native long getYppem(long pointer);

    public long getYppem() {
        return getYppem(super.pointer);
    }

    // FT_Fixed x_scale;
    private static native long getXScale(long pointer);

    public float getXScale() {
        final long raw = getXScale(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed y_scale;
    private static native long getYScale(long pointer);

    public float getYScale() {
        final long raw = getYScale(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Pos ascender;
    private static native long getAscender(long pointer);

    public float getAscender() {
        final long raw = getAscender(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos descender;
    private static native long getDescender(long pointer);

    public float getDescender() {
        final long raw = getDescender(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos height;
    private static native long getHeight(long pointer);

    public float getHeight() {
        final long raw = getHeight(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos max_advance;
    private static native long getMaxAdvance(long pointer);

    public float getMaxAdvance() {
        final long raw = getMaxAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTSizeMetrics newInstance() {
        return new FTSizeMetrics(newStruct());
    }

}