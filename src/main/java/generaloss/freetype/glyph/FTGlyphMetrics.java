package generaloss.freetype.glyph;

import generaloss.freetype.FTLibrary;
import generaloss.freetype.FTObject;
import generaloss.freetype.FTPos;

public class FTGlyphMetrics extends FTObject {

    public FTGlyphMetrics(long pointer) {
        super(pointer);
    }


    private static native int getWidth(long pointer);

    /** The glyph's width. */
    public FTPos getWidth() {
        final int raw = getWidth(super.pointer);
        return new FTPos(raw);
    }


    private static native int getHeight(long pointer);

    /** The glyph's height. */
    public FTPos getHeight() {
        final int raw = getHeight(super.pointer);
        return new FTPos(raw);
    }


    private static native int getHoriBearingX(long pointer);

    /** Left side bearing for horizontal layout. */
    public FTPos getHoriBearingX() {
        final int raw = getHoriBearingX(super.pointer);
        return new FTPos(raw);
    }


    private static native int getHoriBearingY(long pointer);

    /** Top side bearing for horizontal layout. */
    public FTPos getHoriBearingY() {
        final int raw = getHoriBearingY(super.pointer);
        return new FTPos(raw);
    }


    private static native int getHoriAdvance(long pointer);

    /** Advance width for horizontal layout. */
    public FTPos getHoriAdvance() {
        final int raw = getHoriAdvance(super.pointer);
        return new FTPos(raw);
    }


    private static native int getVertBearingX(long pointer);

    /** Left side bearing for vertical layout. */
    public FTPos getVertBearingX() {
        final int raw = getVertBearingX(super.pointer);
        return new FTPos(raw);
    }


    private static native int getVertBearingY(long pointer);

    /** Top side bearing for vertical layout. Larger positive values mean further below the vertical glyph origin. */
    public FTPos getVertBearingY() {
        final int raw = getVertBearingY(super.pointer);
        return new FTPos(raw);
    }


    private static native int getVertAdvance(long pointer);

    /** Advance height for vertical layout. Positive values mean the glyph has a positive advance downward. */
    public FTPos getVertAdvance() {
        final int raw = getVertAdvance(super.pointer);
        return new FTPos(raw);
    }

}