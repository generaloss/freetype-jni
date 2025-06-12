package generaloss.freetype.freetype;

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

    public void setFlags(int flags) {
        setFlags(super.pointer, flags);
    }

    public void setFlags(OpenFlags flags) {
        this.setFlags(flags.getBits());
    }

    // const FT_Byte* memory_base;
    private static native byte getMemoryBase(long pointer);

    public byte getMemoryBase() {
        return getMemoryBase(super.pointer);
    }

    public static native void setMemoryBase(long pointer, ByteBuffer memory_base, long size);

    public void setMemoryBase(ByteBuffer memoryBase) {
        setMemoryBase(super.pointer, memoryBase, memoryBase.limit());
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

    public void setPathname(String pathname) {
        setPathname(super.pointer, pathname);
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

    public void setParams(FTParameter... params) {
        final long[] pointers = FTStruct.makePointerArray(params);
        setParams(super.pointer, pointers);
    }

}
