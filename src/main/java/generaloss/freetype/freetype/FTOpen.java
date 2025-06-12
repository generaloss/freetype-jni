package generaloss.freetype.freetype;

public enum FTOpen {

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

    public final int value;

    FTOpen() {
        this.value = (1 << super.ordinal());
    }

}
