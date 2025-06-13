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

    public FTParameter setTag(long tag) {
        setTag(super.pointer, tag);
        return this;
    }

    public FTParameter setTag(char x1, char x2, char x3, char x4) {
        return this.setTag(FTTag.ftMakeTag(x1, x2, x3, x4));
    }

    public FTParameter setTag(String tag) {
        return this.setTag(FTTag.ftMakeTag(tag));
    }

    public FTParameter setTag(FTParamTag tag) {
        return this.setTag(tag.value);
    }


    private static native void setData(long pointer, ByteBuffer dataBuffer);

    public FTParameter setData(ByteBuffer dataBuffer) {
        setData(super.pointer, dataBuffer);
        return this;
    }

    public FTParameter setDataNull() {
        return this.setData((ByteBuffer) null);
    }

    public FTParameter setData(byte... data) {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.put(data);
        buffer.flip();
        return this.setData(buffer);
    }

    public FTParameter setData(boolean... data) {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        for(boolean flag: data)
            buffer.put((byte) (flag ? 1 : 0));
        buffer.flip();
        return this.setData(buffer);
    }

    public FTParameter setData(int... data) {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(4 * data.length);
        for(int value: data)
            buffer.putInt(value);
        buffer.flip();
        return this.setData(buffer);
    }

}