package generaloss.freetype.freetype;

import generaloss.freetype.BitMaskable;
import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;

import java.nio.ByteBuffer;

public class FTOpenArgs extends FTStruct {

    public FTOpenArgs(long pointer) {
        super(pointer);
    }

    public FTOpenArgs() {
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


    // FT_UInt flags;
    private static native int getFlags(long pointer);

    public int getFlagsRaw() {
        return getFlags(super.pointer);
    }

    public OpenFlags getFlags() {
        final int raw = this.getFlagsRaw();
        return new OpenFlags(raw);
    }

    private static native void setFlags(long pointer, int flags);

    public FTOpenArgs setFlags(int flags) {
        setFlags(super.pointer, flags);
        return this;
    }

    public FTOpenArgs setFlags(OpenFlags flags) {
        return this.setFlags(flags.getBits());
    }

    public FTOpenArgs setFlags(FTOpen... flags) {
        return this.setFlags(BitMaskable.makeMask(flags));
    }

    // const FT_Byte* memory_base;
    private static native byte getMemoryBase(long pointer);

    public byte getMemoryBase() {
        return getMemoryBase(super.pointer);
    }

    public static native void setMemoryBase(long pointer, ByteBuffer memory_base, long size);

    public FTOpenArgs setMemoryBase(ByteBuffer memoryBase) {
        setMemoryBase(super.pointer, memoryBase, memoryBase.limit());
        return this;
    }

    // FT_Long memory_size;
    private static native long getMemorySize(long pointer);

    public long getMemorySize() {
        return getMemorySize(super.pointer);
    }

    // FT_String* pathname;
    private static native String getPathname(long pointer);

    public String getPathname() {
        return getPathname(super.pointer);
    }

    private static native void setPathname(long pointer, String pathname);

    public FTOpenArgs setPathname(String pathname) {
        setPathname(super.pointer, pathname);
        return this;
    }

    // FT_Stream stream;
    private static native long getStream(long pointer);

    public FTStream getStream() {
        final long pointer = getStream(super.pointer);
        return FTStructCache.getOrCreate(FTStream.class, pointer, FTStream::new);
    }

    private static native void setStream(long pointer, long stream);

    public void setStream(FTStream stream) {
        setStream(super.pointer, FTStream.getPointer(stream));
    }

    // FT_Int num_params;
    private static native int getNumParams(long pointer);

    public int getNumParams() {
        return getNumParams(super.pointer);
    }

    // FT_Parameter* params;
    private static native long[] getParams(long pointer);

    public FTParameter[] getParams() {
        final long[] pointers = getParams(super.pointer);
        if(pointers == null)
            return new FTParameter[0];

        final FTParameter[] objects = new FTParameter[pointers.length];
        for(int i = 0; i < pointers.length; i++)
            objects[i] = FTStructCache.getOrCreate(FTParameter.class, pointers[i], FTParameter::new); 

        return objects;
    }

    private static native void setParams(long pointer, long[] params);

    public FTOpenArgs setParams(FTParameter... params) {
        final long[] pointers = FTStruct.makePointerArray(params);
        setParams(super.pointer, pointers);
        return this;
    }

}
