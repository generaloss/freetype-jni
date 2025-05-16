package generaloss.freetype.glyph;

import generaloss.freetype.FTObject;
import generaloss.freetype.bitmap.FTBitmap;

public class FTGlyphSlot extends FTObject {

    public FTGlyphSlot(long pointer) {
        super(pointer);
    }


    // TODO: library, face, next, glyph_index, generic


    private static native long getMetrics(long pointer);

    /** The metrics of the last loaded glyph in the slot. The returned values depend on the last load flags (see the FT_Load_Glyph API function) and can be expressed either in 26.6 fractional pixels or font units. */
    public FTGlyphMetrics getMetrics() {
        final long metricsPointer = getMetrics(super.pointer);
        return new FTGlyphMetrics(metricsPointer);
    }


    private static native int getLinearHoriAdvance(long pointer);

    /** The advance width of the unhinted glyph. Its value is expressed in 16.16 fractional pixels, unless FTLoad.LINEAR_DESIGN is set when loading the glyph. This field can be important to perform correct WYSIWYG layout. Only relevant for scalable glyphs. */
    public int getLinearHoriAdvance() {
        return getLinearHoriAdvance(super.pointer);
    }


    private static native int getLinearVertAdvance(long pointer);

    /** The advance height of the unhinted glyph. Its value is expressed in 16.16 fractional pixels, unless FTLoad.LINEAR_DESIGN is set when loading the glyph. This field can be important to perform correct WYSIWYG layout. Only relevant for scalable glyphs. */
    public int getLinearVertAdvance() {
        return getLinearVertAdvance(super.pointer);
    }


    private static native int getAdvanceX(long pointer);

    /** This shorthand is, depending on FTLoad.IGNORE_TRANSFORM, the transformed (hinted) advance width for the glyph, in 26.6 fractional pixel format. As specified with FTLoad.VERTICAL_LAYOUT, it uses either the horiAdvance or the vertAdvance value of metrics field. */
    public int getAdvanceX() {
        return getAdvanceX(super.pointer);
    }


    private static native int getAdvanceY(long pointer);

    /** This shorthand is, depending on FTLoad.IGNORE_TRANSFORM, the transformed (hinted) advance width for the glyph, in 26.6 fractional pixel format. As specified with FTLoad.VERTICAL_LAYOUT, it uses either the horiAdvance or the vertAdvance value of metrics field. */
    public int getAdvanceY() {
        return getAdvanceY(super.pointer);
    }


    private static native int getFormat(long pointer);

    /** This field indicates the format of the image contained in the glyph slot. Typically FTGlyphFormat.BITMAP, FTGlyphFormat.OUTLINE, or FTGlyphFormat.COMPOSITE, but other values are possible. */
    public FTGlyphFormat getFormat() {
        final int raw = getFormat(super.pointer);
        return FTGlyphFormat.byValue(raw);
    }


    private static native long getBitmap(long pointer);

    /** This field is used as a bitmap descriptor. Note that the address and content of the bitmap buffer can change between calls of FT_Load_Glyph and a few other functions. */
    public FTBitmap getBitmap() {
        final long bitmapPointer = getBitmap(super.pointer);
        return new FTBitmap(bitmapPointer);
    }


    private static native int getBitmapLeft(long pointer);

    /** The bitmap's left bearing expressed in integer pixels. */
    public int getBitmapLeft() {
        return getBitmapLeft(super.pointer);
    }


    private static native int getBitmapTop(long pointer);

    /** The bitmap's top bearing expressed in integer pixels. This is the distance from the baseline to the top-most glyph scanline, upwards y coordinates being positive. */
    public int getBitmapTop() {
        return getBitmapTop(super.pointer);
    }


    // TODO: outline, num_subglyphs, subglyphs, control_data, control_len, other, lsb_delta, rsb_delta


    private static native boolean renderGlyph(long pointer, int renderMode);

    /** Convert a given glyph image to a bitmap. It does so by inspecting the glyph image format, finding the relevant renderer, and invoking it. */
    public boolean renderGlyph(FTRenderMode renderMode) {
        return renderGlyph(super.pointer, renderMode.value);
    }


    private static native long getGlyph(long glyphSlot);

    /** A function used to extract a glyph image from a slot. Note that the created FT_Glyph object must be released with FT_Done_Glyph. */
    public FTGlyph getGlyph() {
        final long glyphPointer = getGlyph(super.pointer);
        return new FTGlyph(glyphPointer);
    }

}