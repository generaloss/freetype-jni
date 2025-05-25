package generaloss.freetype.freetype;

import generaloss.freetype.types.FTFixed;
import generaloss.freetype.FTStruct;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.*;
import generaloss.freetype.types.FTGeneric;
import generaloss.freetype.types.FTPos;
import generaloss.freetype.types.FTVector;

public class FTGlyphSlot extends FTStruct {

    private final FTLibrary library;
    private final FTFace face;
    private final FTGeneric generic;

    public FTGlyphSlot(long pointer, FTLibrary library, FTFace face) {
        super(pointer);
        this.library = library;
        this.face = face;
        this.generic = new FTGeneric();
    }


    // FT_Library library;
    /** A handle to the FreeType library instance this slot belongs to. */
    public FTLibrary getLibrary() {
        return library;
    }


    // FT_Face face;
    /** A handle to the parent face object. */
    public FTFace getFace() {
        return face;
    }


    // FT_GlyphSlot next;
    private static native long getNext(long pointer);

    /** In some cases (like some font tools), several glyph slots per face object can be a good thing.
     * As this is rare, the glyph slots are listed through a direct, single-linked list using its next field.
     * */
    public FTGlyphSlot getNext() {
        final long slotPointer = getNext(super.pointer);
        return new FTGlyphSlot(slotPointer, library, face);
    }


    // FT_UInt glyph_index;
    private static native long getGlyphIndex(long pointer);

    /** [Since 2.10] The glyph index passed as an argument to FT_Load_Glyph while initializing the glyph slot. */
    public long getGlyphIndex() {
        return getGlyphIndex(super.pointer);
    }


    // FT_Generic generic;
    /** A typeless pointer unused by the FreeType library or any of its drivers.
     * It can be used by client applications to link their own data to each glyph slot object.
     * */
    public FTGeneric getGeneric() {
        return generic;
    }


    // FT_Glyph_Metrics metrics;
    private static native long getMetrics(long pointer);

    /** The metrics of the last loaded glyph in the slot. The returned values depend on the last load flags (see the FT_Load_Glyph API function) and can be expressed either in 26.6 fractional pixels or font units. */
    public FTGlyphMetrics getMetrics() {
        final long metricsPointer = getMetrics(super.pointer);
        return new FTGlyphMetrics(metricsPointer);
    }


    // FT_Fixed linearHoriAdvance;
    private static native int getLinearHoriAdvance(long pointer);

    /** The advance width of the unhinted glyph. Its value is expressed in 16.16 fractional pixels, unless FTLoad.LINEAR_DESIGN is set when loading the glyph. This field can be important to perform correct WYSIWYG layout. Only relevant for scalable glyphs. */
    public float getLinearHoriAdvance() {
        final int raw = getLinearHoriAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }


    // FT_Fixed linearVertAdvance;
    private static native int getLinearVertAdvance(long pointer);

    /** The advance height of the unhinted glyph. Its value is expressed in 16.16 fractional pixels, unless FTLoad.LINEAR_DESIGN is set when loading the glyph. This field can be important to perform correct WYSIWYG layout. Only relevant for scalable glyphs. */
    public float getLinearVertAdvance() {
        final int raw = getLinearVertAdvance(super.pointer);
        return FTFixed.toFloat(raw);
    }


    // FT_Vector advance;
    private static native long getAdvance(long pointer);

    /** This shorthand is, depending on FT_LOAD_IGNORE_TRANSFORM, the transformed (hinted) advance width for the glyph, in 26.6 fractional pixel format.
     * As specified with FT_LOAD_VERTICAL_LAYOUT, it uses either the horiAdvance or the vertAdvance value of metrics field.
     * */
    public FTVector getAdvance() {
        final long vectorPointer = getAdvance(super.pointer);
        return new FTVector(vectorPointer);
    }


    // FT_Glyph_Format format;
    private static native int getFormat(long pointer);

    /** This field indicates the format of the image contained in the glyph slot. Typically FTGlyphFormat.BITMAP, FTGlyphFormat.OUTLINE, or FTGlyphFormat.COMPOSITE, but other values are possible. */
    public FTGlyphFormat getFormat() {
        final int raw = getFormat(super.pointer);
        return FTGlyphFormat.byValue(raw);
    }


