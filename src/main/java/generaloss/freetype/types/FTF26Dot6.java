package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTF26Dot6 extends FTStruct { // struct done.

    public FTF26Dot6(long pointer) {
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


    private static native void setRawValue(long pointer, int value);

    public void setRawValue(int value) {
        setRawValue(super.pointer, value);
    }

    public void set(float value) {
        final int raw = of(value);
        setRawValue(super.pointer, raw);
    }


    @Override
    public String toString() {
        final int raw = this.getRawValue();
        return "FTFixed{float=" + toFloat(raw) + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    private static native long newStruct();

    public static FTF26Dot6 newInstance() {
        return new FTF26Dot6(newStruct());
    }


    private static final int BITS = 26;
    private static final int UNIT = (1 << BITS); // 67108864
    private static final int HALF_UNIT = (1 << (BITS - 1)); // 33554432
    private static final int MASK = (UNIT - 1); // 0x3FFFFFF
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
