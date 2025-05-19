package generaloss.freetype;

/** 16.16 fixed-point value utility */
public class FTFixed {

    private final int raw;

    public FTFixed(int rawValue) {
        this.raw = rawValue;
    }

    public int getRaw() {
        return raw;
    }


    public float getFloat() {
        return toFloat(raw);
    }

    public int getFloor() {
        return toIntFloor(raw);
    }

    public int getRound() {
        return toIntRound(raw);
    }

    public int getCeil() {
        return toIntCeil(raw);
    }

    @Override
    public String toString() {
        return "FTFixed{float=" + this.getFloat() + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    private static final int MASK_16 = 0xFFFF;
    private static final float FRAC_65536 = (1F / 65536F);
    private static final int HALF_UNIT = (1 << 15); // 0x8000

    public static float toFloat(int rawValue) {
        return (rawValue * FRAC_65536);
    }

    public static int toIntFloor(int rawValue) {
        return (rawValue >> 16);
    }

    public static int toIntRound(int rawValue) {
        return ((rawValue + HALF_UNIT) >> 16);
    }

    public static int toIntCeil(int rawValue) {
        return (((rawValue + MASK_16) & ~MASK_16) >> 16);
    }

}