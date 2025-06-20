package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTFixed extends FTStruct { // struct done.

    public FTFixed(long pointer) {
        super(pointer);
    }

    public FTFixed() {
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

    public FTFixed setRawValue(int value) {
        setRawValue(super.pointer, value);
        return this;
    }

    public FTFixed set(float value) {
        final int raw = of(value);
        return this.setRawValue(raw);
    }

    public FTFixed set(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.set(value.getFloat());
    }


    @Override
    public String toString() {
        final int raw = this.getRawValue();
        return (super.toString() + "{float=" + toFloat(raw) + ", raw=" + raw + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTFixed fixed = (FTFixed) object;
        return (this.getRawValue() == fixed.getRawValue());
    }

    @Override
    public int hashCode() {
        return this.getRawValue();
    }


    public static final int BITS = 16;
    public static final int UNIT = (1 << BITS); // 65536
    public static final int HALF_UNIT = (1 << (BITS - 1)); // 32768
    public static final int MASK = (UNIT - 1); // 0xFFFF
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