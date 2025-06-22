package generaloss.freetype.glyph;

public enum FTGlyphBBoxMode {

    UNSCALED  (0),
    SUBPIXELS (0),
    GRIDFIT   (1),
    TRUNCATE  (2),
    PIXELS    (3);

    public final int value;

    FTGlyphBBoxMode(int value) {
        this.value = value;
    }

}
