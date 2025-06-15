package generaloss.freetype.freetype;

public enum FTLoadTarget {

    /** Default render mode; it corresponds to 8-bit anti-aliased bitmaps. */
    NORMAL, // 0x0000 (= 0)

    /** This is equivalent to FTRenderMode.NORMAL. It is only defined as a separate value because render modes are also used indirectly to define hinting algorithm selectors. See FTLoadTarget.XXX for details. */
    LIGHT,  // 0x1000 = 65536

    /** This mode corresponds to 1-bit bitmaps (with 2 levels of opacity). */
    MONO,   // 0x2000 = 131072

    /** This mode corresponds to horizontal RGB and BGR subpixel displays like LCD screens. It produces 8-bit bitmaps that are 3 times the width of the original glyph outline in pixels, and which use the FTPixelMode.LCD mode. */
    LCD,    // 0x3000 = 196608

    /** This mode corresponds to vertical RGB and BGR subpixel displays (like PDA screens, rotated LCD displays, etc.). It produces 8-bit bitmaps that are 3 times the height of the original glyph outline in pixels and use the FTPixelMode.LCD_V mode. */
    LCD_V,  // 0x4000 = 262144

    /** The positive (unsigned) 8-bit bitmap values can be converted to the single-channel signed distance field (SDF) by subtracting 128, with the positive and negative results corresponding to the inside and the outside of a glyph contour, respectively. The distance units are arbitrarily determined by an adjustable spread property. */
    SDF;    // 0x5000 = 327680


    public static final int MASK = 0x7000;

    public final int value;

    FTLoadTarget() {
        this.value = (this.ordinal() & 15) << 16;
    }


    public static FTLoadTarget byValue(int value) {
        return values()[value >> 12];
    }

}
