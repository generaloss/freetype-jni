package generaloss.freetype.image;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.types.FTTag;
import generaloss.freetype.types.FTVector;

public class FTOutline extends FTStruct { // struct done.

    public FTOutline(long pointer) {
        super(pointer);
    }


    // unsigned short n_contours;
    private static native short getNContours(long pointer);

    public short getNContours() {
        return getNContours(super.pointer);
    }

    // unsigned short n_points;
    private static native short getNPoints(long pointer);

    public short getNPoints() {
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
            objects[i] = FTStructCache.getOrCreate(FTVector.class, pointers[i], FTVector::new); 

        return objects;
    }

    // unsigned char* tags;
    private static native short[] getTags(long pointer);

    public short[] getTags() {
        final short[] values = getTags(super.pointer);
        if(values == null)
            return new short[0];
        return values;
    }

    public String[] getTagsString() {
        final short[] values = getTags(super.pointer);
        if(values == null)
            return new String[0];

        final String[] strings = new String[values.length];
        for(int i = 0; i < strings.length; i++)
            strings[i] = FTTag.decodeString(values[i]);

        return strings;
    }

    // unsigned short* contours;
    private static native short[] getContours(long pointer);

    public short[] getContours() {
        final short[] values = getContours(super.pointer);
        if(values == null)
            return new short[0];
        return values;
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

}
