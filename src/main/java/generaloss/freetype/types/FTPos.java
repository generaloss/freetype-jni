package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTPos extends FTStruct { // struct done.

    public FTPos(long pointer) {
        super(pointer);
    }


    private static native int getRawValue(long pointer);

    public int getRawValue() {
        return getRawValue(super.pointer);
    }


    public float getFloat() {
        final int raw = this.getRawValue();
        return toFloat(raw);
    }

    public int getIntFloor() {
        final int raw = this.getRawValue();
        return toIntFloor(raw);
    }

    public int getIntRound() {
        final int raw = this.getRawValue();
        return toIntRound(raw);
    }

    public int getIntCeil() {
        final int raw = this.getRawValue();
        return toIntCeil(raw);
    }


    @Override
    public String toString() {
        final int raw = this.getRawValue();
        return "FTFixed{float=" + toFloat(raw) + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    private static native long newStruct();

    public static FTPos newInstance() {
        return new FTPos(newStruct());
    }


    private static final int BITS = 6;
    private static final int UNIT = (1 << BITS); // 64
    private static final int HALF_UNIT = (1 << (BITS - 1)); // 32
    private static final int MASK = (UNIT - 1); // 0x3F
    private static final float UNIT_FRAC = (1F / UNIT);

    public static float toFloat(int rawValue) {
        return (rawValue * UNIT_FRAC);
    }

    public static int toIntFloor(int rawValue) {
        return (rawValue >> BITS);
    }

    public static int toIntRound(int rawValue) {
        return ((rawValue + HALF_UNIT) >> BITS);
    }

    public static int toIntCeil(int rawValue) {
        return (((rawValue + MASK) & ~MASK) >> BITS);
    }


    public static int of(float value) {
        return (int) (value * UNIT);
    }

}
