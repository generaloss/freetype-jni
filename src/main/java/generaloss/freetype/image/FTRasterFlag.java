package generaloss.freetype.image;

import generaloss.freetype.BitMaskable;

public enum FTRasterFlag implements BitMaskable {

    DEFAULT (0),
    AA      (1),
    DIRECT  (2),
    CLIP    (4),
    SDF     (8);

    private final int bit;

    FTRasterFlag(int bit) {
        this.bit = bit;
    }

    @Override
    public int getBit() {
        return bit;
    }


    public static FTRasterFlag byBit(int bit) {
        final int index = BitMaskable.getFromZeroBitIndex(bit);
        return FTRasterFlag.values()[index];
    }

}
