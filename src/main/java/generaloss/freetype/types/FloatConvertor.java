package generaloss.freetype.types;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FloatConvertor {

    private final int bits;
    private final int unit;
    private final int halfUnit;
    private final int mask;
    private final float unitFrac;

    private FloatConvertor(int bits) {
        this.bits = bits;
        this.unit = (1 << bits);
        this.halfUnit = (bits == 0) ? 0 : (1 << (bits - 1));
        this.mask = (unit - 1);
        this.unitFrac = (unit == 0) ? 1F : (1F / unit);
    }


    public float toFloat(int rawValue) {
        return (rawValue * unitFrac);
    }

    public int toIntFloor(int rawValue) {
        return (rawValue >> bits);
    }

    public int toIntRound(int rawValue) {
        return ((rawValue + halfUnit) >> bits);
    }

    public int toIntCeil(int rawValue) {
        return (((rawValue + mask) & ~mask) >> bits);
    }

    public int toRaw(float value) {
        return (int) (value * unit);
    }


    private static final Map<Integer, FloatConvertor> CONVERTOTS_BY_BITS = new ConcurrentHashMap<>();

    public static FloatConvertor get(int bits) {
        if(CONVERTOTS_BY_BITS.containsKey(bits))
            return CONVERTOTS_BY_BITS.get(bits);

        final FloatConvertor convertor = new FloatConvertor(bits);
        CONVERTOTS_BY_BITS.put(bits, convertor);
        return convertor;
    }

}