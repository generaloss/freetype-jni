package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;

public class FTSize extends FTStruct { // struct done.

    public FTSize(long pointer) {
        super(pointer);
    }


    // FT_Face face;
    private static native long getFace(long pointer);

    public FTFace getFace() {
        final long pointer = getMetrics(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTFace::new);
    }

    // FT_Size_Metrics metrics;
    private static native long getMetrics(long pointer);

    public FTSizeMetrics getMetrics() {
        final long pointer = getMetrics(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTSizeMetrics::new);
    }

}