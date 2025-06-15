package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.system.FTMemory;

public class FTStream extends FTStruct {

    private boolean readSet;
    private boolean closeSet;

    public FTStream(long pointer) {
        super(pointer);
    }

    public FTStream() {
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


    // unsigned long size;
    private static native long getSize(long pointer);

    public long getSize() {
        return getSize(this.pointer);
    }

    private static native void setSize(long pointer, long size);

    public void setSize(long size) {
        setSize(this.pointer, size);
    }

    // unsigned long pos;
    private static native long getPos(long pointer);

    public long getPos() {
        return getPos(this.pointer);
    }

    private static native void setPos(long pointer, long pos);

    public void setPos(long pos) {
        setPos(this.pointer, pos);
    }

    // FT_Stream_IoFunc read;
    private static native void setRead(long pointer, FTStreamIOFunc read);

    public void setRead(FTStreamIOFunc read) {
        if(readSet)
            throw new IllegalStateException("FTStreamIOFunc set");
        setRead(this.pointer, read);
        readSet = true;
    }

    // FT_Stream_CloseFunc close;
    private static native void setClose(long pointer, FTStreamCloseFunc close);

    public void setClose(FTStreamCloseFunc close) {
        if(closeSet)
            throw new IllegalStateException("FTStreamCloseFunc already set");
        setClose(this.pointer, close);
        closeSet = true;
    }

    // FT_Memory memory;
    private static native long getMemory(long pointer);

    public FTMemory getMemory() {
        final long pointer = getMemory(this.pointer);
        return FTStructCache.getOrCreate(FTMemory.class, pointer, FTMemory::new);
    }

}
