package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.gload.FTSubGlyph;

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
    private static native long getFlags(long pointer);

    public long getFlags() {
        return getFlags(super.pointer);
    }

    // const FT_Byte* memory_base;
    private static native byte getMemoryBase(long pointer);

    public byte getMemoryBase() {
        return getMemoryBase(super.pointer);
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

    // FT_Stream stream;

    // FT_Module driver;

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
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTParameter::new);

        return objects;
    }

}
