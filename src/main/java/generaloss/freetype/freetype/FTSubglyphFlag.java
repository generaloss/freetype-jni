package generaloss.freetype.freetype;

public enum FTSubglyphFlag {

    ARGS_ARE_WORDS     (1),
    ARGS_ARE_XY_VALUES (2),
    ROUND_XY_TO_GRID   (4),
    SCALE              (8),
    XY_SCALE           (64),
    FLAG_2X2           (128), // 2X2
    USE_MY_METRICS     (512);


    public final int value;

    FTSubglyphFlag(int value) {
        this.value = value;
    }

}
