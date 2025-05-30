package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTPos;

public class FTGlyphMetrics extends FTStruct { // struct done.

    public FTGlyphMetrics(long pointer) {
        super(pointer);
    }


    // FT_Pos width;
    private static native int getWidth(long pointer);

    /** The glyph's width. */
    public float getWidth() {
        final int raw = getWidth(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos height;
    private static native int getHeight(long pointer);

    /** The glyph's height. */
    public float getHeight() {
        final int raw = getHeight(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiBearingX;
    private static native int getHoriBearingX(long pointer);

    /** Left side bearing for horizontal layout. */
    public float getHoriBearingX() {
        final int raw = getHoriBearingX(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiBearingY;
    private static native int getHoriBearingY(long pointer);

    /** Top side bearing for horizontal layout. */
    public float getHoriBearingY() {
        final int raw = getHoriBearingY(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos horiAdvance;
    private static native int getHoriAdvance(long pointer);

    /** Advance width for horizontal layout. */
    public float getHoriAdvance() {
        final int raw = getHoriAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertBearingX;
    private static native int getVertBearingX(long pointer);

    /** Left side bearing for vertical layout. */
    public float getVertBearingX() {
        final int raw = getVertBearingX(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertBearingY;
    private static native int getVertBearingY(long pointer);

    /** Top side bearing for vertical layout. Larger positive values mean further below the vertical glyph origin. */
    public float getVertBearingY() {
        final int raw = getVertBearingY(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos vertAdvance;
    private static native int getVertAdvance(long pointer);

    /** Advance height for vertical layout. Positive values mean the glyph has a positive advance downward. */
    public float getVertAdvance() {
        final int raw = getVertAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTGlyphMetrics newInstance() {
        return new FTGlyphMetrics(newStruct());
    }

}