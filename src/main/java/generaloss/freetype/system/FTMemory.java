package generaloss.freetype.system;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.gload.FTGlyphLoader;
import generaloss.freetype.types.FTError;

public class FTMemory extends FTStruct { // struct done.

    public FTMemory(long pointer) {
        super(pointer);
    }


    public FTGlyphLoader newGlyphLoader() {
        final long[] dstLoaderPointer = new long[1];
        final FTError error = FreeType.ftGlyphLoaderNew(this, dstLoaderPointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTGlyphLoader.class, dstLoaderPointer[0], FTGlyphLoader::new);
    }

}
