package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;
import generaloss.freetype.types.FTTag;

import java.nio.ByteBuffer;

public class FTParameter extends FTStruct { // struct done.

    public FTParameter(long pointer) {
        super(pointer);
    }

    public FTParameter() {
        this(createPointer());
    }

    static {
        FreeType.init();
    }

    private static native long createPointer();

    private static native void freePointer(long pointer);

    public void free() {
        freePointer(this.pointer);
        super.destroyPointer();
    }


    // FT_ULong tag;
    private static native long getTag(long pointer);

    public long getTag() {
        return getTag(super.pointer);
    }

    public String getTagString() {
        return FTTag.decodeString(this.getTag());
    }


    private static native void setTag(long pointer, long tag);

    public void setTag(long tag) {
        setTag(super.pointer, tag);
    }

    public void setTag(char a, char b, char c, char d) {
        this.setTag(FTTag.encode(a, b, c, d));
    }

    public void setTag(String tag) {
        this.setTag(FTTag.encode(tag));
    }


    private static native void setData(long pointer, ByteBuffer buffer);

    public void setData(ByteBuffer buffer) {
        setData(super.pointer, buffer);
    }

}