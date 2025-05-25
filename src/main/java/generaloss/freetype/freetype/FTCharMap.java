package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;

public class FTCharMap extends FTStruct { // struct done.

    private final FTFace face;

    public FTCharMap(long pointer, FTFace face) {
        super(pointer);
        this.face = face;
    }


    // FT_Face face;
    /** A handle to the parent face object. */
    public FTFace getFace() {
        return face;
    }


    // FT_Encoding encoding;
    private static native int getEncoding(long pointer);

    /** An FT_Encoding tag identifying the charmap.
     * Use this with FT_Select_Charmap.
     * */
    public FTEncoding getEncoding() {
        final int raw = getEncoding(super.pointer);
        return FTEncoding.byValue(raw);
    }


    // FT_UShort platform_id;
    private static native int getPlatformID(long pointer);

    /** An ID number describing the platform for the following encoding ID.
     * This comes directly from the TrueType specification and gets emulated for other formats.
     * */
    public int getPlatformID() {
        return getPlatformID(super.pointer);
    }


    // FT_UShort encoding_id;
    private static native int getEncodingID(long pointer);

    /** A platform-specific encoding number.
     * This also comes from the TrueType specification and gets emulated similarly.
     * */
    public int getEncodingID() {
        return getEncodingID(super.pointer);
    }

}
