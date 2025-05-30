package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTFixed;
import generaloss.freetype.types.FTPos;

public class FTSizeMetrics extends FTStruct {

    private static native long newStruct();

    public static FTSizeMetrics newInstance() {
        return new FTSizeMetrics(newStruct());
    }


    public FTSizeMetrics(long pointer) {
        super(pointer);
    }


    private static native int getXppem(long metrics);

    /** The width of the scaled EM square in pixels, hence the term ‘ppem’ (pixels per EM). It is also referred to as ‘nominal width’. */
    public int getXppem() {
        return getXppem(super.pointer);
    }


    private static native int getYppem(long metrics);

    /** The height of the scaled EM square in pixels, hence the term ‘ppem’ (pixels per EM). It is also referred to as ‘nominal height’. */
    public int getYppem() {
        return getYppem(super.pointer);
    }


    private static native int getXScale(long metrics);

    /** A 16.16 fractional scaling value to convert horizontal metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public float getXScale() {
        final int raw = getXScale(super.pointer);
        return FTFixed.toFloat(raw);
    }


    private static native int getYScale(long metrics);

    /** A 16.16 fractional scaling value to convert vertical metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public float getYScale() {
        final int raw = getYScale(super.pointer);
        return FTFixed.toFloat(raw);
    }


    private static native int getAscender(long metrics);

    /** The ascender in 26.6 fractional pixels, rounded up to an integer value. See FT_FaceRec for the details. */
    public float getAscender() {
        final int raw = getAscender(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native int getDescender(long metrics);

    /** The descender in 26.6 fractional pixels, rounded down to an integer value. See FT_FaceRec for the details. */
    public float getDescender() {
        final int raw = getDescender(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native int getHeight(long metrics);

    /** The height in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public float getHeight() {
        final int raw = getHeight(super.pointer);
        return FTPos.toFloat(raw);
    }


    private static native int getMaxAdvance(long metrics);

    /** The maximum advance width in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public float getMaxAdvance() {
        final int raw = getMaxAdvance(super.pointer);
        return FTPos.toFloat(raw);
    }

}