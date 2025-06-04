package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

import java.util.StringJoiner;

public class SubglyphFlags extends BitMask {

    public SubglyphFlags() { }

    public SubglyphFlags(int bits) {
        super(bits);
    }


    public boolean has(FTSubglyphFlag flag) {
        return super.has(flag.value);
    }

    public SubglyphFlags set(FTSubglyphFlag flag) {
        super.set(flag.value);
        return this;
    }

    public SubglyphFlags clear(FTSubglyphFlag flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasArgsAreWords() {
        return this.has(FTSubglyphFlag.ARGS_ARE_WORDS);
    }

    public boolean hasArgsAreXYvalues() {
        return this.has(FTSubglyphFlag.ARGS_ARE_XY_VALUES);
    }

    public boolean hasRoundXYtoGrid() {
        return this.has(FTSubglyphFlag.ROUND_XY_TO_GRID);
    }

    public boolean hasScale() {
        return this.has(FTSubglyphFlag.SCALE);
    }

    public boolean hasXYscale() {
        return this.has(FTSubglyphFlag.XY_SCALE);
    }

    public boolean has2x2() {
        return this.has(FTSubglyphFlag.FLAG_2X2);
    }

    public boolean hasUseMyMetrics() {
        return this.has(FTSubglyphFlag.USE_MY_METRICS);
    }


    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ");
        for(FTSubglyphFlag flag: FTSubglyphFlag.values())
            joiner.add(flag.toString() + "=" + this.has(flag));
        return "FaceFlags{" + joiner + "}";
    }

}