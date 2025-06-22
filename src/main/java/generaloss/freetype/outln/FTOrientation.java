package generaloss.freetype.outln;

public enum FTOrientation {

    TRUETYPE   (0),
    POSTSCRIPT (1),
    FILL_RIGHT (TRUETYPE.value),
    FILL_LEFT  (POSTSCRIPT.value),
    NONE       (2);

    public final int value;

    FTOrientation(int value) {
        this.value = value;
    }

    public static FTOrientation byValue(int value) {
        switch(value) {
            case 0: return TRUETYPE;
            case 1: return POSTSCRIPT;
            default: return NONE;
        }
    }

}
