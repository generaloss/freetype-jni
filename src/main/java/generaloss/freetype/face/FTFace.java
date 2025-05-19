package generaloss.freetype.face;

import generaloss.freetype.*;
import generaloss.freetype.charmap.FTCharMap;
import generaloss.freetype.charmap.FTEncoding;
import generaloss.freetype.glyph.FTGlyphSlot;

public class FTFace extends FTObject {

    private final FTLibrary library;

    public FTFace(long pointer, FTLibrary library) {
        super(pointer);
        this.library = library;
    }


    private static native int getFaceFlags(long pointer);

    /** A set of bit flags that give important information about the face; see FTFaceFlag.XXX for the details. */
    public FTFaceFlags getFaceFlags() {
        final int flags = getFaceFlags(super.pointer);
        return new FTFaceFlags(flags);
    }


    private static native int getStyleFlags(long pointer);

    /** The lower 16 bits contain a set of bit flags indicating the style of the face; see FTStyleFlag.XXX for the details.
      * Bits 16-30 hold the number of named instances available for the current face if we have a GX or OpenType variation (sub)font. Bit 31 is always zero (that is, style_flags is always a positive value). Note that a variation font has always at least one named instance, namely the default instance. */
    public FTStyleFlags getStyleFlags() {
        final int flags = getStyleFlags(super.pointer);
        return new FTStyleFlags(flags);
    }


    private static native int getNumGlyphs(long pointer);

    /** The number of glyphs in the face. If the face is scalable and has sbits (see num_fixed_sizes), it is set to the number of outline glyphs.
      * For CID-keyed fonts (not in an SFNT wrapper) this value gives the highest CID used in the font. */
    public int getNumGlyphs() {
        return getNumGlyphs(super.pointer);
    }


    private static native int getNumCharmaps(long pointer);

    /** The number of charmaps in the face. */
    public int getNumCharmaps() {
        return getNumCharmaps(super.pointer);
    }


    private static native long[] getCharmaps(long pointer);

    /** An array of the charmaps of the face. */
    public FTCharMap[] getCharmaps() {
        final long[] charmapPointers = getCharmaps(super.pointer);

        final FTCharMap[] charmaps = new FTCharMap[charmapPointers.length];
        for(int i = 0; i < charmapPointers.length; i++)
            charmaps[i] = new FTCharMap(charmapPointers[i], this);
        return charmaps;
    }


    private static native int getAscender(long pointer);

    /** The typographic ascender of the face, expressed in font units. For font formats not having this information, it is set to bbox.yMax. Only relevant for scalable formats. */
    public int getAscender() {
        return getAscender(super.pointer);
    }


    private static native int getDescender(long pointer);

    /** The typographic descender of the face, expressed in font units. For font formats not having this information, it is set to bbox.yMin. Note that this field is negative for values below the baseline. Only relevant for scalable formats. */
    public int getDescender() {
        return getDescender(super.pointer);
    }


    private static native int getHeight(long pointer);

    /** This value is the vertical distance between two consecutive baselines, expressed in font units. It is always positive. Only relevant for scalable formats. */
    public int getHeight() {
        return getHeight(super.pointer);
    }


    private static native int getMaxAdvanceWidth(long pointer);

    /** The maximum advance width, in font units, for all glyphs in this face. This can be used to make word wrapping computations faster. Only relevant for scalable formats. */
    public int getMaxAdvanceWidth() {
        return getMaxAdvanceWidth(super.pointer);
    }


    private static native int getMaxAdvanceHeight(long pointer);

    /** The maximum advance height, in font units, for all glyphs in this face. This is only relevant for vertical layouts, and is set to height for fonts that do not provide vertical metrics. Only relevant for scalable formats. */
    public int getMaxAdvanceHeight() {
        return getMaxAdvanceHeight(super.pointer);
    }


    private static native int getUnderlinePosition(long pointer);

    /** The position, in font units, of the underline line for this face. It is the center of the underlining stem. Only relevant for scalable formats. */
    public int getUnderlinePosition() {
        return getUnderlinePosition(super.pointer);
    }


    private static native int getUnderlineThickness(long pointer);

    /** The thickness, in font units, of the underline for this face. Only relevant for scalable formats. */
    public int getUnderlineThickness() {
        return getUnderlineThickness(super.pointer);
    }


    private static native long getGlyph(long pointer);

