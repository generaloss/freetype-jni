package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class SubglyphFlags extends BitMask<FTSubglyphFlag> {

    public SubglyphFlags() { }

    public SubglyphFlags(int bits) {
        super(bits);
    }


    public boolean hasArgsAreWords() {
        return super.has(FTSubglyphFlag.ARGS_ARE_WORDS);
    }

    public boolean hasArgsAreXYvalues() {
        return super.has(FTSubglyphFlag.ARGS_ARE_XY_VALUES);
    }

    public boolean hasRoundXYtoGrid() {
        return super.has(FTSubglyphFlag.ROUND_XY_TO_GRID);
    }

    public boolean hasScale() {
        return super.has(FTSubglyphFlag.SCALE);
    }

    public boolean hasXYscale() {
        return super.has(FTSubglyphFlag.XY_SCALE);
    }

    public boolean has2x2() {
        return super.has(FTSubglyphFlag._2X2);
    }

    public boolean hasUseMyMetrics() {
        return super.has(FTSubglyphFlag.USE_MY_METRICS);
    }


    @Override
    public String toString() {
        return super.toString(FTSubglyphFlag.class);
    }

    @Override
    public SubglyphFlags copy() {
        return new SubglyphFlags(super.getBits());
    }

}