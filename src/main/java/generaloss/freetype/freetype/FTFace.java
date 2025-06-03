package generaloss.freetype.freetype;

import generaloss.freetype.*;
import generaloss.freetype.types.*;

import java.nio.ByteBuffer;

public class FTFace extends FTStruct { // struct done.

    public FTFace(long pointer) {
        super(pointer);
    }


    // FT_Long num_faces;
    private static native long getNumFaces(long pointer);

    public long getNumFaces() {
        return getNumFaces(super.pointer);
    }

    // FT_Long face_index;
    private static native long getFaceIndex(long pointer);

    public long getFaceIndex() {
        return getFaceIndex(super.pointer);
    }

    // FT_Long face_flags;
    private static native int getFaceFlags(long pointer);

    public FaceFlags getFaceFlags() {
        final int flags = getFaceFlags(super.pointer);
        return new FaceFlags(flags);
    }

    // FT_Long style_flags;
    private static native int getStyleFlags(long pointer);

    public StyleFlags getStyleFlags() {
        final int flags = getStyleFlags(super.pointer);
        return new StyleFlags(flags);
    }

    // FT_Long num_glyphs;
    private static native long getNumGlyphs(long pointer);

    public long getNumGlyphs() {
        return getNumGlyphs(super.pointer);
    }

    // FT_String* family_name;
    private static native String getFamilyName(long pointer);

    public String getFamilyName() {
        return getFamilyName(super.pointer);
    }

    // FT_String* style_name;
    private static native String getStyleName(long pointer);

    public String getStyleName() {
        return getStyleName(super.pointer);
    }

    // FT_Int num_fixed_sizes;
    private static native int getNumFixedSizes(long pointer);

    public int getNumFixedSizes() {
        return getNumFixedSizes(super.pointer);
    }

    // FT_Bitmap_Size* available_sizes;
    private static native long[] getAvailableSizes(long pointer);

    public FTBitmapSize[] getAvailableSizes() {
        final long[] pointers = getAvailableSizes(super.pointer);

        final FTBitmapSize[] objects = new FTBitmapSize[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTBitmapSize::new);

        return objects;
    }

    // FT_Int num_charmaps;
    private static native int getNumCharmaps(long pointer);

    public int getNumCharmaps() {
        return getNumCharmaps(super.pointer);
    }

    // FT_CharMap* charmaps;
    private static native long[] getCharmaps(long pointer);

    public FTCharMap[] getCharmaps() {
        final long[] pointers = getCharmaps(super.pointer);

        final FTCharMap[] objects = new FTCharMap[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTCharMap::new);

        return objects;
    }

    // FT_BBox bbox;
    private static native long getBBox(long pointer);

    public FTBBox getBBox() {
        final long pointer = getBBox(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTBBox::new);
    }

    // FT_UShort units_per_EM;
    private static native int getUnitsPerEM(long pointer);

    public int getUnitsPerEM() {
        return getUnitsPerEM(super.pointer);
    }

    // FT_Short ascender;
    private static native short getAscender(long pointer);

    public short getAscender() {
        return getAscender(super.pointer);
    }

    // FT_Short descender;
    private static native short getDescender(long pointer);

    public short getDescender() {
        return getDescender(super.pointer);
    }

    // FT_Short height;
    private static native short getHeight(long pointer);

    public short getHeight() {
        return getHeight(super.pointer);
    }

    // FT_Short max_advance_width;
    private static native short getMaxAdvanceWidth(long pointer);

    public short getMaxAdvanceWidth() {
        return getMaxAdvanceWidth(super.pointer);
    }

    // FT_Short max_advance_height;
    private static native short getMaxAdvanceHeight(long pointer);

    public short getMaxAdvanceHeight() {
        return getMaxAdvanceHeight(super.pointer);
    }

    // FT_Short underline_position;
    private static native short getUnderlinePosition(long pointer);

    public short getUnderlinePosition() {
        return getUnderlinePosition(super.pointer);
    }

    // FT_Short underline_thickness;
    private static native short getUnderlineThickness(long pointer);

    public short getUnderlineThickness() {
        return getUnderlineThickness(super.pointer);
    }

