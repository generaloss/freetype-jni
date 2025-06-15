package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class StyleFlags extends BitMask<FTStyleFlag> {

    public StyleFlags() { }

    public StyleFlags(int bits) {
        super(bits);
    }


    public boolean hasItalic() {
        return super.has(FTStyleFlag.ITALIC);
    }

    public boolean hasBold() {
        return super.has(FTStyleFlag.BOLD);
    }


    @Override
    public String toString() {
        return super.toString(FTStyleFlag.class);
    }

}
