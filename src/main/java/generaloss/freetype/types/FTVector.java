package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTVector extends FTStruct { // struct done.

    public FTVector(long pointer) {
        super(pointer);
    }


    // FT_Pos x;
    private static native long getX(long pointer);

    public float getX() {
        final long raw = getX(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setX(long pointer, long value);

    public void setX(float value) {
        final long raw = FTPos.of(value);
        setX(super.pointer, raw);
    }

    // FT_Pos y;
    private static native long getY(long pointer);

    public float getY() {
        final long raw = getY(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setY(long pointer, long value);

    public void setY(float value) {
        final long raw = FTPos.of(value);
        setY(super.pointer, raw);
    }


    public void transform(FTMatrix matrix) {
        FreeType.ftVectorTransform(this, matrix);
    }


    private static native long newStruct();

    public static FTVector newInstance() {
        return new FTVector(newStruct());
    }

}
