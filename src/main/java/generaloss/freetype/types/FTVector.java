package generaloss.freetype.types;

import generaloss.freetype.FTStruct;

public class FTVector extends FTStruct {

    public FTVector(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTVector newInstance() {
        return new FTVector(newStruct());
    }


    // FT_Pos x;
    private static native int getX(long pointer);

    /** The horizontal coordinate. */
    public float getX() {
        final int raw = getX(super.pointer);
        return FTPos.toFloat(raw);
    }


    // FT_Pos y;
    private static native int getY(long pointer);

    /** The vertical coordinate. */
    public float getY() {
        final int raw = getY(super.pointer);
        return FTPos.toFloat(raw);
    }

}
