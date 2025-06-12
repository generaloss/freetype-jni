package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

import java.util.StringJoiner;

public class OpenFlags extends BitMask {

    public OpenFlags() { }

    public OpenFlags(int bits) {
        super(bits);
    }


    public boolean has(FTOpen flag) {
        return super.has(flag.value);
    }

    public OpenFlags set(FTOpen flag) {
        super.set(flag.value);
        return this;
    }

    public OpenFlags clear(FTOpen flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasMemory() {
        return this.has(FTOpen.MEMORY);
    }

    public boolean hasStream() {
        return this.has(FTOpen.STREAM);
    }

    public boolean hasPathname() {
        return this.has(FTOpen.PATHNAME);
    }

    public boolean hasDriver() {
        return this.has(FTOpen.DRIVER);
    }

    public boolean hasParams() {
        return this.has(FTOpen.PARAMS);
    }


    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ");
        for(FTOpen flag: FTOpen.values())
            joiner.add(flag.toString() + "=" + this.has(flag));
        return "FaceFlags{" + joiner + "}";
    }

}
