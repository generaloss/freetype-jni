package generaloss.freetype.freetype;

public enum FTFSType {

    INSTALLABLE_EMBEDDING        (0),
    RESTRICTED_LICENSE_EMBEDDING (2),
    PREVIEW_AND_PRINT_EMBEDDING  (4),
    EDITABLE_EMBEDDING           (8),
    NO_SUBSETTING                (256),
    BITMAP_EMBEDDING_ONLY        (512);

    public final int value;

    FTFSType(int value) {
        this.value = value;
    }

}
