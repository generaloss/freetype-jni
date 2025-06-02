package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTBBox extends FTStruct { // struct done.

    public FTBBox(long pointer) {
        super(pointer);
    }


    // FT_Pos xMin;
    private static native long getXMin(long pointer);

    public float getXMin() {
        final long raw = getXMin(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos yMin;
    private static native long getYMin(long pointer);

    public float getYMin() {
        final long raw = getYMin(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos xMax;
    private static native long getXMax(long pointer);

    public float getXMax() {
        final long raw = getXMax(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos yMax;
    private static native long getYMax(long pointer);

    public float getYMax() {
        final long raw = getYMax(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native long newStruct();

    public static FTBBox newInstance() {
        return new FTBBox(newStruct());
    }

}
