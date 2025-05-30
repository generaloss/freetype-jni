package generaloss.freetype.glyph;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructRegistry;
import generaloss.freetype.image.FTBitmap;

public class FTBitmapGlyph extends FTStruct {

    private static native long newStruct();

    public static FTBitmapGlyph newInstance() {
        return new FTBitmapGlyph(newStruct());
    }


    public FTBitmapGlyph(long pointer) {
        super(pointer);
    }


    //
    private static native long getRoot(long pointer);

    /** The root fields of FT_Glyph. */
    public FTGlyph getRoot() {
        final long pointer = getRoot(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTGlyph::new);
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
        final long pointer = getBitmap(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTBitmap::new);
    }

}
