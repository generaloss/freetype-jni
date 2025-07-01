package generaloss.freetype.image;

import generaloss.freetype.FTStruct;

public class FTSpan extends FTStruct {

    public FTSpan(long pointer) {
        super(pointer);
    }


    // short x;
    private static native short getX(long pointer);

    public short getX() {
        return getX(super.pointer);
    }

    private static native void setX(long pointer, short x);

    public void setX(short x) {
        setX(super.pointer, x);
    }

    // unsigned short len;
    private static native int getLen(long pointer);

    public int getLen() {
        return getLen(super.pointer);
    }

    private static native void setLen(long pointer, int len);

    public void setLen(int len) {
        setLen(super.pointer, len);
    }

    // unsigned char coverage;
    private static native short getCoverage(long pointer);

    public short getCoverage() {
        return getCoverage(super.pointer);
    }

    private static native void setCoverage(long pointer, short coverage);

    public void setCoverage(short coverage) {
        setCoverage(super.pointer, coverage);
    }

}
