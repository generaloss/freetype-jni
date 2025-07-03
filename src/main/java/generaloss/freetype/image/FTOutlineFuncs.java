package generaloss.freetype.image;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;
import generaloss.freetype.types.PosType;

public class FTOutlineFuncs extends FTStruct {

    public FTOutlineFuncs(long pointer) {
        super(pointer);
    }

    public FTOutlineFuncs() {
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


    // FT_Outline_MoveToFunc move_to;
    private static native void setMoveTo(long pointer, FTOutlineMoveToFunc move_to);

    public void setMoveTo(FTOutlineMoveToFunc moveTo) {
        setMoveTo(this.pointer, moveTo);
    }

    // FT_Outline_LineToFunc line_to;
    private static native void setLineTo(long pointer, FTOutlineLineToFunc line_to);

    public void setLineTo(FTOutlineLineToFunc lineTo) {
        setLineTo(this.pointer, lineTo);
    }

    // FT_Outline_ConicToFunc conic_to;
    private static native void setConicTo(long pointer, FTOutlineConicToFunc conic_to);

    public void setConicTo(FTOutlineConicToFunc conicTo) {
        setConicTo(this.pointer, conicTo);
    }

    // FT_Outline_CubicToFunc cubic_to;
    private static native void setCubicTo(long pointer, FTOutlineCubicToFunc cubic_to);

    public void setCubicTo(FTOutlineCubicToFunc cubicTo) {
        setCubicTo(this.pointer, cubicTo);
    }

    // int shift;
    private static native int getShift(long pointer);

    public int getShift() {
        return getShift(super.pointer);
    }

    private static native void setShift(long pointer, int shift);

    public void setShift(int shift) {
        setShift(this.pointer, shift);
    }

    // FT_Pos delta;
    private static native int getDelta(long pointer);

    public float getDelta(PosType posType) {
        final int raw = getDelta(super.pointer);
        return posType.toFloat(raw);
    }

    private static native void setDelta(long pointer, int delta);

    public void setDelta(float delta, PosType posType) {
        final int raw = posType.toRaw(delta);
        setDelta(this.pointer, raw);
    }

}