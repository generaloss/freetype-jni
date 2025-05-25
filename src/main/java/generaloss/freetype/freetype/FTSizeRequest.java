package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;

public class FTSizeRequest extends FTStruct {

    public FTSizeRequest(long pointer) {
        super(pointer);
    }


    // FT_Size_Request_Type type;
    private static native int getType(long pointer);

    /** See FT_Size_Request_Type. */
    public FTSizeRequestType getType() {
        final int raw = getType(super.pointer);
        return FTSizeRequestType.byValue(raw);
    }


    // FT_Long width;
    private static native long getWidth(long pointer);

    /** The desired width, given as a 26.6 fractional point value (with 72pt = 1in). */
    public long getWidth() {
        return getWidth(super.pointer);
    }


    // FT_Long height;
    private static native long getHeight(long pointer);

    /** The desired height, given as a 26.6 fractional point value (with 72pt = 1in). */
    public long getHeight() {
        return getHeight(super.pointer);
    }


    // FT_UInt horiResolution;
    private static native long getHoriResolution(long pointer);

    /** The horizontal resolution (dpi, i.e., pixels per inch).
     * If set to zero, width is treated as a 26.6 fractional pixel value, which gets internally rounded to an integer.
     * */
    public long getHoriResolution() {
        return getHoriResolution(super.pointer);
    }


    // FT_UInt vertResolution;
    private static native long getVertResolution(long pointer);

    /** The vertical resolution (dpi, i.e., pixels per inch).
     * If set to zero, height is treated as a 26.6 fractional pixel value, which gets internally rounded to an integer.
     * */
    public long getVertResolution() {
        return getVertResolution(super.pointer);
    }

}
