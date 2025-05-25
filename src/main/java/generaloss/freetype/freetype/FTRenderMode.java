package generaloss.freetype.freetype;

public enum FTRenderMode {

    /** Default render mode; it corresponds to 8-bit anti-aliased bitmaps. */
    NORMAL, // 0

    /** This is equivalent to FT_RENDER_MODE_NORMAL. It is only defined as a separate value because render modes are also used indirectly to define hinting algorithm selectors. See FT_LOAD_TARGET_XXX for details. */
    LIGHT,  // 1

    /** This mode corresponds to 1-bit bitmaps (with 2 levels of opacity). */
    MONO,   // 2

    /** This mode corresponds to horizontal RGB and BGR subpixel displays like LCD screens. It produces 8-bit bitmaps that are 3 times the width of the original glyph outline in pixels, and which use the FT_PIXEL_MODE_LCD mode. */
    LCD,    // 3

    /** This mode corresponds to vertical RGB and BGR subpixel displays (like PDA screens, rotated LCD displays, etc.). It produces 8-bit bitmaps that are 3 times the height of the original glyph outline in pixels and use the FT_PIXEL_MODE_LCD_V mode. */
    LCD_V,  // 4

    /** The positive (unsigned) 8-bit bitmap values can be converted to the single-channel signed distance field (SDF) by subtracting 128, with the positive and negative results corresponding to the inside and the outside of a glyph contour, respectively. The distance units are arbitrarily determined by an adjustable spread property. */
    SDF;    // 5

    public final int value;

    FTRenderMode() {
        this.value = this.ordinal();
    }

    public static FTRenderMode byValue(int value) {
        return values()[value];
    }

}
