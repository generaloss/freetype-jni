package generaloss.freetype.freetype;

import generaloss.freetype.BitMaskable;

public enum FTOpen implements BitMaskable {

    /** This is a memory-based stream. */
    MEMORY,
    /** Copy the stream from the stream field. */
    STREAM,
    /** Create a new input stream from a C path name. */
    PATHNAME,
    /** Use the driver field. */
    DRIVER,
    /** Use the num_params and params fields. */
    PARAMS;

    private final int bit;

    FTOpen() {
        this.bit = (1 << super.ordinal());
    }

    @Override
    public int getBit() {
        return bit;
    }

}
