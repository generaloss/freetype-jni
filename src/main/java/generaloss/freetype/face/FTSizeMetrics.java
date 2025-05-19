package generaloss.freetype.face;

import generaloss.freetype.FTFixed;
import generaloss.freetype.FTPos;

public class FTSizeMetrics {

    private final long address;

    public FTSizeMetrics(long address) {
        this.address = address;
    }

    public long getAddress() {
        return address;
    }


    private static native int getXppem(long metrics);

    /** The width of the scaled EM square in pixels, hence the term ‘ppem’ (pixels per EM). It is also referred to as ‘nominal width’. */
    public int getXppem() {
        return getXppem(address);
    }


    private static native int getYppem(long metrics);

    /** The height of the scaled EM square in pixels, hence the term ‘ppem’ (pixels per EM). It is also referred to as ‘nominal height’. */
    public int getYppem() {
        return getYppem(address);
    }


    private static native int getXScale(long metrics);

    /** A 16.16 fractional scaling value to convert horizontal metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public FTFixed getXScale() {
        final int raw = getXScale(address);
        return new FTFixed(raw);
    }


    private static native int getYScale(long metrics);

    /** A 16.16 fractional scaling value to convert vertical metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public FTFixed getYScale() {
        final int raw = getYScale(address);
        return new FTFixed(raw);
    }


    private static native int getAscender(long metrics);

    /** The ascender in 26.6 fractional pixels, rounded up to an integer value. See FT_FaceRec for the details. */
    public FTPos getAscender() {
        final int raw = getAscender(address);
        return new FTPos(raw);
    }


    private static native int getDescender(long metrics);

    /** The descender in 26.6 fractional pixels, rounded down to an integer value. See FT_FaceRec for the details. */
    public FTPos getDescender() {
        final int raw = getDescender(address);
        return new FTPos(raw);
    }


    private static native int getHeight(long metrics);

    /** The height in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public FTPos getHeight() {
        final int raw = getHeight(address);
        return new FTPos(raw);
    }


    private static native int getMaxAdvance(long metrics);

    /** The maximum advance width in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public FTPos getMaxAdvance() {
        final int raw = getMaxAdvance(address);
        return new FTPos(raw);
    }

}