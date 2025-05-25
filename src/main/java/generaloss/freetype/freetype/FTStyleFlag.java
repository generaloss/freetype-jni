package generaloss.freetype.freetype;

public enum FTStyleFlag {

    /** The face style is italic or oblique. */
    ITALIC, // 1

    /** The face is bold. */
    BOLD;   // 2

    public final int value;

    FTStyleFlag() {
        this.value = (1 << this.ordinal());
    }


    public boolean isItalic() {
        return this == ITALIC;
    }

    public boolean isBold() {
        return this == BOLD;
    }

}
