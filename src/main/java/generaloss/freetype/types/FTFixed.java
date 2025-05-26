package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

/** 16.16 fixed-point value utility */
public class FTFixed extends FTStruct {

    public FTFixed(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTFixed newInstance() {
        return new FTFixed(newStruct());
    }


    private static native int getRawValue(long pointer);

    public int getRawValue() {
        return getRawValue(super.pointer);
    }


    public float getFloat() {
        final int raw = this.getRawValue();
        return toFloat(raw);
    }

    public int getFloor() {
        final int raw = this.getRawValue();
        return toIntFloor(raw);
    }

    public int getRound() {
        final int raw = this.getRawValue();
        return toIntRound(raw);
    }

    public int getCeil() {
        final int raw = this.getRawValue();
        return toIntCeil(raw);
    }

    @Override
    public String toString() {
        final int raw = this.getRawValue();
        return "FTFixed{float=" + toFloat(raw) + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    private static final int BITS = 16;
    private static final int UNIT = (1 << BITS); // 65536
    private static final int HALF_UNIT = (1 << (BITS - 1)); // 32768
    private static final int MASK = (UNIT - 1); // 0xFFFF
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