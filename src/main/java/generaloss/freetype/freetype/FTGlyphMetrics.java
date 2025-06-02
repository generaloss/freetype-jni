package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTPos;

public class FTGlyphMetrics extends FTStruct { // struct done.

    public FTGlyphMetrics(long pointer) {
        super(pointer);
    }


    // FT_Pos width;
    private static native long getWidth(long pointer);

    public float getWidth() {
        final long raw = getWidth(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos height;
    private static native long getHeight(long pointer);

    public float getHeight() {
        final long raw = getHeight(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiBearingX;
    private static native long getHoriBearingX(long pointer);

    public float getHoriBearingX() {
        final long raw = getHoriBearingX(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiBearingY;
    private static native long getHoriBearingY(long pointer);

    public float getHoriBearingY() {
        final long raw = getHoriBearingY(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiAdvance;
    private static native long getHoriAdvance(long pointer);

    public float getHoriAdvance() {
        final long raw = getHoriAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertBearingX;
    private static native long getVertBearingX(long pointer);

    public float getVertBearingX() {
        final long raw = getVertBearingX(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertBearingY;
    private static native long getVertBearingY(long pointer);

    public float getVertBearingY() {
        final long raw = getVertBearingY(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertAdvance;
    private static native long getVertAdvance(long pointer);

    public float getVertAdvance() {
        final long raw = getVertAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTGlyphMetrics newInstance() {
        return new FTGlyphMetrics(newStruct());
    }

}