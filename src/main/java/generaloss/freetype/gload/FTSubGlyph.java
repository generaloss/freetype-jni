package generaloss.freetype.gload;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.freetype.SubglyphFlags;
import generaloss.freetype.types.FTMatrix;

public class FTSubGlyph extends FTStruct { // struct done.

    public FTSubGlyph(long pointer) {
        super(pointer);
    }


    // FT_Int index;
    private static native int getIndex(long pointer);

    public int getIndex() {
        return getIndex(super.pointer);
    }

    // FT_UShort flags;
    private static native int getFlags(long pointer);

    public int getFlagsRaw() {
        return getFlags(super.pointer);
    }

    public SubglyphFlags getFlags() {
        final int raw = this.getFlagsRaw();
        return new SubglyphFlags(raw);
    }

    // FT_Int arg1;
    private static native int getArg1(long pointer);

    public int getArg1() {
        return getArg1(super.pointer);
    }

    // FT_Int arg2;
    private static native int getArg2(long pointer);

    public int getArg2() {
        return getArg2(super.pointer);
    }

    // FT_Matrix transform;
    private static native long getTransform(long pointer);

    public FTMatrix getTransform() {
        final long pointer = getTransform(super.pointer);
        return FTStructCache.getOrCreate(FTMatrix.class, pointer, FTMatrix::new); 
    }

}
