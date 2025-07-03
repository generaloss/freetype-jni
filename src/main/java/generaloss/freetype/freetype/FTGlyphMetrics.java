package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.PosType;

public class FTGlyphMetrics extends FTStruct { // struct done.

    public FTGlyphMetrics(long pointer) {
        super(pointer);
    }


    // FT_Pos width;
    private static native int getWidth(long pointer);

    public float getWidth() {
        final int raw = getWidth(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos height;
    private static native int getHeight(long pointer);

    public float getHeight() {
        final int raw = getHeight(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos horiBearingX;
    private static native int getHoriBearingX(long pointer);

    public float getHoriBearingX() {
        final int raw = getHoriBearingX(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos horiBearingY;
    private static native int getHoriBearingY(long pointer);

    public float getHoriBearingY() {
        final int raw = getHoriBearingY(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos horiAdvance;
    private static native int getHoriAdvance(long pointer);

    public float getHoriAdvance() {
        final int raw = getHoriAdvance(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos vertBearingX;
    private static native int getVertBearingX(long pointer);

    public float getVertBearingX() {
        final int raw = getVertBearingX(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos vertBearingY;
    private static native int getVertBearingY(long pointer);

    public float getVertBearingY() {
        final int raw = getVertBearingY(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

    // FT_Pos vertAdvance;
    private static native int getVertAdvance(long pointer);

    public float getVertAdvance() {
        final int raw = getVertAdvance(super.pointer);
        return PosType.F26DOT6.toFloat(raw);
    }

}