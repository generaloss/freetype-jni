package generaloss.freetype.image;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.types.FTVector;

public class FTOutline extends FTStruct { // struct done.

    public FTOutline(long pointer) {
        super(pointer);
    }


    // unsigned short n_contours;
    private static native int getNContours(long pointer);

    public int getNContours() {
        return getNContours(super.pointer);
    }

    // unsigned short n_points;
    private static native int getNPoints(long pointer);

    public int getNPoints() {
        return getNPoints(super.pointer);
    }

    // FT_Vector* points;
    private static native long[] getPoints(long pointer);

    public FTVector[] getPoints() {
        final long[] pointers = getPoints(super.pointer);
        if(pointers == null)
            return new FTVector[0];

        final FTVector[] objects = new FTVector[pointers.length];
        for(int i = 0; i < objects.length; i++)
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTVector::new);

        return objects;
    }

    // unsigned char* tags;
    private static native int getTags(long pointer);

    public int getTags() {
        return getTags(super.pointer);
    }

    // unsigned short* contours;
    private static native int[] getContours(long pointer);

    public int[] getContours() {
        return getContours(super.pointer);
    }

    // int flags;
    private static native int getFlags(long pointer);

    public int getFlagsRaw() {
        return getFlags(super.pointer);
    }

    public OutlineFlags getFlags() {
        final int raw = this.getFlagsRaw();
        return new OutlineFlags(raw);
    }


    public FTStrokerBorder getInsideBorder(FTOutline outline) {
        return FreeType.ftOutlineGetInsideBorder(this);
    }

    public FTStrokerBorder getOutsideBorder(FTOutline outline) {
        return FreeType.ftOutlineGetOutsideBorder(this);
    }


    private static native long newStruct();

    public static FTOutline newInstance() {
        return new FTOutline(newStruct());
    }

}
