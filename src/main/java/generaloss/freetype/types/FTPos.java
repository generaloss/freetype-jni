package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTPos extends FTStruct { // struct done.

    public FTPos(long pointer) {
        super(pointer);
    }


    private static native long getRawValue(long pointer);

    public long getRawValue() {
        return getRawValue(super.pointer);
    }


    public float getFloat() {
        final long raw = this.getRawValue();
        return toFloat(raw);
    }

    public int getIntFloor() {
        final long raw = this.getRawValue();
        return toIntFloor(raw);
    }

    public int getIntRound() {
        final long raw = this.getRawValue();
        return toIntRound(raw);
    }

    public int getIntCeil() {
        final long raw = this.getRawValue();
        return toIntCeil(raw);
    }


    @Override
    public String toString() {
        final long raw = this.getRawValue();
        return "FTFixed{float=" + toFloat(raw) + ", raw=0x" + Long.toHexString(raw) + "}";
    }


    private static native long newStruct();

    public static FTPos newInstance() {
        return new FTPos(newStruct());
    }


    private static final int BITS = 6;
    private static final long UNIT = (1 << BITS); // 64
    private static final long HALF_UNIT = (1 << (BITS - 1)); // 32
    private static final long MASK = (UNIT - 1); // 0x3F
    private static final float UNIT_FRAC = (1F / UNIT);

    public static float toFloat(long rawValue) {
        return (rawValue * UNIT_FRAC);
    }

    public static int toIntFloor(long rawValue) {
        return (int) (rawValue >> BITS);
    }

    public static int toIntRound(long rawValue) {
        return (int) ((rawValue + HALF_UNIT) >> BITS);
    }

    public static int toIntCeil(long rawValue) {
        return (int) (((rawValue + MASK) & ~MASK) >> BITS);
    }


    public static long of(float value) {
        return (long) (value * UNIT);
    }

}
