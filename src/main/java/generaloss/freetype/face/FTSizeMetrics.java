package generaloss.freetype.face;

import generaloss.freetype.FTLibrary;

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


    private static native int getXscale(long metrics);

    /** A 16.16 fractional scaling value to convert horizontal metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public int getXScale() {
        return getXscale(address);
    }


    private static native int getYscale(long metrics);

    /** A 16.16 fractional scaling value to convert vertical metrics from font units to 26.6 fractional pixels. Only relevant for scalable font formats. */
    public int getYscale() {
        return getYscale(address);
    }


    private static native int getAscender(long metrics);

    /** The ascender in 26.6 fractional pixels, rounded up to an integer value. See FT_FaceRec for the details. */
    public int getAscender() {
        final int raw = getAscender(address);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getDescender(long metrics);

    /** The descender in 26.6 fractional pixels, rounded down to an integer value. See FT_FaceRec for the details. */
    public int getDescender() {
        final int raw = getDescender(address);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getHeight(long metrics);

    /** The height in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public int getHeight() {
        final int raw = getHeight(address);
        return FTLibrary.FTPos_toInt(raw);
    }


    private static native int getMaxAdvance(long metrics);

    /** The maximum advance width in 26.6 fractional pixels, rounded to an integer value. See FT_FaceRec for the details. */
    public int getMaxAdvance() {
        final int raw = getMaxAdvance(address);
        return FTLibrary.FTPos_toInt(raw);
    }

}