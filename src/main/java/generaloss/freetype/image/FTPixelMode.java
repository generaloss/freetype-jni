package generaloss.freetype.image;

public enum FTPixelMode {

    NONE,  // 0

    /** A monochrome bitmap, using 1 bit per pixel. Note that pixels are stored in most-significant order (MSB), which means that the left-most pixel in a byte has value 128. */
    MONO,  // 1

    /** An 8-bit bitmap, generally used to represent anti-aliased glyph images. Each pixel is stored in one byte. Note that the number of ‘gray’ levels is stored in the num_grays field of the FT_Bitmap structure (it generally is 256). */
    GRAY,  // 2

    /** A 2-bit per pixel bitmap, used to represent embedded anti-aliased bitmaps in font files according to the OpenType specification. We haven't found a single font using this format, however. */
    GRAY2, // 3

    /** A 4-bit per pixel bitmap, representing embedded anti-aliased bitmaps in font files according to the OpenType specification. We haven't found a single font using this format, however. */
    GRAY4, // 4

    /** An 8-bit bitmap, representing RGB or BGR decimated glyph images used for display on LCD displays; the bitmap is three times wider than the original glyph image. See also FTRenderMode.LCD. */
    LCD,   // 5

    /** An 8-bit bitmap, representing RGB or BGR decimated glyph images used for display on rotated LCD displays; the bitmap is three times taller than the original glyph image. See also FTRenderMoce.LCD_V. */
    LCD_V, // 6

    /** An image with four 8-bit channels per pixel, representing a color image (such as emoticons) with alpha channel. For each pixel, the format is BGRA, which means, the blue channel comes first in memory. The color channels are pre-multiplied and in the sRGB colorspace. For example, full red at half-translucent opacity will be represented as ‘00,00,80,80’, not ‘00,00,FF,80’. See also FTLoad.COLOR. */
    BGRA;  // 7

    public final int value;

    FTPixelMode() {
        this.value = this.ordinal();
    }

    public static FTPixelMode byValue(int value) {
        return values()[value];
    }

}
