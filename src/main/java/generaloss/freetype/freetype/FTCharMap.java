package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;

public class FTCharMap extends FTStruct { // struct done.

    public FTCharMap(long pointer) {
        super(pointer);
    }


    // FT_Face face;
    private static native long getFace(long pointer);

    public FTFace getFace() {
        final long pointer = getFace(super.pointer);
        return FTStructCache.getOrCreate(FTFace.class, pointer, FTFace::new);
    }

    // FT_Encoding encoding;
    private static native int getEncoding(long pointer);

    public FTEncoding getEncoding() {
        final int raw = getEncoding(super.pointer);
        return FTEncoding.byValue(raw);
    }

    // FT_UShort platform_id;
    private static native int getPlatformID(long pointer);

    public int getPlatformID() {
        return getPlatformID(super.pointer);
    }

    // FT_UShort encoding_id;
    private static native int getEncodingID(long pointer);

    public int getEncodingID() {
        return getEncodingID(super.pointer);
    }


    public int getIndex() {
        return FreeType.ftGetCharmapIndex(this);
    }

}
