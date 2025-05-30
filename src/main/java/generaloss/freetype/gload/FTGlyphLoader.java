package generaloss.freetype.gload;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.system.FTMemory;
import generaloss.freetype.types.FTError;

public class FTGlyphLoader extends FTStruct {

    public FTGlyphLoader(long pointer) {
        super(pointer);
    }


    // FT_Memory memory;
    private static native long getMemory(long pointer);

    public FTMemory getMemory() {
        final long pointer = getMemory(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTMemory::new);
    }

    // FT_UInt max_points;
    private static native long getMaxPoints(long pointer);

    public long getMaxPoints() {
        return getMaxPoints(super.pointer);
    }

    // FT_UInt max_contours;
    private static native long getMaxContours(long pointer);

    public long getMaxContours() {
        return getMaxContours(super.pointer);
    }

    // FT_UInt max_subglyphs;
    private static native long getMaxSubglyphs(long pointer);

    public long getMaxSubglyphs() {
        return getMaxSubglyphs(super.pointer);
    }

    // FT_Bool use_extra;
    private static native boolean getUseExtra(long pointer);

    public boolean getUseExtra() {
        return getUseExtra(super.pointer);
    }

    // FT_GlyphLoadRec base;
    private static native long getBase(long pointer);

    public FTGlyphLoad getBase() {
        final long pointer = getBase(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyphLoad::new);
    }

    // FT_GlyphLoadRec current;
    private static native long getCurrent(long pointer);

    public FTGlyphLoad getCurrent() {
        final long pointer = getCurrent(super.pointer);
        return FTStructCache.getOrCreate(pointer, FTGlyphLoad::new);
    }

    // void* other;
    private static native Object getOther(long pointer);

    public Object getOther() {
        return getOther(super.pointer);
    }


    public void newGlyphLoader(FTMemory memory) {
        final FTError error = FreeType.ftGlyphLoaderNew(memory, this);
        error.checkError();
    }

    public void createExtra() {
        final FTError error = FreeType.ftGlyphLoaderCreateExtra(this);
        error.checkError();
    }

    public void done() {
        FreeType.ftGlyphLoaderDone(this);
    }

    public void reset() {
        FreeType.ftGlyphLoaderReset(this);
    }

    public void rewind() {
        FreeType.ftGlyphLoaderRewind(this);
    }

    public void checkPoints(long nPoints, long nConrours) {
        final FTError error = FreeType.ftGlyphLoaderCheckPoints(this, nPoints, nConrours);
        error.checkError();
    }

    public void checkSubGlyphs(long nSubs) {
        final FTError error = FreeType.ftGlyphLoaderCheckSubGlyphs(this, nSubs);
        error.checkError();
    }

    public void prepare() {
        FreeType.ftGlyphLoaderPrepare(this);
    }

    public void add() {
        FreeType.ftGlyphLoaderAdd(this);
    }


    private static native long newStruct();

    public static FTGlyphLoader newInstance() {
        return new FTGlyphLoader(newStruct());
    }

}
