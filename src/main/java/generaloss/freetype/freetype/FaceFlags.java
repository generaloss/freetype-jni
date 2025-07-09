package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class FaceFlags extends BitMask<FTFaceFlag> {

    public FaceFlags() { }

    public FaceFlags(int bits) {
        super(bits);
    }


    public boolean hasScalable() {
        return super.has(FTFaceFlag.SCALABLE);
    }

    public boolean hasFixedSizes() {
        return super.has(FTFaceFlag.FIXED_SIZES);
    }

    public boolean hasFixedWidth() {
        return super.has(FTFaceFlag.FIXED_WIDTH);
    }

    public boolean hasSFNT() {
        return super.has(FTFaceFlag.SFNT);
    }

    public boolean hasHorizontal() {
        return super.has(FTFaceFlag.HORIZONTAL);
    }

    public boolean hasVertical() {
        return super.has(FTFaceFlag.VERTICAL);
    }

    public boolean hasKerning() {
        return super.has(FTFaceFlag.KERNING);
    }

    public boolean hasFastGlyphs() {
        return super.has(FTFaceFlag.FAST_GLYPHS);
    }

    public boolean hasMultipleMasters() {
        return super.has(FTFaceFlag.MULTIPLE_MASTERS);
    }

    public boolean hasGlyphNames() {
        return super.has(FTFaceFlag.GLYPH_NAMES);
    }

    public boolean hasExternalStream() {
        return super.has(FTFaceFlag.EXTERNAL_STREAM);
    }

    public boolean hasHinter() {
        return super.has(FTFaceFlag.HINTER);
    }

    public boolean hasCIDKeyed() {
        return super.has(FTFaceFlag.CID_KEYED);
    }

    public boolean hasTricky() {
        return super.has(FTFaceFlag.TRICKY);
    }


    @Override
    public String toString() {
        return super.toString(FTFaceFlag.class);
    }

    @Override
    public FaceFlags copy() {
        return new FaceFlags(super.getBits());
    }

}
