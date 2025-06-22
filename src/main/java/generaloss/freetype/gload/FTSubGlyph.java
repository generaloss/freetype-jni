package generaloss.freetype.gload;

import generaloss.freetype.BitMaskable;
import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.freetype.FTSubglyphFlag;
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

    private static native void setIndex(long pointer, int index);

    public void setIndex(int index) {
        setIndex(super.pointer, index);
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

    private static native void setFlags(long pointer, int flags);

    public void setFlags(int flags) {
        setFlags(super.pointer, flags);
    }

    public void setFlags(SubglyphFlags flags) {
        this.setFlags(flags.getBits());
    }

    public void setFlags(FTSubglyphFlag... flags) {
        this.setFlags(BitMaskable.makeMask(flags));
    }

    // FT_Int arg1;
    private static native int getArg1(long pointer);

    public int getArg1() {
        return getArg1(super.pointer);
    }

    private static native void setArg1(long pointer, int arg1);

    public void setArg1(int arg1) {
        setArg1(super.pointer, arg1);
    }

    // FT_Int arg2;
    private static native int getArg2(long pointer);

    public int getArg2() {
        return getArg2(super.pointer);
    }

    private static native void setArg2(long pointer, int arg2);

    public void setArg2(int arg2) {
        setArg2(super.pointer, arg2);
    }

    // FT_Matrix transform;
    private static native long getTransform(long pointer);

    public FTMatrix getTransform() {
        final long pointer = getTransform(super.pointer);
        return FTStructCache.getOrCreate(FTMatrix.class, pointer, FTMatrix::new); 
    }

}
