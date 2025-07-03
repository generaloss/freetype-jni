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
        final int raw = toRaw(value);
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
        return (super.toString() + "{float=" + toFloat(raw) + ", raw=" + raw + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTF26Dot6 f26dot6 = (FTF26Dot6) object;
        return (this.getRawValue() == f26dot6.getRawValue());
    }

    @Override
    public int hashCode() {
        return this.getRawValue();
    }


    private static final FloatConvertor CONVERTOR = FloatConvertor.get(6);

    public static float toFloat(int rawValue) {
        return CONVERTOR.toFloat(rawValue);
    }

    public static int toIntFloor(int rawValue) {
        return CONVERTOR.toIntFloor(rawValue);
    }

    public static int toIntRound(int rawValue) {
        return CONVERTOR.toIntRound(rawValue);
    }

    public static int toIntCeil(int rawValue) {
        return CONVERTOR.toIntCeil(rawValue);
    }

    public static int toRaw(float value) {
        return CONVERTOR.toRaw(value);
    }

}
