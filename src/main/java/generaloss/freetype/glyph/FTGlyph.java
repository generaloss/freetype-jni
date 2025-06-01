package generaloss.freetype.glyph;

import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.freetype.FTLibrary;
import generaloss.freetype.FTStruct;
import generaloss.freetype.freetype.FTRenderMode;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.types.FTBBox;
import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTMatrix;
import generaloss.freetype.types.FTVector;

public class FTGlyph extends FTStruct { // struct done.

    public FTGlyph(long pointer) {
        super(pointer);
    }


    // FT_Library library;
    private static native long getLibrary(long pointer);

    public FTLibrary getLibrary() {
        final long pointer = getLibrary(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTLibrary::new);
    }

    // FT_Glyph_Format format;
    private static native int getFormat(long pointer);

    public FTGlyphFormat getFormat() {
        final int raw = getFormat(super.pointer);
        return FTGlyphFormat.byValue(raw);
    }

    // FT_Vector advance;
    private static native long getAdvance(long pointer);

    public FTVector getAdvance() {
        final long pointer = getFormat(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTVector::new);
    }


    public void copy(FTGlyph target) {
        final FTError error = FreeType.ftGlyphCopy(this, target);
        error.checkError();
    }

    public void transform(FTMatrix matrix, FTVector delta) {
        final FTError error = FreeType.ftGlyphTransform(this, matrix, delta);
        error.checkError();
    }

    public void getCBox(long bboxMode, FTBBox cbox) {
        FreeType.ftGlyphGetCBox(this, bboxMode, cbox);
    }

    public FTBitmapGlyph toBitmap(FTRenderMode renderMode, FTVector origin, boolean destroy) {
        final long[] dstPointer = new long[1];
        final FTError error = FreeType.ftGlyphToBitmap(this, renderMode, origin, destroy, dstPointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstPointer[0], FTBitmapGlyph::new);
    }

    public void done() {
        FreeType.ftDoneGlyph(this);
    }

    public void stroke(FTStroker stroker, boolean destroy) {
        final FTError error = FreeType.ftGlyphStroke(this, stroker, destroy);
        error.checkError();
    }

    public void strokeBorder(FTStroker stroker, boolean inside, boolean destroy) {
        final FTError error = FreeType.ftGlyphStrokeBorder(this, stroker, inside, destroy);
        error.checkError();
    }


    private static native long newStruct();

    public static FTGlyph newInstance() {
        return new FTGlyph(newStruct());
    }

}