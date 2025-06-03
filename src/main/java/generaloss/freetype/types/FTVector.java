package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTVector extends FTStruct { // struct done.

    public FTVector(long pointer) {
        super(pointer);
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


    public void transform(FTMatrix matrix) {
        FreeType.ftVectorTransform(this, matrix);
    }


    private static native long newStruct();

    public static FTVector newInstance() {
        return new FTVector(newStruct());
    }

}
