package generaloss.freetype.freetype;

import generaloss.freetype.types.FTPos;
import generaloss.freetype.FTStruct;

public class FTBitmapSize extends FTStruct { // struct done.

    public FTBitmapSize(long pointer) {
        super(pointer);
    }


    // FT_Short height;
    private static native short getHeight(long pointer);

    /** The vertical distance, in pixels, between two consecutive baselines.
     * It is always positive.
     * */
    public short getHeight() {
        return getHeight(super.pointer);
    }

    // FT_Short width;
    private static native short getWidth(long pointer);

    /** The average width, in pixels, of all glyphs in the strike. */
    public short getWidth() {
        return getWidth(super.pointer);
    }

    // FT_Pos size;
    private static native int getSize(long pointer);

    /** The nominal size of the strike in 26.6 fractional points.
     * This field is not very useful.
     * */
    public float getSize() {
        final int raw = getSize(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos x_ppem;
    private static native int getXppem(long pointer);

    /** The horizontal ppem (nominal width) in 26.6 fractional pixels. */
    public float getXppem() {
        final int raw = getXppem(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos y_ppem;
    private static native int getYppem(long pointer);

    /** The vertical ppem (nominal height) in 26.6 fractional pixels. */
    public float getYppem() {
        final int raw = getYppem(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTBitmapSize newInstance() {
        return new FTBitmapSize(newStruct());
    }

}
