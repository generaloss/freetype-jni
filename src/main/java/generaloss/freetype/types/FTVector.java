package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

import java.util.Objects;

public class FTVector extends FTStruct { // struct done.

    public FTVector(long pointer) {
        super(pointer);
    }

    public FTVector() {
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


    // FT_Pos x;
    private static native int getRawX(long pointer);

    public int getRawX() {
        return getRawX(super.pointer);
    }

    public float getX(PosType posType) {
        final int raw = this.getRawX();
        return posType.toFloat(raw);
    }

    private static native void setRawX(long pointer, int value);

    public FTVector setRawX(int value) {
        setRawX(super.pointer, value);
        return this;
    }

    public FTVector setX(float value, PosType posType) {
        final int raw = posType.toRaw(value);
        return this.setRawX(raw);
    }

    public FTVector setX(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setX(value.getRawValue(), value.getType());
    }

    // FT_Pos y;
    private static native int getRawY(long pointer);

    public int getRawY() {
        return getRawY(super.pointer);
    }

    public float getY(PosType posType) {
        final int raw = this.getRawY();
        return posType.toFloat(raw);
    }

    private static native void setRawY(long pointer, int value);

    public FTVector setRawY(int value) {
        setRawY(super.pointer, value);
        return this;
    }

    public FTVector setY(float value, PosType posType) {
        final int raw = posType.toRaw(value);
        return this.setRawY(raw);
    }

    public FTVector setY(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setY(value.getRawValue(), value.getType());
    }


    public FTVector set(float x, float y, PosType posType) {
        this.setX(x, posType);
        this.setY(y, posType);
        return this;
    }

    public FTVector set(float xy, PosType posType) {
        this.setX(xy, posType);
        this.setY(xy, posType);
        return this;
    }

    public FTVector set(FTPos x, FTPos y) {
        if(FTStruct.equals(x, y))
            return this.set(x);

        this.setX(x.getRawValue(), x.getType());
        this.setY(y.getRawValue(), y.getType());
        return this;
    }

    public FTVector set(FTPos xy) {
        return this.set(xy.getRawValue(), xy.getType());
    }


    public void transform(FTMatrix matrix) {
        FreeType.ftVectorTransform(this, matrix);
    }


    @Override
    public String toString() {
        return (super.toString() + "{x=" + this.getRawX() + ", y=" + this.getRawY() + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTVector vector = (FTVector) object;
        return (this.getRawX() == vector.getRawX() && this.getRawY() == vector.getRawY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getRawX(), this.getRawY());
    }

}
