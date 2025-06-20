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

    public float getX() {
        final int raw = getRawX(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setRawX(long pointer, int value);

    public FTVector setX(float value) {
        final int raw = FTPos.of(value);
        setRawX(super.pointer, raw);
        return this;
    }

    public FTVector setX(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setX(value.getFloat());
    }

    // FT_Pos y;
    private static native int getRawY(long pointer);

    public float getY() {
        final int raw = getRawY(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setRawY(long pointer, int value);

    public FTVector setY(float value) {
        final int raw = FTPos.of(value);
        setRawY(super.pointer, raw);
        return this;
    }

    public FTVector setY(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setY(value.getFloat());
    }


    public FTVector set(float x, float y) {
        this.setX(x);
        this.setY(y);
        return this;
    }

    public FTVector set(float xy) {
        this.setX(xy);
        this.setY(xy);
        return this;
    }

    public FTVector set(FTPos x, FTPos y) {
        if(FTStruct.equals(x, y))
            return this.set(x);

        return this.set(x.getFloat(), y.getFloat());
    }

    public FTVector set(FTPos xy) {
        return this.set(xy.getFloat());
    }


    public void transform(FTMatrix matrix) {
        FreeType.ftVectorTransform(this, matrix);
    }


    @Override
    public String toString() {
        return (super.toString() + "{x=" + this.getX() + ", y=" + this.getY() + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTVector vector = (FTVector) object;
        return (this.getX() == vector.getX() && this.getY() == vector.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

}
