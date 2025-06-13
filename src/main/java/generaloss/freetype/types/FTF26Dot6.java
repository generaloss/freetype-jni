package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTF26Dot6 extends FTStruct { // struct done.

    public FTF26Dot6(long pointer) {
        super(pointer);
    }

    public FTF26Dot6() {
        this(createPointer());
    }

    static {
        FreeType.init();
    }

    private static native long createPointer();

    private static native void freePointer(long pointer);

    public void free() {
        freePointer(this.pointer);
        super.destroyPointer();
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

    public FTF26Dot6 setRawValue(int value) {
        setRawValue(super.pointer, value);
        return this;
    }

    public FTF26Dot6 set(float value) {
        final int raw = of(value);
        return this.setRawValue(raw);
    }

    public FTF26Dot6 set(FTF26Dot6 value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.set(value.getFloat());
    }


    @Override
    public String toString() {
        final int raw = this.getRawValue();
        return "FTFixed{float=" + toFloat(raw) + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    public static final int BITS = 26;
    public static final int UNIT = (1 << BITS); // 67108864
    public static final int HALF_UNIT = (1 << (BITS - 1)); // 33554432
    public static final int MASK = (UNIT - 1); // 0x3FFFFFF
    public static final float UNIT_FRAC = (1F / UNIT);

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
