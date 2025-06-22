package generaloss.freetype.stroke;

public enum FTStrokerLineCap {

    /** The end of lines is rendered as a full stop on the last point itself. */
    BUTT,   // 0

    /** The end of lines is rendered as a half-circle around the last point. */
    ROUND,  // 1

    /** The end of lines is rendered as a square around the last point. */
    SQUARE; // 2

    public final int value;

    FTStrokerLineCap() {
        this.value = this.ordinal();
    }

    public static FTStrokerLineCap byValue(int value) {
        return values()[value];
    }

}
