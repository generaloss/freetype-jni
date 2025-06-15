package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTMatrix extends FTStruct { // struct done.

    public FTMatrix(long pointer) {
        super(pointer);
    }

    public FTMatrix() {
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


    // FT_Fixed xx;
    private static native int getXX(long pointer);

    public float getXX() {
        final int raw = getXX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setXX(long pointer, int valueRaw);

    public FTMatrix setXX(float value) {
        final int raw = FTFixed.of(value);
        setXX(super.pointer, raw);
        return this;
    }

    public FTMatrix setXX(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setXX(value.getFloat());
    }

    // FT_Fixed xy;
    private static native int getXY(long pointer);

    public float getXY() {
        final int raw = getXY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setXY(long pointer, int valueRaw);

    public FTMatrix setXY(float value) {
        final int raw = FTFixed.of(value);
        setXY(super.pointer, raw);
        return this;
    }

    public FTMatrix setXY(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setXY(value.getFloat());
    }

    // FT_Fixed yx;
    private static native int getYX(long pointer);

    public float getYX() {
        final int raw = getYX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYX(long pointer, int valueRaw);

    public FTMatrix setYX(float value) {
        final int raw = FTFixed.of(value);
        setYX(super.pointer, raw);
        return this;
    }

    public FTMatrix setYX(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setYX(value.getFloat());
    }

    // FT_Fixed yy;
    private static native int getYY(long pointer);

    public float getYY() {
        final int raw = getYY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYY(long pointer, int valueRaw);

    public FTMatrix setYY(float value) {
        final int raw = FTFixed.of(value);
        setYY(super.pointer, raw);
        return this;
    }

    public FTMatrix setYY(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setYY(value.getFloat());
    }


    public FTMatrix setIdentity() {
        this.setXX(1F);
        this.setYY(1F);
        return this;
    }


    @Override
    public String toString() {
        return "FTMatrix {xx=" + this.getXX() + ", xy=" + this.getXY() + ", yx=" + this.getYX() + ", yy=" + this.getYY() + '}';
    }


    public void multiply(FTMatrix a, FTMatrix b) {
        FreeType.ftMatrixMultiply(a, b);
    }

    public void invert(FTMatrix matrix) {
        final FTError error = FreeType.ftMatrixInvert(matrix);
        error.checkError();
    }

}
