package generaloss.freetype.freetype;

import generaloss.freetype.*;
import generaloss.freetype.types.*;

public class FTFace extends FTStruct { // struct done.

    private static native long newStruct();

    public static FTFace newInstance() {
        return new FTFace(newStruct());
    }


    private final FTGeneric generic;

    public FTFace(long pointer) {
        super(pointer);
        this.generic = new FTGeneric();
    }


    // FT_Long num_faces;
    private static native long getNumFaces(long pointer);

    /** The number of faces in the font file.
     * Some font formats can have multiple faces in a single font file.
     * */
    public long getNumFaces() {
        return getNumFaces(super.pointer);
    }


    // FT_Long face_index;
    private static native long getFaceIndex(long pointer);

    /** This field holds two different values.
     * Bits 0-15 are the index of the face in the font file (starting with value 0).
     * They are set to 0 if there is only one face in the font file.
     * [Since 2.6.1] Bits 16-30 are relevant to GX and OpenType variation fonts only, holding the named instance index for the current face index (starting with value 1; value 0 indicates font access without a named instance).
     * For non-variation fonts, bits 16-30 are ignored.
     * If we have the third named instance of face 4, say, face_index is set to 0x00030004.
     * Bit 31 is always zero (that is, face_index is always a positive value).
     * */
    public long getFaceIndex() {
        return getFaceIndex(super.pointer);
    }


    // FT_Long face_flags;
    private static native int getFaceFlags(long pointer);

    /** A set of bit flags that give important information about the face; see FTFaceFlag.XXX for the details. */
    public FaceFlags getFaceFlags() {
        final int flags = getFaceFlags(super.pointer);
        return new FaceFlags(flags);
    }


    // FT_Long style_flags;
    private static native int getStyleFlags(long pointer);

    /** The lower 16 bits contain a set of bit flags indicating the style of the face; see FTStyleFlag.XXX for the details.
     * Bits 16-30 hold the number of named instances available for the current face if we have a GX or OpenType variation (sub)font.
     * Bit 31 is always zero (that is, style_flags is always a positive value).
     * Note that a variation font has always at least one named instance, namely the default instance.
     * */
    public StyleFlags getStyleFlags() {
        final int flags = getStyleFlags(super.pointer);
        return new StyleFlags(flags);
    }


    // FT_Long num_glyphs;
    private static native long getNumGlyphs(long pointer);

    /** The number of glyphs in the face. If the face is scalable and has sbits (see num_fixed_sizes), it is set to the number of outline glyphs.
     * For CID-keyed fonts (not in an SFNT wrapper) this value gives the highest CID used in the font.
     * */
    public long getNumGlyphs() {
        return getNumGlyphs(super.pointer);
    }


    // FT_String* family_name;
    private static native String getFamilyName(long pointer);

    /** The face's family name.
     * This is an ASCII string, usually in English, that describes the typeface's family (like ‘Times New Roman’, ‘Bodoni’, ‘Garamond’, etc).
     * This is a least common denominator used to list fonts.
     * Some formats (TrueType & OpenType) provide localized and Unicode versions of this string.
     * Applications should use the format-specific interface to access them.
     * Can be NULL (e.g., in fonts embedded in a PDF file).
     * In case the font doesn't provide a specific family name entry, FreeType tries to synthesize one, deriving it from other name entries.
     * */
    public String getFamilyName() {
        return getFamilyName(super.pointer);
    }


    // FT_String* style_name;
    private static native String getStyleName(long pointer);

    /** The face's style name.
     * This is an ASCII string, usually in English, that describes the typeface's style (like ‘Italic’, ‘Bold’, ‘Condensed’, etc).
     * Not all font formats provide a style name, so this field is optional, and can be set to NULL.
     * As for family_name, some formats provide localized and Unicode versions of this string.
     * Applications should use the format-specific interface to access them.
     * */
    public String getStyleName() {
        return getStyleName(super.pointer);
    }


    // FT_Int num_fixed_sizes;
    private static native int getNumFixedSizes(long pointer);

    /** The number of bitmap strikes in the face.
     * Even if the face is scalable, there might still be bitmap strikes, which are called ‘sbits’ in that case.
     * */
    public int getNumFixedSizes() {
        return getNumFixedSizes(super.pointer);
    }


    // FT_Bitmap_Size* available_sizes;
    private static native long[] getAvailableSizes(long pointer);

