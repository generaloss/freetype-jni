package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructRegistry;

public class FTSize extends FTStruct {

    public FTSize(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTSize newInstance() {
        return new FTSize(newStruct());
    }


    // TODO: face, generic


    private static native long getMetrics(long pointer);

    /** Metrics for this size object. */
    public FTSizeMetrics getMetrics() {
        final long pointer = getMetrics(super.pointer);
        return FTStructRegistry.getOrCreate(pointer, FTSizeMetrics::new);
    }

}