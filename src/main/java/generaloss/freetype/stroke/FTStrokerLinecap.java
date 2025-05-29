package generaloss.freetype.stroke;

public enum FTStrokerLinecap {

    /** The end of lines is rendered as a full stop on the last point itself. */
    BUTT,   // 0

    /** The end of lines is rendered as a half-circle around the last point. */
    ROUND,  // 1

    /** The end of lines is rendered as a square around the last point. */
    SQUARE; // 2

    public final int value;

    FTStrokerLinecap() {
        this.value = this.ordinal();
    }

    public static FTStrokerLinecap byValue(int value) {
        return values()[value];
    }

}
