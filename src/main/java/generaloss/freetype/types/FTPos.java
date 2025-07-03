package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTPos extends FTStruct { // struct done.

    private final PosType type;

    public FTPos(long pointer, PosType type) {
        super(pointer);
        this.type = type;
    }

    public FTPos(PosType floatingPointType) {
        this(createPointer(), floatingPointType);
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


    public PosType getType() {
        return type;
    }

    private static native int getRawValue(long pointer);

    public int getRawValue() {
        return getRawValue(super.pointer);
    }

    public float getFloat() {
        final int raw = this.getRawValue();
        return type.toFloat(raw);
    }

    public int getIntFloor() {
        final int raw = this.getRawValue();
        return type.toIntFloor(raw);
    }

    public int getIntRound() {
        final int raw = this.getRawValue();
        return type.toIntRound(raw);
    }

    public int getIntCeil() {
        final int raw = this.getRawValue();
        return type.toIntCeil(raw);
    }


    private static native void setRawValue(long pointer, int value);

    public FTPos setRawValue(int value) {
        setRawValue(super.pointer, value);
        return this;
    }

    public FTPos set(float value) {
        final int raw = type.toRaw(value);
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
        return (super.toString() + "{float=" + type.toFloat(raw) + ", raw=" + raw + "}");
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

}
