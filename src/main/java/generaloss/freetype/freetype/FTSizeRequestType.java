package generaloss.freetype.freetype;

public enum FTSizeRequestType {

    /** The nominal size.
     * The units_per_EM field of FT_FaceRec is used to determine both scaling values.
     * This is the standard scaling found in most applications.
     * In particular, use this size request type for TrueType fonts if they provide optical scaling or something similar.
     * Note, however, that units_per_EM is a rather abstract value which bears no relation to the actual size of the glyphs in a font. */
    NOMINAL,  // 0
    /** The real dimension.
     * The sum of the ascender and (minus of) the descender fields of FT_FaceRec is used to determine both scaling values. */
    REAL_DIM, // 1
    /** The font bounding box.
     * The width and height of the bbox field of FT_FaceRec are used to determine the horizontal and vertical scaling value, respectively. */
    BBOX,     // 2
    /** The max_advance_width field of FT_FaceRec is used to determine the horizontal scaling value; the vertical scaling value is determined the same way as FT_SIZE_REQUEST_TYPE_REAL_DIM does.
     * Finally, both scaling values are set to the smaller one.
     * This type is useful if you want to specify the font size for, say, a window of a given dimension and 80x24 cells. */
    CELL,     // 3
    /** Specify the scaling values directly. */
    SCALES;   // 4

    public final int value;

    FTSizeRequestType() {
        this.value = this.ordinal();
    }

    public static FTSizeRequestType byValue(int value) {
        return values()[value];
    }

}
