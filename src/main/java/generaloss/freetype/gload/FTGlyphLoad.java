package generaloss.freetype.gload;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.image.FTOutline;
import generaloss.freetype.types.FTVector;

public class FTGlyphLoad extends FTStruct { // struct done.

    public FTGlyphLoad(long pointer) {
        super(pointer);
    }

    public FTGlyphLoad() {
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


    // FT_Outline outline;
    private static native long getOutline(long pointer);

    public FTOutline getOutline() {
        final long pointer = getOutline(super.pointer);
        return FTStructCache.getOrCreate(FTOutline.class, pointer, FTOutline::new); 
    }

    // FT_Vector* extra_points;
    private static native long getExtraPoints(long pointer);

    public FTVector getExtraPoints() {
        final long pointer = getExtraPoints(super.pointer);
        return FTStructCache.getOrCreate(FTVector.class, pointer, FTVector::new); 
    }

    // FT_Vector* extra_points2;
    private static native long getExtraPoints2(long pointer);

    public FTVector getExtraPoints2() {
        final long pointer = getExtraPoints2(super.pointer);
        return FTStructCache.getOrCreate(FTVector.class, pointer, FTVector::new); 
    }

    // FT_UInt num_subglyphs;
    private static native long getNumSubglyphs(long pointer);

    public long getNumSubglyphs() {
        return getNumSubglyphs(super.pointer);
    }

    // FT_SubGlyph subglyphs;
    private static native long[] getSubglyphs(long pointer);

    public FTSubGlyph[] getSubglyphs() {
        final long[] pointers = getSubglyphs(super.pointer);
        if(pointers == null)
            return new FTSubGlyph[0];

        final FTSubGlyph[] objects = new FTSubGlyph[pointers.length];
        for(int i = 0; i < objects.length; i++)
            objects[i] = FTStructCache.getOrCreate(FTSubGlyph.class, pointers[i], FTSubGlyph::new); 

        return objects;
    }

}
