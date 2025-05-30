package generaloss.freetype.glyph;

import generaloss.freetype.FTStructRegistry;
import generaloss.freetype.freetype.FTLibrary;
import generaloss.freetype.FTStruct;
import generaloss.freetype.freetype.FTRenderMode;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;

public class FTGlyph extends FTStruct {

    private static native long newStruct();

    public static FTGlyph newInstance() {
        return new FTGlyph(newStruct());
    }


    public FTGlyph(long pointer) {
        super(pointer);
    }


    //
    private static native long getLibrary(long pointer);

    /** A handle to the FreeType library object. */
    public FTLibrary getLibrary() {
        final long pointer = getLibrary(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTLibrary::new);
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
        final long pointer = toBitmap(super.pointer, renderMode.value);
        return FTStructRegistry.getOrCreate(pointer, FTBitmapGlyph::new);
    }


    private static native void done(long pointer);

    /** Destroy a given glyph. */
    public void done() {
        done(super.pointer);
        super.destroyPointer();
    }

}