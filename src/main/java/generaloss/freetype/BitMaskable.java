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

}
