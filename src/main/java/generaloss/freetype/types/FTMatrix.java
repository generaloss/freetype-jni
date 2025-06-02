package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTMatrix extends FTStruct { // struct done.

    public FTMatrix(long pointer) {
        super(pointer);
    }


    // FT_Fixed xx;
    private static native long getXX(long pointer);

    public float getXX() {
        final long raw = getXX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed xy;
    private static native long getXY(long pointer);

    public float getXY() {
        final long raw = getXY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed yx;
    private static native long getYX(long pointer);

    public float getYX() {
        final long raw = getYX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed yy;
    private static native long getYY(long pointer);

    public float getYY() {
        final long raw = getYY(super.pointer);
        return FTFixed.toFloat(raw);
    }


    public void multiply(FTMatrix a, FTMatrix b) {
        FreeType.ftMatrixMultiply(a, b);
    }

    public void invert(FTMatrix matrix) {
        final FTError error = FreeType.ftMatrixInvert(matrix);
        error.checkError();
    }


    private static native long newStruct();

    public static FTMatrix newInstance() {
        return new FTMatrix(newStruct());
    }

}
