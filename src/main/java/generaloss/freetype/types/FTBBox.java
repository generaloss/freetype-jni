package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

import java.util.Objects;

public class FTBBox extends FTStruct { // struct done.

    public FTBBox(long pointer) {
        super(pointer);
    }

    public FTBBox() {
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


    // FT_Pos xMin;
    private static native int getXMin(long pointer);

    public float getXMin() {
        final int raw = getXMin(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos yMin;
    private static native int getYMin(long pointer);

    public float getYMin() {
        final int raw = getYMin(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos xMax;
    private static native int getXMax(long pointer);

    public float getXMax() {
        final int raw = getXMax(super.pointer);
        return FTPos.toFloat(raw);
    }

    // FT_Pos yMax;
    private static native int getYMax(long pointer);

    public float getYMax() {
        final int raw = getYMax(super.pointer);
        return FTPos.toFloat(raw);
    }


    @Override
    public String toString() {
        return (super.toString() + "{xMin=" + this.getXMin() + ", yMin=" + this.getYMin() + ", xMax=" + this.getXMax() + ", yMax=" + this.getYMax() + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTBBox bbox = (FTBBox) object;
        return (
            this.getXMin() == bbox.getXMin() && this.getYMin() == bbox.getYMin() &&
            this.getXMax() == bbox.getXMax() && this.getYMax() == bbox.getYMax()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getXMin(), this.getYMin(), this.getXMax(), this.getYMax());
    }

}
