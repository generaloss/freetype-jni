package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class OpenFlags extends BitMask<FTOpen> {

    public OpenFlags() { }

    public OpenFlags(int bits) {
        super(bits);
    }


    public boolean hasMemory() {
        return super.has(FTOpen.MEMORY);
    }

    public boolean hasStream() {
        return super.has(FTOpen.STREAM);
    }

    public boolean hasPathname() {
        return super.has(FTOpen.PATHNAME);
    }

    public boolean hasDriver() {
        return super.has(FTOpen.DRIVER);
    }

    public boolean hasParams() {
        return super.has(FTOpen.PARAMS);
    }


    @Override
    public String toString() {
        return super.toString(FTOpen.class);
    }

    @Override
    public OpenFlags copy() {
        return new OpenFlags(super.getBits());
    }

}
