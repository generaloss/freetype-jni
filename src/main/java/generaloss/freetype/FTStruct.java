package generaloss.freetype;

import generaloss.freetype.gload.FTSubGlyph;

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
        return (this.getClass().getSimpleName() + "[0x" + Long.toHexString(pointer) + "]");
    }


    public static long[] makePointerArray(FTStruct... structs) {
        final long[] pointers = new long[structs.length];
        for(int i = 0; i < structs.length; i++)
            pointers[i] = structs[i].pointer;
        return pointers;
    }

}
