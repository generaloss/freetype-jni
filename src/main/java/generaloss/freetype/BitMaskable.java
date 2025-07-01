package generaloss.freetype;

@FunctionalInterface
public interface BitMaskable {

    int getBit();

    @SafeVarargs
    static <T extends BitMaskable> int makeMask(T... flags) {
        int bits = 0;
        for(T flag: flags)
            bits |= flag.getBit();
        return bits;
    }

    static int getBitIndex(int value) {
        return Integer.numberOfTrailingZeros(value);
    }

    static int getFromZeroBitIndex(int value) {
        final int zeros = Integer.numberOfTrailingZeros(value);
        final int isNotZero = (1 - zeros / Integer.SIZE);
        return (zeros % Integer.SIZE + isNotZero);
    }

}
