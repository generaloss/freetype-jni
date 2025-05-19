package generaloss.freetype;

/** 26.6 fixed-point value utility */
public class FTPos {

    private final int raw;

    public FTPos(int rawValue) {
        this.raw = rawValue;
    }

    public int getRaw() {
        return raw;
    }


    public float getFloat() {
        return toFloat(raw);
    }

    public int getIntFloor() {
        return toIntFloor(raw);
    }

    public int getIntRound() {
        return toIntRound(raw);
    }

    public int getIntCeil() {
        return toIntCeil(raw);
    }

    @Override
    public String toString() {
        return "FTPos{float=" + this.getFloat() + ", raw=0x" + Integer.toHexString(raw) + "}";
    }


    private static final int MASK_6_BIT = 0x3F;
    private static final float FRAC_64 = (1F / 64F);
    private static final int HALF_UNIT = (1 << 5); // 0x20

    public static float toFloat(int rawValue) {
        return (rawValue * FRAC_64);
    }

    public static int toIntFloor(int rawValue) {
        return (rawValue >> 6);
    }

    public static int toIntRound(int rawValue) {
        return ((rawValue + HALF_UNIT) >> 6);
    }

    public static int toIntCeil(int rawValue) {
        return (((rawValue + MASK_6_BIT) & ~MASK_6_BIT) >> 6);
    }

}
