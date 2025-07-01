package generaloss.freetype.image;

import generaloss.freetype.BitMaskable;

public enum FTOutlineFlag implements BitMaskable {

    NONE            (0),
    OWNER           (1),
    EVEN_ODD_FILL   (2),
    REVERSE_FILL    (4),
    IGNORE_DROPOUTS (8),
    SMART_DROPOUTS  (16),
    INCLUDE_STUBS   (32),
    OVERLAP         (64),
    HIGH_PRECISION  (256),
    SINGLE_PASS     (512);

    private final int bit;

    FTOutlineFlag(int bit) {
        this.bit = bit;
    }

    @Override
    public int getBit() {
        return bit;
    }

}
