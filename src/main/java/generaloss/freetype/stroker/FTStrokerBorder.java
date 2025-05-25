package generaloss.freetype.stroker;

public enum FTStrokerBorder {

    /** Select the left border, relative to the drawing direction. */
    LEFT,  // 0

    /** Select the right border, relative to the drawing direction. */
    RIGHT; // 1

    public final int value;

    FTStrokerBorder() {
        this.value = this.ordinal();
    }

    public static FTStrokerBorder byValue(int value) {
        return values()[value];
    }

}
