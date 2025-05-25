package generaloss.freetype.types;

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


    private static final int BITS = 6;
    private static final int UNIT = (1 << BITS); // 64
    private static final int HALF_UNIT = (1 << (BITS - 1)); // 32
    private static final int MASK = (UNIT - 1); // 0x3F
    private static final float UNIT_FRAC = (1F / UNIT);

    public static float toFloat(int rawValue) {
        return (rawValue * UNIT_FRAC);
    }

    public static int toIntFloor(int rawValue) {
        return (rawValue >> BITS);
    }

    public static int toIntRound(int rawValue) {
        return ((rawValue + HALF_UNIT) >> BITS);
    }

    public static int toIntCeil(int rawValue) {
        return (((rawValue + MASK) & ~MASK) >> BITS);
    }


    public static int of(float value) {
        return (int) (value * UNIT);
    }

}
