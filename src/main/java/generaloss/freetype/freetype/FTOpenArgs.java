package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;

public class FTOpenArgs extends FTStruct { // struct done.

    public FTOpenArgs(long pointer) {
        super(pointer);
    }


    // FT_UInt flags;
    private static native long getFlags(long pointer);

    /** A set of bit flags indicating how to use the structure. */
    public long getFlags() {
        return getFlags(super.pointer);
    }


    // const FT_Byte* memory_base;
    private static native byte getMemoryBase(long pointer);

    /** The first byte of the file in memory. */
    public byte getMemoryBase() {
        return getMemoryBase(super.pointer);
    }


    // FT_Long memory_size;
    private static native long getMemorySize(long pointer);

    /** The size in bytes of the file in memory. */
    public long getMemorySize() {
        return getMemorySize(super.pointer);
    }


    // FT_String* pathname;
    private static native String getPathname(long pointer);

    /** A pointer to an 8-bit file pathname, which must be a C string (i.e., no null bytes except at the very end).
     * The pointer is not owned by FreeType.
     * */
    public String getPathname() {
        return getPathname(super.pointer);
    }


    // FT_Stream stream;
    private static native long getStream(long pointer);

    // /** A handle to a source stream object. */
    // public FTStream getStream() {
    //     final long pointer = getStream(super.pointer);
    //     return new FTStream(pointer);
    // }


    // FT_Module driver;
    private static native long getDriver(long pointer);

    // /** This field is exclusively used by FT_Open_Face; it simply specifies the font driver to use for opening the face.
    //  * If set to NULL, FreeType tries to load the face with each one of the drivers in its list.
    //  * */
    // public FTModule getDriver() {
    //     final long pointer = getDriver(super.pointer);
    //     return new FTModule(pointer);
    // }


    // FT_Int num_params;
    private static native int getNumParams(long pointer);

    /** The number of extra parameters. */
    public int getNumParams() {
        return getNumParams(super.pointer);
    }


    // FT_Parameter* params;
    private static native long[] getParams(long pointer);

    // /** Extra parameters passed to the font driver when opening a new face. */
    // public FTParameter[] getParams() {
    //     final long[] pointers = getParams(super.pointer);
    //
    //     final FTParameter[] objects = new FTParameter[pointers.length];
    //     for(int i = 0; i < pointers.length; i++)
    //         objects[i] = new FTParameter(pointers[i]);
    //
    //     return objects;
    // }

}
