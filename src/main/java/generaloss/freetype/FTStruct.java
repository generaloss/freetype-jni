package generaloss.freetype;

import generaloss.freetype.freetype.FTLibrary;

public class FTStruct {

    protected long pointer;

    protected FTStruct(long pointer) {
        if(pointer == 0L) {
            throw new IllegalArgumentException(
                "Invalid pointer (0). Unable to create " + this.getClass().getSimpleName() +
                " object. FT last error: " + FTLibrary.getLastError()
            );
        }
        this.pointer = pointer;
    }

    public long getPointer() {
        return pointer;
    }

    public void done() {
        pointer = 0L;
    }


    public static long[] makePointerArray(FTStruct... struct) {
        final long[] pointers = new long[struct.length];
        for(int i = 0; i < struct.length; i++)
            pointers[i] = struct[i].pointer;
        return pointers;
    }

}
