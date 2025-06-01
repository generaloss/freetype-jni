package generaloss.freetype.system;

import generaloss.freetype.FTStruct;

public class FTMemory extends FTStruct { // struct done.

    public FTMemory(long pointer) {
        super(pointer);
    }


    private static native long newStruct();

    public static FTMemory newInstance() {
        return new FTMemory(newStruct());
    }


    private static FTMemory DEFAULT_INSTANCE;

    public static synchronized FTMemory getDefault() {
        if(DEFAULT_INSTANCE == null)
            DEFAULT_INSTANCE = newInstance();
        return DEFAULT_INSTANCE;
    }

}
