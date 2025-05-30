package generaloss.freetype.image;

public enum FTOutlineFlag {

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


    public final int value;

    FTOutlineFlag(int value) {
        this.value = value;
    }

}