    /** An array of FT_Bitmap_Size for all bitmap strikes in the face.
     * It is set to NULL if there is no bitmap strike.
     * Note that FreeType tries to sanitize the strike data since they are sometimes sloppy or incorrect, but this can easily fail.
     * */
    public FTBitmapSize[] getAvailableSizes() {
        final long[] pointers = getAvailableSizes(super.pointer);

        final FTBitmapSize[] objects = new FTBitmapSize[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructRegistry.getOrCreate(pointers[i], FTBitmapSize::new);

        return objects;
    }


    // FT_Int num_charmaps;
    private static native int getNumCharmaps(long pointer);

    /** The number of charmaps in the face. */
    public int getNumCharmaps() {
        return getNumCharmaps(super.pointer);
    }


    // FT_CharMap* charmaps;
    private static native long[] getCharmaps(long pointer);

    /** An array of the charmaps of the face. */
    public FTCharMap[] getCharmaps() {
        final long[] pointers = getCharmaps(super.pointer);

        final FTCharMap[] objects = new FTCharMap[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructRegistry.getOrCreate(pointers[i], FTCharMap::new);

        return objects;
    }


    // FT_Generic generic;
    public FTGeneric getGeneric() {
        return generic;
    }


    // FT_BBox bbox;
    private static native long getBBox(long pointer);

    /** The font bounding box.
     * Coordinates are expressed in font units (see units_per_EM).
     * The box is large enough to contain any glyph from the font.
     * Thus, bbox.yMax can be seen as the ‘maximum ascender’, and bbox.yMin as the ‘minimum descender’.
     * Only relevant for scalable formats.
     * Note that the bounding box might be off by (at least) one pixel for hinted fonts.
     * See FT_Size_Metrics for further discussion.
     * Note that the bounding box does not vary in OpenType variation fonts and should only be used in relation to the default instance. */
    public FTBBox getBBox() {
        final long pointer = getBBox(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTBBox::new);
    }


    // FT_UShort units_per_EM;
    private static native int getUnitsPerEM(long pointer);

    /** The number of font units per EM square for this face.
     * This is typically 2048 for TrueType fonts, and 1000 for Type 1 fonts.
     * Only relevant for scalable formats.
     * */
    public int getUnitsPerEM() {
        return getUnitsPerEM(super.pointer);
    }


    // FT_Short ascender;
    private static native short getAscender(long pointer);

    /** The typographic ascender of the face, expressed in font units.
     * For font formats not having this information, it is set to bbox.yMax.
     * Only relevant for scalable formats.
     * */
    public short getAscender() {
        return getAscender(super.pointer);
    }


    // FT_Short descender;
    private static native short getDescender(long pointer);

    /** The typographic descender of the face, expressed in font units.
     * For font formats not having this information, it is set to bbox.yMin.
     * Note that this field is negative for values below the baseline.
     * Only relevant for scalable formats.
     * */
    public short getDescender() {
        return getDescender(super.pointer);
    }


    // FT_Short height;
    private static native short getHeight(long pointer);

    /** This value is the vertical distance between two consecutive baselines, expressed in font units.
     * It is always positive.
     * Only relevant for scalable formats.
     * */
    public short getHeight() {
        return getHeight(super.pointer);
    }


    // FT_Short max_advance_width;
    private static native short getMaxAdvanceWidth(long pointer);

    /** The maximum advance width, in font units, for all glyphs in this face.
     * This can be used to make word wrapping computations faster.
     * Only relevant for scalable formats.
     * */
    public short getMaxAdvanceWidth() {
        return getMaxAdvanceWidth(super.pointer);
    }


    // FT_Short max_advance_height;
    private static native short getMaxAdvanceHeight(long pointer);

    /** The maximum advance height, in font units, for all glyphs in this face.
     * This is only relevant for vertical layouts, and is set to height for fonts that do not provide vertical metrics.
     * Only relevant for scalable formats.
     * */
    public short getMaxAdvanceHeight() {
        return getMaxAdvanceHeight(super.pointer);
    }


    // FT_Short underline_position;
    private static native short getUnderlinePosition(long pointer);

    /** The position, in font units, of the underline line for this face.
     * It is the center of the underlining stem.
     * Only relevant for scalable formats.
     * */
    public short getUnderlinePosition() {
        return getUnderlinePosition(super.pointer);
    }


    // FT_Short underline_thickness;
    private static native short getUnderlineThickness(long pointer);

    /** The thickness, in font units, of the underline for this face.
     * Only relevant for scalable formats.
     * */
    public short getUnderlineThickness() {
        return getUnderlineThickness(super.pointer);
    }


    // FT_GlyphSlot glyph;
    private static native long getGlyph(long pointer);

    /** The face's associated glyph slot(s). */
    public FTGlyphSlot getGlyph() {
        final long pointer = getGlyph(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTGlyphSlot::new);
    }


    // FT_Size size;
    private static native long getSize(long pointer);

    /** The current active size for this face. */
    public FTSize getSize() {
        final long pointer = getSize(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTSize::new);
    }


    // FT_CharMap charmap;
    private static native long getCharmap(long pointer);

    /** The current active charmap for this face. */
    public FTCharMap getCharmap() {
        final long pointer = getCharmap(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTCharMap::new);
    }


    public void attachFile(String filepath) {
        final FTError error = FreeType.ftAttachFile(this, filepath);
        error.checkError();
    }

    public void attachStream(FTOpenArgs parameters) {
        final FTError error = FreeType.ftAttachStream(this, parameters);
        error.checkError();
    }

    public void referenceFace() {
        final FTError error = FreeType.ftReferenceFace(this);
        error.checkError();
    }

    public void selectSize(int strikeIndex) {
        final FTError error = FreeType.ftSelectSize(this, strikeIndex);
        error.checkError();
    }

    public void requestSize(FTSizeRequest reqest) {
        final FTError error = FreeType.ftRequestSize(this, reqest);
        error.checkError();
    }

    public void setCharSize(int charWidth, int charHeight, int horzResolution, int vertResolution) {
        final FTError error = FreeType.ftSetCharSize(this, charWidth, charHeight, horzResolution, vertResolution);
        error.checkError();
    }

    public void setPixelSizes(int pixelWidth, int pixelHeight) {
        final FTError error = FreeType.ftSetPixelSizes(this, pixelWidth, pixelHeight);
        error.checkError();
    }

    public void loadGlyph(int glyphIndex, LoadFlags loadFlags) {
        final FTError error = FreeType.ftLoadGlyph(this, glyphIndex, loadFlags);
        error.checkError();
    }

    public void loadGlyph(int glyphIndex, int loadFlags) {
        final FTError error = FreeType.ftLoadGlyph(this, glyphIndex, loadFlags);
        error.checkError();
    }

    public void loadGlyph(int glyphIndex) {
        this.loadGlyph(glyphIndex, 0);
    }

    public void loadChar(int charcode, LoadFlags loadFlags) {
        final FTError error = FreeType.ftLoadChar(this, charcode, loadFlags);
        error.checkError();
    }

    public void loadChar(int charcode, int loadFlags) {
        final FTError error = FreeType.ftLoadChar(this, charcode, loadFlags);
        error.checkError();
    }

    public void loadChar(int charcode) {
        this.loadChar(charcode, 0);
    }

    public void setTransform(FTMatrix matrix, FTVector delta) {
        FreeType.ftSetTransform(this, matrix, delta);
    }

    public void getTransform(FTMatrix dstMatrix, FTVector dstDelta) {
        FreeType.ftGetTransform(this, dstMatrix, dstDelta);
    }

    public void getKerning(int leftGlyph, int rightGlyph, FTKerningMode kernMode, FTVector dstKerning) {
        final FTError error = FreeType.ftGetKerning(this, leftGlyph, rightGlyph, kernMode, dstKerning);
        error.checkError();
    }

    public FTVector getKerning(int leftGlyph, int rightGlyph, FTKerningMode kernMode) {
        final FTVector dstKerning = FTVector.newInstance();
        this.getKerning(leftGlyph, rightGlyph, kernMode, dstKerning);
        return dstKerning;
    }

    public void getTrackKerning(int pointSize, int degree, FTFixed dstKerning) {
        final FTError error = FreeType.ftGetTrackKerning(this, pointSize, degree, dstKerning);
        error.checkError();
    }

    public float getTrackKerning(int pointSize, int degree) {
        final FTFixed dstKerning = FTFixed.newInstance();
        this.getTrackKerning(pointSize, degree, dstKerning);
        return dstKerning.getFloat();
    }

    public void selectCharmap(FTEncoding encoding) {
        final FTError error = FreeType.ftSelectCharmap(this, encoding);
        error.checkError();
    }

    public void setCharmap(FTCharMap charmap) {
        final FTError error = FreeType.ftSetCharmap(this, charmap);
        error.checkError();
    }

    public int getCharIndex(int charcode) {
        return FreeType.ftGetCharIndex(this, charcode);
    }

    public long getFirstChar(long[] dstGIndex) {
        return FreeType.ftGetFirstChar(this, dstGIndex);
    }

    // long getNextChar(FTFace face, long charcode, int* gindex)

    // FTError properties(FTFace face, int numProperties, FTParameter* properties)

    // int getNameIndex(FTFace face, String* glyphName)

    // FTError getGlyphName(FTFace face, int glyphIndex, FTPointer buffer, int bufferMax)

    // String getPostscriptName(FTFace face)

    // int getFSTypeFlags(FTFace face)

    // int getCharVariantIndex(FTFace face, long charcode, long variantSelector)

    // int getCharVariantIsDefault(FTFace face, long charcode, long variantSelector)

    // int getVariantSelectors(FTFace face)

    // int getVariantsOfChar(FTFace face, long charcode)

    // int getCharsOfVariant(FTFace face, long variantSelector)

    // boolean checkTrueTypePatents(FTFace face)

    // boolean setUnpatentedHinting(FTFace face, boolean value)

    public void done() {
        generic.getFinalizer().run();
        FreeType.ftDoneFace(this);
        super.destroyPointer();
    }

}