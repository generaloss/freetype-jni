package generaloss.freetype.stroke;

import generaloss.freetype.FTStruct;

public class FTStroker extends FTStruct {

    public FTStroker(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTStroker newInstance() {
        return new FTStroker(newStruct());
    }


    private static native void set(long pointer, int radius, int lineCap, int lineJoin, int miterLimit);

    /** he radius is expressed in the same units as the outline coordinates.
      * The miter_limit multiplied by the radius gives the maximum size of a miter spike, at which it is clipped for FTStrokerLinejoin.MITER_VARIABLE or replaced with a bevel join for FTStrokerLinejoin.MITER_FIXED.
      * This function calls FT_Stroker_Rewind automatically. */
    public void set(int radius, FTStrokerLinecap lineCap, FTStrokerLinejoin lineJoin, int miterLimit) {
        set(super.pointer, radius, lineCap.value, lineJoin.value, miterLimit);
    }


    private static native void done(long pointer);

    /** Destroy a stroker object. */
    public void done() {
        done(super.pointer);
        super.destroyPointer();
    }

}