package generaloss.freetype;

public enum UnicodeVariationSelector {

    VS1        (0xFE00), // 65024
    VS2        (0xFE01), // 65025
    VS3        (0xFE02), // 65026
    VS4        (0xFE03), // 65027
    VS5        (0xFE04), // 65028
    VS6        (0xFE05), // 65029
    VS7        (0xFE06), // 65030
    VS8        (0xFE07), // 65031
    VS9        (0xFE08), // 65032
    VS10       (0xFE09), // 65033
    VS11       (0xFE0A), // 65034
    VS12       (0xFE0B), // 65035
    VS13       (0xFE0C), // 65036
    VS14       (0xFE0D), // 65037
    VS15_TEXT  (0xFE0E), // 65038
    VS16_EMOJI (0xFE0F); // 65039

    public final int code;

    UnicodeVariationSelector(int code) {
        this.code = code;
    }

    public static UnicodeVariationSelector byCode(int value) {
        final int index = (value - VS1.code);
        if(index < 0 || index >= values().length)
            return null;
        return values()[index];
    }

}
