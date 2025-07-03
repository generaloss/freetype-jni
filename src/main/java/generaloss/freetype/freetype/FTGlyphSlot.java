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
        return FTStructCache.getOrCreate(FTLibrary.class, pointer, FTLibrary::new); 
    }

    // FT_Face face;
    private static native long getFace(long pointer);

    public FTFace getFace() {
        final long pointer = getFace(super.pointer);
        return FTStructCache.getOrCreate(FTFace.class, pointer, FTFace::new); 
    }

    // FT_GlyphSlot next;
    private static native long getNext(long pointer);

    public FTGlyphSlot getNext() {
        final long pointer = getNext(super.pointer);
        return FTStructCache.getOrCreate(FTGlyphSlot.class, pointer, FTGlyphSlot::new); 
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
        return FTStructCache.getOrCreate(FTGlyphMetrics.class, pointer, FTGlyphMetrics::new); 
    }

    // FT_Fixed linearHoriAdvance;
    private static native int getLinearHoriAdvance(long pointer);

    public float getLinearHoriAdvance() {
        final int raw = getLinearHoriAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed linearVertAdvance;
    private static native int getLinearVertAdvance(long pointer);

    public float getLinearVertAdvance() {
        final int raw = getLinearVertAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Vector advance;
    private static native long getAdvance(long pointer);

    public FTVector getAdvance() {
        final long pointer = getAdvance(super.pointer);
        return FTStructCache.getOrCreate(FTVector.class, pointer, FTVector::new); 
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
        return FTStructCache.getOrCreate(FTBitmap.class, pointer, FTBitmap::new); 
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
        return FTStructCache.getOrCreate(FTOutline.class, pointer, FTOutline::new); 
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
        if(pointers == null)
            return new FTSubGlyph[0];

        final FTSubGlyph[] objects = new FTSubGlyph[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructCache.getOrCreate(FTSubGlyph.class, pointers[i], FTSubGlyph::new); 

        return objects;
    }

    // FT_Pos lsb_delta;
    private static native int getLsbDelta(long pointer);

    public float getLsbDelta(PosType posType) {
        final int raw = getLsbDelta(super.pointer);
        return posType.toFloat(raw);
    }

    // FT_Pos rsb_delta;
    private static native int getRsbDelta(long pointer);

    public float getRsbDelta(PosType posType) {
        final int raw = getRsbDelta(super.pointer);
        return posType.toFloat(raw);
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
        final long[] dstGlyphPointer = new long[1];
        final FTError error = FreeType.ftGetGlyph(this, dstGlyphPointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTGlyph.class, dstGlyphPointer[0], FTGlyph::new); 
    }

}