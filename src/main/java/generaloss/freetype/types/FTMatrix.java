package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTMatrix extends FTStruct {

    public FTMatrix(long pointer) {
        super(pointer);
    }


    // FT_Fixed xx;
    private static native int getXX(long pointer);

    /** Matrix coefficient. */
    public float getXX() {
        final int raw = getXX(super.pointer);
        return FTFixed.toFloat(raw);
    }


    // FT_Fixed xy;
    private static native int getXY(long pointer);

    /** Matrix coefficient. */
    public float getXY() {
        final int raw = getXY(super.pointer);
        return FTFixed.toFloat(raw);
    }


    // FT_Fixed yx;
    private static native int getYX(long pointer);

    /** Matrix coefficient. */
    public float getYX() {
        final int raw = getYX(super.pointer);
        return FTFixed.toFloat(raw);
    }


    // FT_Fixed yy;
    private static native int getYY(long pointer);

    /** Matrix coefficient. */
    public float getYY() {
        final int raw = getYY(super.pointer);
        return FTFixed.toFloat(raw);
    }


}