    // FT_GlyphSlot glyph;
    private static native long getGlyph(long pointer);

    public FTGlyphSlot getGlyph() {
        final long pointer = getGlyph(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyphSlot::new);
    }

    // FT_Size size;
    private static native long getSize(long pointer);

    public FTSize getSize() {
        final long pointer = getSize(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTSize::new);
    }

    // FT_CharMap charmap;
    private static native long getCharmap(long pointer);

    public FTCharMap getCharmap() {
        final long pointer = getCharmap(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTCharMap::new);
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

    public void done() {
        final FTError error = FreeType.ftDoneFace(this);
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

    public void loadGlyph(long glyphIndex, int loadFlags) {
        final FTError error = FreeType.ftLoadGlyph(this, glyphIndex, loadFlags);
        error.checkError();
    }

    public void loadGlyph(long glyphIndex) {
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

    public void getKerning(int leftGlyph, int rightGlyph, FTKerningMode kerningMode, FTVector dstKerning) {
        final FTError error = FreeType.ftGetKerning(this, leftGlyph, rightGlyph, kerningMode, dstKerning);
        error.checkError();
    }

    public FTVector getKerning(int leftGlyph, int rightGlyph, FTKerningMode kerningMode) {
        final FTVector dstKerning = FTVector.newInstance();
        this.getKerning(leftGlyph, rightGlyph, kerningMode, dstKerning);
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

    public long getCharIndex(int charcode) {
        return FreeType.ftGetCharIndex(this, charcode);
    }

    public long getFirstChar(long[] dstGIndex) {
        return FreeType.ftGetFirstChar(this, dstGIndex);
    }

    public long getNextChar(long charcode, long[] dstGIndex) {
        return FreeType.ftGetNextChar(this, charcode, dstGIndex);
    }

    public void properties(int numProperties, FTParameter[] properties) {
        final FTError error = FreeType.ftFaceProperties(this, numProperties, properties);
        error.checkError();
    }

    public void properties(FTParameter[] properties) {
        final FTError error = FreeType.ftFaceProperties(this, properties);
        error.checkError();
    }

    public long getNameIndex(String glyphName) {
        return FreeType.ftGetNameIndex(this, glyphName);
    }

    public void getGlyphName(int glyphIndex, ByteBuffer buffer, long bufferMax) {
        final FTError error = FreeType.ftGetGlyphName(this, glyphIndex, buffer, bufferMax);
        error.checkError();
    }

    public void getGlyphName(int glyphIndex, ByteBuffer buffer) {
        final FTError error = FreeType.ftGetGlyphName(this, glyphIndex, buffer);
        error.checkError();
    }

    public String getPostscriptName() {
        return FreeType.ftGetPostscriptName(this);
    }

    public int getFSTypeFlagsRaw() {
        return FreeType.ftGetFSTypeFlagsRaw(this);
    }

    public FSTypeFlags getFSTypeFlags() {
        return FreeType.ftGetFSTypeFlags(this);
    }

    public long getCharVariantIndex(long charcode, long variantSelector) {
        return FreeType.ftFaceGetCharVariantIndex(this, charcode, variantSelector);
    }

    public int getCharVariantIsDefault(long charcode, long variantSelector) {
        return FreeType.ftFaceGetCharVariantIsDefault(this, charcode, variantSelector);
    }

    public long getVariantSelectors() {
        return FreeType.ftFaceGetVariantSelectors(this);
    }

    public long getVariantsOfChar(long charcode) {
        return FreeType.ftFaceGetVariantsOfChar(this, charcode);
    }

    public long getCharsOfVariant(long variantSelector) {
        return FreeType.ftFaceGetCharsOfVariant(this, variantSelector);
    }

    public boolean checkTrueTypePatents() {
        return FreeType.ftFaceCheckTrueTypePatents(this);
    }

    public boolean setUnpatentedHinting(boolean value) {
        return FreeType.ftFaceSetUnpatentedHinting(this, value);
    }


    private static native long newStruct();

    public static FTFace newInstance() {
        return new FTFace(newStruct());
    }

}