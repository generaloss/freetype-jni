package generaloss.freetype.image;

import generaloss.freetype.BitMask;
import generaloss.freetype.freetype.SubglyphFlags;

public class OutlineFlags extends BitMask<FTOutlineFlag> {

    public OutlineFlags() { }

    public OutlineFlags(int bits) {
        super(bits);
    }


    public boolean hasOwner() {
        return super.has(FTOutlineFlag.OWNER);
    }

    public boolean hasEvenOddFill() {
        return super.has(FTOutlineFlag.EVEN_ODD_FILL);
    }

    public boolean hasReverseFill() {
        return super.has(FTOutlineFlag.REVERSE_FILL);
    }

    public boolean hasIgnoreDropouts() {
        return super.has(FTOutlineFlag.IGNORE_DROPOUTS);
    }

    public boolean hasSmartDropouts() {
        return super.has(FTOutlineFlag.SMART_DROPOUTS);
    }

    public boolean hasIncludeStubs() {
        return super.has(FTOutlineFlag.INCLUDE_STUBS);
    }

    public boolean hasOverlap() {
        return super.has(FTOutlineFlag.OVERLAP);
    }

    public boolean hasHighPrecision() {
        return super.has(FTOutlineFlag.HIGH_PRECISION);
    }

    public boolean hasSinglePass() {
        return super.has(FTOutlineFlag.SINGLE_PASS);
    }


    @Override
    public String toString() {
        return super.toString(FTOutlineFlag.class);
    }

    @Override
    public OutlineFlags copy() {
        return new OutlineFlags(super.getBits());
    }

}