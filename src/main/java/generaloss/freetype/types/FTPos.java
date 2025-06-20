package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

import java.util.Objects;

public class FTPos extends FTStruct { // struct done.

    public FTPos(long pointer) {
        super(pointer);
    }

    public FTPos() {
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

    public FTPos setRawValue(int value) {
        setRawValue(super.pointer, value);
        return this;
    }

    public FTPos set(float value) {
        final int raw = of(value);
        return this.setRawValue(raw);
    }

    public FTPos set(FTPos value) {
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
        final FTPos pos = (FTPos) object;
        return (this.getRawValue() == pos.getRawValue());
    }

    @Override
    public int hashCode() {
        return this.getRawValue();
    }


    public static final int BITS = 6;
    public static final int UNIT = (1 << BITS); // 64
    public static final int HALF_UNIT = (1 << (BITS - 1)); // 32
    public static final int MASK = (UNIT - 1); // 0x3F
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
