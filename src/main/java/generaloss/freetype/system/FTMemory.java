package generaloss.freetype.system;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTMemory extends FTStruct { // struct done.

    public FTMemory(long pointer) {
        super(pointer);
    }

    public FTMemory() {
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


    private static FTMemory DEFAULT_INSTANCE;

    public static synchronized FTMemory getDefault() {
        if(DEFAULT_INSTANCE == null)
            DEFAULT_INSTANCE = new FTMemory();
        return DEFAULT_INSTANCE;
    }

}
