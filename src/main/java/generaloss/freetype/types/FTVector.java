package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTVector extends FTStruct {

    public FTVector(long pointer) {
        super(pointer);
    }


    // FT_Pos x;
    private static native int getX(long pointer);

    public float getX() {
        final int raw = getX(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setX(long pointer, float value);

    public void setX(float value) {
        final int raw = FTPos.of(value);
        setX(super.pointer, raw);
    }

    // FT_Pos y;
    private static native int getY(long pointer);

    public float getY() {
        final int raw = getY(super.pointer);
        return FTPos.toFloat(raw);
    }

    private static native void setY(long pointer, float value);

    public void setY(float value) {
        final int raw = FTPos.of(value);
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
