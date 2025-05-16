package generaloss.freetype.glyph;

import generaloss.freetype.FTLibrary;
import generaloss.freetype.FTObject;

public class FTGlyphMetrics extends FTObject { // fully implemented

    public FTGlyphMetrics(long pointer) {
        super(pointer);
    }


    private static native int getWidth(long pointer);

    /** The glyph's width. */
    public int getWidth() {
        final int raw = getWidth(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getHeight(long pointer);

    /** The glyph's height. */
    public int getHeight() {
        final int raw = getHeight(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getHoriBearingX(long pointer);

    /** Left side bearing for horizontal layout. */
    public int getHoriBearingX() {
        final int raw = getHoriBearingX(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getHoriBearingY(long pointer);

    /** Top side bearing for horizontal layout. */
    public int getHoriBearingY() {
        final int raw = getHoriBearingY(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getHoriAdvance(long pointer);

    /** Advance width for horizontal layout. */
    public int getHoriAdvance() {
        final int raw = getHoriAdvance(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getVertBearingX(long pointer);

    /** Left side bearing for vertical layout. */
    public int getVertBearingX() {
        final int raw = getVertBearingX(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getVertBearingY(long pointer);

    /** Top side bearing for vertical layout. Larger positive values mean further below the vertical glyph origin. */
    public int getVertBearingY() {
        final int raw = getVertBearingY(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getVertAdvance(long pointer);

    /** Advance height for vertical layout. Positive values mean the glyph has a positive advance downward. */
    public int getVertAdvance() {
        final int raw = getVertAdvance(super.pointer);
        return FTLibrary.FTPos_toInt(raw);
    }

}