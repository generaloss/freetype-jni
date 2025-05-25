package generaloss.freetype.freetype;

public enum FTKerningMode {

    /** Return grid-fitted kerning distances in 26.6 fractional pixels. */
    DEFAULT,  // 0

    /** Return un-grid-fitted kerning distances in 26.6 fractional pixels. */
    UNFITTED, // 1

    /** Return the kerning vector in original font units. */
    UNSCALED; // 2

    public final int value;

    FTKerningMode() {
        this.value = this.ordinal();
    }

    public static FTKerningMode byValue(int value) {
        return values()[value];
    }

}
