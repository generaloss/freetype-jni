package generaloss.freetype.freetype;

import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.types.*;
import generaloss.freetype.FTStruct;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.*;

public class FTGlyphSlot extends FTStruct { // struct done.

    public FTGlyphSlot(long pointer) {
        super(pointer);
    }


    // FT_Library library;
    private static native long getLibrary(long pointer);

    public FTLibrary getLibrary() {
        final long pointer = getLibrary(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTLibrary::new);
    }

    // FT_Face face;
    private static native long getFace(long pointer);

    public FTFace getFace() {
        final long pointer = getFace(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTFace::new);
    }

    // FT_GlyphSlot next;
    private static native long getNext(long pointer);

    public FTGlyphSlot getNext() {
        final long pointer = getNext(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyphSlot::new);
    }

    // FT_UInt glyph_index;
    private static native long getGlyphIndex(long pointer);

    public long getGlyphIndex() {
        return getGlyphIndex(super.pointer);
    }

    // FT_Glyph_Metrics metrics;
    private static native long getMetrics(long pointer);

    public FTGlyphMetrics getMetrics() {
        final long pointer = getMetrics(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyphMetrics::new);
    }

    // FT_Fixed linearHoriAdvance;
    private static native long getLinearHoriAdvance(long pointer);

    public float getLinearHoriAdvance() {
        final long raw = getLinearHoriAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed linearVertAdvance;
    private static native long getLinearVertAdvance(long pointer);

    public float getLinearVertAdvance() {
        final long raw = getLinearVertAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Vector advance;
    private static native long getAdvance(long pointer);

    public FTVector getAdvance() {
        final long pointer = getAdvance(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTVector::new);
    }

    // FT_Glyph_Format format;
    private static native int getFormat(long pointer);

    public FTGlyphFormat getFormat() {
        final int raw = getFormat(super.pointer);
        return FTGlyphFormat.byValue(raw);
    }

    // FT_Bitmap bitmap;
    private static native long getBitmap(long pointer);

    public FTBitmap getBitmap() {
        final long pointer = getBitmap(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTBitmap::new);
    }

    // FT_Int bitmap_left;
    private static native int getBitmapLeft(long pointer);

    public int getBitmapLeft() {
        return getBitmapLeft(super.pointer);
    }

    // FT_Int bitmap_top;
    private static native int getBitmapTop(long pointer);

    public int getBitmapTop() {
        return getBitmapTop(super.pointer);
    }

    // FT_Outline outline;
    private static native long getOutline(long pointer);

    public FTOutline getOutline() {
        final long pointer = getOutline(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTOutline::new);
    }

    // FT_UInt num_subglyphs;
    private static native long getNumSubglyphs(long pointer);

    public long getNumSubglyphs() {
        return getNumSubglyphs(super.pointer);
    }

    // FT_SubGlyph subglyphs;
    private static native long[] getSubglyphs(long pointer);

    public FTSubGlyph[] getSubglyphs() {
        final long[] pointers = getSubglyphs(super.pointer);

        final FTSubGlyph[] objects = new FTSubGlyph[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTSubGlyph::new);

        return objects;
    }

    // FT_Pos lsb_delta;
    private static native long getLsbDelta(long pointer);

    public float getLsbDelta() {
        final long raw = getLsbDelta(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos rsb_delta;
    private static native long getRsbDelta(long pointer);

    public float getRsbDelta() {
        final long raw = getRsbDelta(super.pointer);
        return FTPos.toFloat(raw);
    }


    public void renderGlyph(FTRenderMode renderMode) {
        final FTError error = FreeType.ftRenderGlyph(this, renderMode);
        error.checkError();
    }

    public void getSubGlyphInfo(long subIndex, int[] dstPIndex, long[] dstPFlags, int[] dstPArg1, int[] dstPArg2, FTMatrix dstPTransform) {
        final FTError error = FreeType.ftGetSubGlyphInfo(this, subIndex, dstPIndex, dstPFlags, dstPArg1, dstPArg2, dstPTransform);
        error.checkError();
    }

    public FTGlyph getGlyph() {
        final FTGlyph dstGlyph = FTGlyph.newInstance();
        final FTError error = FreeType.ftGetGlyph(this, dstGlyph);
        error.checkError();
        return dstGlyph;
    }


    private static native long newStruct();

    public static FTGlyphSlot newInstance() {
        return new FTGlyphSlot(newStruct());
    }

}