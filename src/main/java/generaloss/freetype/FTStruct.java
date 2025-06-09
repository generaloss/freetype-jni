package generaloss.freetype;

import java.util.Objects;

public class FTStruct {

    protected long pointer;

    protected FTStruct(long pointer) {
        this.pointer = pointer;
        this.checkPointer();
        FTStructCache.register(this);
    }

    private void checkPointer() {
        if(this.isDestroyed()) {
            throw new IllegalArgumentException(
                "Invalid pointer (0). Unable to create " + this.getClass().getSimpleName() + " object."
            );
        }
    }


    public long getPointer() {
        return pointer;
    }

    protected void destroyPointer() {
        FTStructCache.unregister(pointer);
        pointer = 0L;
    }

    public boolean isDestroyed() {
        return (pointer == 0L);
    }


    @Override
    public String toString() {
        return (this.getClass().getSimpleName() + "[" + pointer + "]");
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTStruct struct = (FTStruct) object;
        return (pointer == struct.pointer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pointer);
    }


    public static boolean equals(FTStruct a, FTStruct b) {
        if(a == null || b == null || a.isDestroyed() || b.isDestroyed())
            return false;
        return (a.pointer == b.pointer);
    }

    public static long getPointer(FTStruct struct) {
        if(struct == null)
            return 0L;
        return struct.pointer;
    }

    public static long[] makePointerArray(FTStruct... structs) {
        if(structs == null)
            return null;

        final long[] pointers = new long[structs.length];
        for(int i = 0; i < structs.length; i++)
            pointers[i] = structs[i].pointer;

        return pointers;
    }

}
