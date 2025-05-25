package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class StyleFlags extends BitMask {

    public StyleFlags() { }

    public StyleFlags(int bits) {
        super(bits);
    }


    public boolean has(FTStyleFlag flag) {
        return super.has(flag.value);
    }

    public StyleFlags set(FTStyleFlag flag) {
        super.set(flag.value);
        return this;
    }

    public StyleFlags clear(FTStyleFlag flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasItalic() {
        return this.has(FTStyleFlag.ITALIC);
    }

    public boolean hasBold() {
        return this.has(FTStyleFlag.BOLD);
    }


}
