package generaloss.freetype;

public class BitMask {

    private int bits;

    public BitMask() { }

    public BitMask(int bits) {
        this.bits = bits;
    }


    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }


    public boolean isDefault() {
        return (bits == 0);
    }


    public boolean has(int bit) {
        if(bit == 0)
            return this.isDefault();
        return (bits & bit) != 0;
    }

    public void set(int bit) {
        if(bit == 0) {
            bits = 0;
        }else{
            bits |= bit;
        }
    }

    public void clear(int bit) {
        bits &= ~bit;
    }


    public static int getBits(BitMask mask) {
        if(mask == null)
            return 0;
        return mask.bits;
    }

}