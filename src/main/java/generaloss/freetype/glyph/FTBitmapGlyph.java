package generaloss.freetype.glyph;

import generaloss.freetype.FTStruct;
import generaloss.freetype.image.FTBitmap;

public class FTBitmapGlyph extends FTStruct {

    private final FTGlyph glyph;

    public FTBitmapGlyph(long pointer, FTGlyph glyph) {
        super(pointer);
        this.glyph = glyph;
    }


    /** The root fields of FT_Glyph. */
    public FTGlyph getRoot() {
        return glyph;
    }


    private static native int getLeft(long pointer);

    /** The left-side bearing, i.e., the horizontal distance from the current pen position to the left border of the glyph bitmap. */
    public int getLeft() {
        return getLeft(super.pointer);
    }


    private static native int getTop(long pointer);

    /** The top-side bearing, i.e., the vertical distance from the current pen position to the top border of the glyph bitmap.
     * This distance is positive for upwards y!
     * */
    public int getTop() {
        return getTop(super.pointer);
    }


    private static native long getBitmap(long pointer);

    /** A descriptor for the bitmap. */
    public FTBitmap getBitmap() {
        final long bitmapPointer = getBitmap(super.pointer);
        return new FTBitmap(bitmapPointer);
    }

}