    /** The face's associated glyph slot(s). */
    public FTGlyphSlot getGlyph() {
        final long slotPointer = getGlyph(super.pointer);
        return new FTGlyphSlot(slotPointer, library, this);
    }


    private static native long getSize(long pointer);

    /** The current active size for this face. */
    public FTSize getSize() {
        final long sizePointer = getSize(super.pointer);
        return new FTSize(sizePointer);
    }




    private static native boolean selectCharmap(long pointer, int encoding);

    /** Select a given charmap by its encoding tag. */
    public boolean selectCharmap(FTEncoding encoding) {
        return selectCharmap(super.pointer, encoding.value);
    }


    private static native boolean setCharmap(long pointer, long charmap);

    /** Select a given charmap for character code to glyph index mapping. */
    public boolean setCharmap(FTCharMap charmap) {
        return setCharmap(super.pointer, charmap.getPointer());
    }


    private static native boolean selectSize(long pointer, int strike_index);

    /** Select a bitmap strike. To be more precise, this function sets the scaling factors of the active FT_Size object in a face so that bitmaps from this particular strike are taken by FT_Load_Glyph and friends. */
    public boolean selectSize(int strikeIndex) {
        return selectSize(super.pointer, strikeIndex);
    }


    private static native boolean setCharSize(long pointer, int charWidth, int charHeight, int horzResolution, int vertResolution);

    /** While this function allows fractional points as input values, the resulting ppem value for the given resolution is always rounded to the nearest integer.
      * If either the character width or height is zero, it is set equal to the other value.
      * If either the horizontal or vertical resolution is zero, it is set equal to the other value.
      * A character width or height smaller than 1pt is set to 1pt; if both resolution values are zero, they are set to 72dpi.
      * Don't use this function if you are using the FreeType cache API. */
    public boolean setCharSize(int charWidth, int charHeight, int horzResolution, int vertResolution) {
        return setCharSize(super.pointer, charWidth, charHeight, horzResolution, vertResolution);
    }


    private static native boolean setPixelSizes(long pointer, int pixelWidth, int pixelHeight);

    /** You should not rely on the resulting glyphs matching or being constrained to this pixel size. Refer to FT_Request_Size to understand how requested sizes relate to actual sizes.
      * Don't use this function if you are using the FreeType cache API. */
    public boolean setPixelSizes(int pixelWidth, int pixelHeight) {
        return setPixelSizes(super.pointer, pixelWidth, pixelHeight);
    }


    private static native boolean loadGlyph(long pointer, int glyphIndex, int loadFlags);

    /** Load a glyph into the glyph slot of a face object. */
    public boolean loadGlyph(int glyphIndex, FTLoadFlags loadFlags) {
        return loadGlyph(super.pointer, glyphIndex, loadFlags.getBits());
    }

    public boolean loadGlyph(int glyphIndex) {
        return loadGlyph(super.pointer, glyphIndex, 0);
    }


    private static native boolean loadChar(long pointer, int charCode, int loadFlags);

    /** Load a glyph into the glyph slot of a face object, accessed by its character code.c */
    public boolean loadChar(int charCode, FTLoadFlags loadFlags) {
        return loadChar(super.pointer, charCode, loadFlags.getBits());
    }

    public boolean loadChar(int charCode) {
        return loadChar(super.pointer, charCode, 0);
    }


    private static native boolean hasKerning(long pointer);

    /** A macro that returns true whenever a face object contains kerning data that can be accessed with FT_Get_Kerning. */
    public boolean hasKerning() {
        return hasKerning(super.pointer);
    }


    private static native int getKerning(long pointer, int leftGlyph, int rightGlyph, int kernMode);

    /** Return the kerning vector between two glyphs of the same face. */
    public FTPos getKerning(int leftGlyph, int rightGlyph, FTKerningMode kernMode) {
        final int raw = getKerning(super.pointer, leftGlyph, rightGlyph, kernMode.value);
        return new FTPos(raw);
    }


    private static native int getCharIndex(long pointer, int charCode);

    /** Return the glyph index of a given character code. This function uses the currently selected charmap to do the mapping.c */
    public int getCharIndex(int charCode) {
        return getCharIndex(super.pointer, charCode);
    }


    private static native void doneFace(long pointer);

    /** Discard a given face object, as well as all of its child slots and sizes. */
    @Override
    public void done() {
        doneFace(super.pointer);
        super.done();
    }

}