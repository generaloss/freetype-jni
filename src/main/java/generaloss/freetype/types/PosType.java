package generaloss.freetype.types;

public enum PosType {

    INT      (FloatConvertor.get(0)),  // 32.0
    F26DOT6  (FloatConvertor.get(6)),  // 26.6
    F16DOT16 (FloatConvertor.get(16)); // 16.16

    private final FloatConvertor convertor;

    PosType(FloatConvertor convertor) {
        this.convertor = convertor;
    }

    public FloatConvertor getConvertor() {
        return convertor;
    }


    public float toFloat(int rawValue) {
        return convertor.toFloat(rawValue);
    }

    public int toIntFloor(int rawValue) {
        return convertor.toIntFloor(rawValue);
    }

    public int toIntRound(int rawValue) {
        return convertor.toIntRound(rawValue);
    }

    public int toIntCeil(int rawValue) {
        return convertor.toIntCeil(rawValue);
    }

    public int toRaw(float value) {
        return convertor.toRaw(value);
    }

}
