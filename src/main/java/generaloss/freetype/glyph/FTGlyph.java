package generaloss.freetype.glyph;

import generaloss.freetype.freetype.FTLibrary;
import generaloss.freetype.FTStruct;
import generaloss.freetype.freetype.FTRenderMode;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroker.FTStroker;

public class FTGlyph extends FTStruct {

    public FTGlyph(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTGlyph newInstance() {
        return new FTGlyph(newStruct());
    }


    //
    private static native long get(long pointer);

    /** A handle to the FreeType library object. */
    public FTLibrary getLibrary() {
        return library;
    }


    // FT_Glyph_Format format;
    private static native int getFormat(long pointer);

    /** The format of the glyph's image.c */
    public FTGlyphFormat getFormat() {
        final int raw = getFormat(super.pointer);
        return FTGlyphFormat.byValue(raw);
    }


    // FT_Vector advance;
    /** A 16.16 vector that gives the glyph's advance width. */




    private static native long strokeBorder(long pointer, long stroker, boolean inside);

    /** Stroke a given outline glyph object with a given stroker, but only return either its inside or outside border.c */
    public void strokeBorder(FTStroker stroker, boolean inside) {
        super.pointer = strokeBorder(super.pointer, stroker.getPointer(), inside);
    }


    private static native long toBitmap(long pointer, int renderMode);

    /** Convert a given glyph object to a bitmap glyph object. */
    public FTBitmapGlyph toBitmap(FTRenderMode renderMode) {
        final long bitmapPointer = toBitmap(super.pointer, renderMode.value);
        return new FTBitmapGlyph(bitmapPointer, this);
    }


    private static native void done(long pointer);

    /** Destroy a given glyph. */
    @Override
    public void done() {
        done(super.pointer);
        super.done();
    }

}