package generaloss.freetype.freetype;

import generaloss.freetype.BitMaskable;

public enum FTStyleFlag implements BitMaskable {

    /** The face style is italic or oblique. */
    ITALIC, // 1

    /** The face is bold. */
    BOLD;   // 2

    private final int bit;

    FTStyleFlag() {
        this.bit = (1 << this.ordinal());
    }

    @Override
    public int getBit() {
        return bit;
    }


    public boolean isItalic() {
        return this == ITALIC;
    }

    public boolean isBold() {
        return this == BOLD;
    }

}