    // FT_Bitmap bitmap;
    private static native long getBitmap(long pointer);

    /** This field is used as a bitmap descriptor. Note that the address and content of the bitmap buffer can change between calls of FT_Load_Glyph and a few other functions. */
    public FTBitmap getBitmap() {
        final long bitmapPointer = getBitmap(super.pointer);
        return new FTBitmap(bitmapPointer);
    }


    // FT_Int bitmap_left;
    private static native int getBitmapLeft(long pointer);

    /** The bitmap's left bearing expressed in integer pixels. */
    public int getBitmapLeft() {
        return getBitmapLeft(super.pointer);
    }


    // FT_Int bitmap_top;
    private static native int getBitmapTop(long pointer);

    /** The bitmap's top bearing expressed in integer pixels. This is the distance from the baseline to the top-most glyph scanline, upwards y coordinates being positive. */
    public int getBitmapTop() {
        return getBitmapTop(super.pointer);
    }


    // FT_Outline outline;
    private static native long getOutline(long pointer);

    /** The outline descriptor for the current glyph image if its format is FT_GLYPH_FORMAT_OUTLINE.
     * Once a glyph is loaded, outline can be transformed, distorted, emboldened, etc.
     * However, it must not be freed.
     * [Since 2.10.1] If FT_LOAD_NO_SCALE is set, outline coordinates of OpenType variation fonts for a selected instance are internally handled as 26.6 fractional font units but returned as (rounded) integers, as expected.
     * To get unrounded font units, don't use FT_LOAD_NO_SCALE but load the glyph with FT_LOAD_NO_HINTING and scale it, using the font's units_per_EM value as the ppem. */
    public FTOutline getOutline() {
        final long outlinePointer = getOutline(super.pointer);
        return new FTOutline(outlinePointer);
    }


    // FT_UInt num_subglyphs;
    private static native long getNumSubglyphs(long pointer);

    /** The number of subglyphs in a composite glyph.
     * This field is only valid for the composite glyph format that should normally only be loaded with the FT_LOAD_NO_RECURSE flag.
     * */
    public long getNumSubglyphs() {
        return getNumSubglyphs(super.pointer);
    }


    // FT_SubGlyph subglyphs;
    private static native long[] getSubglyphs(long pointer);

    /** The difference between hinted and unhinted left side bearing while auto-hinting is active.
     * Zero otherwise.
     * */
    // public FTSubGlyph[] getSubglyphs() {
    //     final long[] pointers = getSubglyphs(super.pointer);
    //
    //     final FTSubGlyph[] objects = new FTSubGlyph[pointers.length];
    //     for(int i = 0; i < pointers.length; i++)
    //         objects[i] = new FTSubGlyph(pointers[i]);
    //
    //     return objects;
    // }


    // FT_Pos lsb_delta;
    private static native int getLsbDelta(long pointer);

    /** The difference between hinted and unhinted left side bearing while auto-hinting is active.
     * Zero otherwise.
     * */
    public float getLsbDelta() {
        final int raw = getLsbDelta(super.pointer);
        return FTPos.toFloat(raw);
    }


    // FT_Pos rsb_delta;
    private static native int getRsbDelta(long pointer);

    /** The difference between hinted and unhinted right side bearing while auto-hinting is active.
     * Zero otherwise.
     * */
    public float getRsbDelta() {
        final int raw = getRsbDelta(super.pointer);
        return FTPos.toFloat(raw);
    }




    private static native boolean renderGlyph(long pointer, int renderMode);

    /** Convert a given glyph image to a bitmap. It does so by inspecting the glyph image format, finding the relevant renderer, and invoking it. */
    public boolean renderGlyph(FTRenderMode renderMode) {
        return renderGlyph(super.pointer, renderMode.value);
    }


    private static native long getGlyph(long glyphSlot);

    /** A function used to extract a glyph image from a slot. Note that the created FT_Glyph object must be released with FT_Done_Glyph. */
    public FTGlyph getGlyph() {
        final long glyphPointer = getGlyph(super.pointer);
        return new FTGlyph(glyphPointer, library);
    }

}