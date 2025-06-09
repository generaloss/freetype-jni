package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

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

    public void setX(float value) {
        final int raw = FTPos.of(value);
        setRawX(super.pointer, raw);
    }

    public void setX(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        this.setX(value.getFloat());
    }

    // FT_Pos y;
    private static native int getRawY(long pointer);

    public float getY() {
        final int raw = getRawY(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setRawY(long pointer, int value);

    public void setY(float value) {
        final int raw = FTPos.of(value);
        setRawY(super.pointer, raw);
    }

    public void setY(FTPos value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        this.setY(value.getFloat());
    }


    public void set(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public void set(float xy) {
        this.setX(xy);
        this.setY(xy);
    }

    public void set(FTPos x, FTPos y) {
        if(FTStruct.equals(x, y)) {
            this.set(x);
            return;
        }
        this.setX(x.getFloat());
        this.setY(y.getFloat());
    }

    public void set(FTPos xy) {
        this.set(xy.getFloat());
    }


    public void transform(FTMatrix matrix) {
        FreeType.ftVectorTransform(this, matrix);
    }


    @Override
    public String toString() {
        return "FTVector{x=" + this.getX() + ", y=" + this.getY() + "}";
    }

}
