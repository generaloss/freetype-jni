package generaloss.freetype.face;

import generaloss.freetype.FTObject;

public class FTSize extends FTObject {

    public FTSize(long pointer) {
        super(pointer);
    }


    // TODO: face, generic


    private static native long getMetrics(long pointer);

    /** Metrics for this size object. */
    public FTSizeMetrics getMetrics() {
        final long metricsPointer = getMetrics(super.pointer);
        return new FTSizeMetrics(metricsPointer);
    }

}