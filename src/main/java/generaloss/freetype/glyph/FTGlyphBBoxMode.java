package generaloss.freetype.glyph;

import generaloss.freetype.types.PosType;

public enum FTGlyphBBoxMode {

    /** Return unscaled font units. */
    UNSCALED  (0, PosType.INT),
    /** Return unfitted 26.6 coordinates. */
    SUBPIXELS (0, PosType.F26DOT6),
    /** Return grid-fitted 26.6 coordinates. */
    GRIDFIT   (1, PosType.F26DOT6),
    /** Return coordinates in integer pixels. */
    TRUNCATE  (2, PosType.INT),
    /** Return grid-fitted pixel coordinates. */
    PIXELS    (3, PosType.INT);

    public final int value;
    public final PosType posType;

    FTGlyphBBoxMode(int value, PosType posType) {
        this.value = value;
        this.posType = posType;
    }

}
