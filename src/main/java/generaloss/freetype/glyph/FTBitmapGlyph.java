package generaloss.freetype.glyph;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.image.FTBitmap;

public class FTBitmapGlyph extends FTStruct { // struct done.

    public FTBitmapGlyph(long pointer) {
        super(pointer);
    }


    // FT_GlyphRec root;
    private static native long getRoot(long pointer);

    public FTGlyph getRoot() {
        final long pointer = getRoot(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyph::new);
    }

    // FT_Int left;
    private static native int getLeft(long pointer);

    public int getLeft() {
        return getLeft(super.pointer);
    }

    // FT_Int top;
    private static native int getTop(long pointer);

    public int getTop() {
        return getTop(super.pointer);
    }

    // FT_Bitmap bitmap;
    private static native long getBitmap(long pointer);

    public FTBitmap getBitmap() {
        final long pointer = getBitmap(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTBitmap::new);
    }

}
