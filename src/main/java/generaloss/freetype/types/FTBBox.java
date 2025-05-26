package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTBBox extends FTStruct {

    public FTBBox(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTBBox newInstance() {
        return new FTBBox(newStruct());
    }


    // FT_Pos xMin;
    private static native int getXMin(long pointer);

    /** The horizontal minimum (left-most). */
    public float getXMin() {
        final int raw = getXMin(super.pointer);
        return FTPos.toFloat(raw);
    }


    // FT_Pos yMin;
    private static native int getYMin(long pointer);

    /** The vertical minimum (bottom-most). */
    public float getYMin() {
        final int raw = getYMin(super.pointer);
        return FTPos.toFloat(raw);
    }


    // FT_Pos xMax;
    private static native int getXMax(long pointer);

    /** The horizontal maximum (right-most). */
    public float getXMax() {
        final int raw = getXMax(super.pointer);
        return FTPos.toFloat(raw);
    }


    // FT_Pos yMax;
    private static native int getYMax(long pointer);

    /** The vertical maximum (top-most). */
    public float getYMax() {
        final int raw = getYMax(super.pointer);
        return FTPos.toFloat(raw);
    }


}
