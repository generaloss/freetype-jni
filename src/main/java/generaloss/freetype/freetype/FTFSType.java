package generaloss.freetype.freetype;

import generaloss.freetype.BitMaskable;

public enum FTFSType implements BitMaskable {

    INSTALLABLE_EMBEDDING        (0),
    RESTRICTED_LICENSE_EMBEDDING (2),
    PREVIEW_AND_PRINT_EMBEDDING  (4),
    EDITABLE_EMBEDDING           (8),
    NO_SUBSETTING                (256),
    BITMAP_EMBEDDING_ONLY        (512);

    public final int bit;

    FTFSType(int bit) {
        this.bit = bit;
    }

    @Override
    public int getBit() {
        return bit;
    }

}
