package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.types.FTTag;

import java.nio.ByteBuffer;

public class FTParameter extends FTStruct {

    public FTParameter(long pointer) {
        super(pointer);
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


    // FT_Pointer data;
    private static native ByteBuffer getData(long pointer);

    public ByteBuffer getData() {
        return getData(super.pointer);
    }


    private static native void setData(long pointer, ByteBuffer buffer);

    public void setData(ByteBuffer buffer) {
        setData(super.pointer, buffer);
    }


    private static native long newStruct();

    public static FTParameter newInstance() {
        return new FTParameter(newStruct());
    }

}