package generaloss.freetype.freetype;

import generaloss.freetype.BitMaskable;

public enum FTOpen implements BitMaskable {

    /** This is a memory-based stream. */
    MEMORY   (1),
    /** Copy the stream from the stream field. */
    STREAM   (2),
    /** Create a new input stream from a C path name. */
    PATHNAME (4),
    /** Use the driver field. */
    DRIVER   (8),
    /** Use the num_params and params fields. */
    PARAMS   (16);

    private final int bit;

    FTOpen(int bit) {
        this.bit = bit;
    }

    @Override
    public int getBit() {
        return bit;
    }


    public static FTOpen byBit(int bit) {
        final int index = BitMaskable.getBitIndex(bit);
        return FTOpen.values()[index];
    }

}
