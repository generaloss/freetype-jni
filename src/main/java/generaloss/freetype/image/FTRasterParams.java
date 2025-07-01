package generaloss.freetype.image;

import generaloss.freetype.BitMaskable;
import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.types.FTBBox;

public class FTRasterParams extends FTStruct {

    public FTRasterParams(long pointer) {
        super(pointer);
    }

    public FTRasterParams() {
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


    // const FT_Bitmap* target;
    private static native long getTarget(long pointer);

    public FTBitmap getTarget() {
        final long pointer = getTarget(super.pointer);
        return FTStructCache.getOrCreate(FTBitmap.class, pointer, FTBitmap::new);
    }

    private static native void setTarget(long pointer, long target);

    public void setTarget(FTBitmap target) {
        setTarget(super.pointer, FTStruct.getPointer(target));
    }

    // const void* source;
    private static native long getSource(long pointer);

    public long getSource() {
        return getSource(super.pointer);
    }

    private static native void setSource(long pointer, long source);

    public void setSource(long source) {
        setSource(super.pointer, source);
    }

    // int flags;
    private static native int getFlags(long pointer);

    public int getFlagsRaw() {
        return getFlags(super.pointer);
    }

    public RasterFlags getFlags() {
        final int raw = this.getFlagsRaw();
        return new RasterFlags(raw);
    }

    private static native void setFlags(long pointer, int flags);

    public void setFlags(int flags) {
        setFlags(super.pointer, flags);
    }

    public void setFlags(RasterFlags flags) {
        this.setFlags(flags.getBits());
    }

    public void setFlags(FTRasterFlag... flags) {
        this.setFlags(BitMaskable.makeMask(flags));
    }

    // FT_SpanFunc gray_spans;
    private static native void setGraySpans(long pointer, FTSpanFunc gray_spans);

    public void setGraySpans(FTSpanFunc graySpans) {
        setGraySpans(super.pointer, graySpans);
    }

    // FT_SpanFunc black_spans;
    private static native void setBlackSpans(long pointer, FTSpanFunc black_spans);

    public void setBlackSpans(FTSpanFunc blackSpans) {
        setBlackSpans(super.pointer, blackSpans);
    }

    // FT_Raster_BitTest_Func bit_test;
    private static native void setBitTest(long pointer, FTRasterBitTestFunc bit_test);

    public void setBitTest(FTRasterBitTestFunc bitTest) {
        setBitTest(super.pointer, bitTest);
    }

    // FT_Raster_BitSet_Func bit_set;
    private static native void setBitSet(long pointer, FTRasterBitSetFunc bit_set);

    public void setBitSet(FTRasterBitSetFunc bitSet) {
        setBitSet(super.pointer, bitSet);
    }

    // FT_BBox clip_box;
    private static native long getClipBox(long pointer);

    public FTBBox getClipBox() {
        final long pointer = getClipBox(super.pointer);
        return FTStructCache.getOrCreate(FTBBox.class, pointer, FTBBox::new);
    }

}
