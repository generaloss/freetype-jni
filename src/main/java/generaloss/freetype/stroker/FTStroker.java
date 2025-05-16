package generaloss.freetype.stroker;

import generaloss.freetype.FTObject;

public class FTStroker extends FTObject {

    public FTStroker(long pointer) {
        super(pointer);
    }


    private static native void set(long pointer, int radius, int lineCap, int lineJoin, int miterLimit);

    /** he radius is expressed in the same units as the outline coordinates.
      * The miter_limit multiplied by the radius gives the maximum size of a miter spike, at which it is clipped for FTStrokerLinejoin.MITER_VARIABLE or replaced with a bevel join for FTStrokerLinejoin.MITER_FIXED.
      * This function calls FT_Stroker_Rewind automatically. */
    public void set(int radius, FTStrokerLinecap lineCap, FTStrokerLinejoin lineJoin, int miterLimit) {
        set(super.pointer, radius, lineCap.value, lineJoin.value, miterLimit);
    }


    // TODO: rewind, parseOutline, beginSubPath, endSubPath, lineTo, conicTo, cubicTo, getBorderCounts, exportBorder, getCounts, export


    private static native void done(long pointer);

    /** Destroy a stroker object. */
    @Override
    public void done() {
        done(super.pointer);
        super.done();
    }

}