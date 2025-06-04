package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

import java.util.StringJoiner;

public class FaceFlags extends BitMask {

    public FaceFlags() { }

    public FaceFlags(int bits) {
        super(bits);
    }

    public boolean has(FTFaceFlag flag) {
        return super.has(flag.value);
    }

    public FaceFlags set(FTFaceFlag flag) {
        super.set(flag.value);
        return this;
    }

    public FaceFlags clear(FTFaceFlag flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasScalable() {
        return this.has(FTFaceFlag.SCALABLE);
    }

    public boolean hasFixedSizes() {
        return this.has(FTFaceFlag.FIXED_SIZES);
    }

    public boolean hasFixedWidth() {
        return this.has(FTFaceFlag.FIXED_WIDTH);
    }

    public boolean hasSFNT() {
        return this.has(FTFaceFlag.SFNT);
    }

    public boolean hasHorizontal() {
        return this.has(FTFaceFlag.HORIZONTAL);
    }

    public boolean hasVertical() {
        return this.has(FTFaceFlag.VERTICAL);
    }

    public boolean hasKerning() {
        return this.has(FTFaceFlag.KERNING);
    }

    public boolean hasFastGlyphs() {
        return this.has(FTFaceFlag.FAST_GLYPHS);
    }

    public boolean hasMultipleMasters() {
        return this.has(FTFaceFlag.MULTIPLE_MASTERS);
    }

    public boolean hasGlyphNames() {
        return this.has(FTFaceFlag.GLYPH_NAMES);
    }

    public boolean hasExternalStream() {
        return this.has(FTFaceFlag.EXTERNAL_STREAM);
    }

    public boolean hasHinter() {
        return this.has(FTFaceFlag.HINTER);
    }

    public boolean hasCIDKeyed() {
        return this.has(FTFaceFlag.CID_KEYED);
    }

    public boolean hasTricky() {
        return this.has(FTFaceFlag.TRICKY);
    }


    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ");
        for(FTFaceFlag flag: FTFaceFlag.values())
            joiner.add(flag.toString() + "=" + this.has(flag));
        return "FaceFlags{" + joiner + "}";
    }

}
