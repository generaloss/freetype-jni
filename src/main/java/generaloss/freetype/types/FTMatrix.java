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

    public void setXX(float value) {
        final int raw = FTFixed.of(value);
        setXX(super.pointer, raw);
    }

    // FT_Fixed xy;
    private static native int getXY(long pointer);

    public float getXY() {
        final int raw = getXY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setXY(long pointer, int valueRaw);

    public void setXY(float value) {
        final int raw = FTFixed.of(value);
        setXY(super.pointer, raw);
    }

    // FT_Fixed yx;
    private static native int getYX(long pointer);

    public float getYX() {
        final int raw = getYX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYX(long pointer, int valueRaw);

    public void setYX(float value) {
        final int raw = FTFixed.of(value);
        setYX(super.pointer, raw);
    }

    // FT_Fixed yy;
    private static native int getYY(long pointer);

    public float getYY() {
        final int raw = getYY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYY(long pointer, int valueRaw);

    public void setYY(float value) {
        final int raw = FTFixed.of(value);
        setYY(super.pointer, raw);
    }


    public void multiply(FTMatrix a, FTMatrix b) {
        FreeType.ftMatrixMultiply(a, b);
    }

    public void invert(FTMatrix matrix) {
        final FTError error = FreeType.ftMatrixInvert(matrix);
        error.checkError();
    }

}
