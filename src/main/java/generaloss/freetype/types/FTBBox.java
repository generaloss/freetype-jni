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
    private static native int getXMinRaw(long pointer);

    public int getXMinRaw() {
        return getXMinRaw(super.pointer);
    }

    public float getXMin(PosType posType) {
        final int raw = this.getXMinRaw();
        return posType.toFloat(raw);
    }

    // FT_Pos yMin;
    private static native int getYMinRaw(long pointer);

    public int getYMinRaw() {
        return getYMinRaw(super.pointer);
    }

    public float getYMin(PosType posType) {
        final int raw = this.getYMinRaw();
        return posType.toFloat(raw);
    }

    // FT_Pos xMax;
    private static native int getXMaxRaw(long pointer);

    public int getXMaxRaw() {
        return getXMaxRaw(super.pointer);
    }

    public float getXMax(PosType posType) {
        final int raw = this.getXMaxRaw();
        return posType.toFloat(raw);
    }

    // FT_Pos yMax;
    private static native int getYMaxRaw(long pointer);

    public int getYMaxRaw() {
        return getYMaxRaw(super.pointer);
    }

    public float getYMax(PosType posType) {
        final int raw = this.getYMaxRaw();
        return posType.toFloat(raw);
    }


    @Override
    public String toString() {
        return (super.toString() + "{xMin=" + this.getXMinRaw() + ", yMin=" + this.getYMinRaw() + ", xMax=" + this.getXMaxRaw() + ", yMax=" + this.getYMaxRaw() + "}");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTBBox bbox = (FTBBox) object;
        return (
            this.getXMinRaw() == bbox.getXMinRaw() && this.getYMinRaw() == bbox.getYMinRaw() &&
            this.getXMaxRaw() == bbox.getXMaxRaw() && this.getYMaxRaw() == bbox.getYMaxRaw()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getXMinRaw(), this.getYMinRaw(), this.getXMaxRaw(), this.getYMaxRaw());
    }

}
