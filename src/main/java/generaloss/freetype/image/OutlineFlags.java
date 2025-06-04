package generaloss.freetype.image;

import generaloss.freetype.BitMask;
import generaloss.freetype.freetype.FTFaceFlag;

import java.util.StringJoiner;

public class OutlineFlags extends BitMask {

    public OutlineFlags() { }

    public OutlineFlags(int bits) {
        super(bits);
    }


    public boolean has(FTOutlineFlag flag) {
        return super.has(flag.value);
    }

    public OutlineFlags set(FTOutlineFlag flag) {
        super.set(flag.value);
        return this;
    }

    public OutlineFlags clear(FTOutlineFlag flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasOwner() {
        return this.has(FTOutlineFlag.OWNER);
    }

    public boolean hasEvenOddFill() {
        return this.has(FTOutlineFlag.EVEN_ODD_FILL);
    }

    public boolean hasReverseFill() {
        return this.has(FTOutlineFlag.REVERSE_FILL);
    }

    public boolean hasIgnoreDropouts() {
        return this.has(FTOutlineFlag.IGNORE_DROPOUTS);
    }

    public boolean hasSmartDropouts() {
        return this.has(FTOutlineFlag.SMART_DROPOUTS);
    }

    public boolean hasIncludeStubs() {
        return this.has(FTOutlineFlag.INCLUDE_STUBS);
    }

    public boolean hasOverlap() {
        return this.has(FTOutlineFlag.OVERLAP);
    }

    public boolean hasHighPrecision() {
        return this.has(FTOutlineFlag.HIGH_PRECISION);
    }

    public boolean hasSinglePass() {
        return this.has(FTOutlineFlag.SINGLE_PASS);
    }


    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ");
        for(FTOutlineFlag flag: FTOutlineFlag.values())
            joiner.add(flag.toString() + "=" + this.has(flag));
        return "FaceFlags{" + joiner + "}";
    }

}