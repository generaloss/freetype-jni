package generaloss.freetype;

import java.util.StringJoiner;

public abstract class BitMask<M extends BitMaskable> {

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


    public boolean has(int bits) {
        if(bits == 0)
            return this.isDefault();
        return (this.bits & bits) == bits;
    }

    public void set(int bits) {
        if(bits == 0) {
            this.clear();
        }else{
            this.bits |= bits;
        }
    }

    public void clear(int bits) {
        this.bits &= ~bits;
    }

    public void clear() {
        bits = 0;
    }


    public boolean has(M maskable) {
        return this.has(maskable.getBit());
    }

    @SuppressWarnings("unchecked")
    public <T extends BitMask<M>> T set(M... maskables) {
        for(M maskable: maskables)
            this.set(maskable.getBit());

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends BitMask<M>> T clear(M... maskables) {
        for(M maskable: maskables)
            this.clear(maskable.getBit());

        return (T) this;
    }


    protected String toString(Class<M> enumClass) {
        final StringJoiner flags = new StringJoiner(", ");
        for(M maskable: enumClass.getEnumConstants())
            flags.add(maskable.toString() + "=" + this.has(maskable));

        return this.getClass().getSimpleName() + "{" + flags + "}";
    }


    public static int getBits(BitMask<?> mask) {
        if(mask == null)
            return 0;
        return mask.bits;
    }

}