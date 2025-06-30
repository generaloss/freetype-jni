package generaloss.freetype.image;

import generaloss.freetype.BitMaskable;
import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.freetype.FTLibrary;
import generaloss.freetype.outln.FTOrientation;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.types.*;

public class FTOutline extends FTStruct { // struct done.

    public FTOutline(long pointer) {
        super(pointer);
    }


    // unsigned short n_contours;
    private static native int getNContours(long pointer);

    public int getNContours() {
        return getNContours(super.pointer);
    }

    private static native void setNContours(long pointer, int nContours);

    public void setNContours(int nContours) {
        setNContours(super.pointer, nContours);
    }

    // unsigned short n_points;
    private static native int getNPoints(long pointer);

    public int getNPoints() {
        return getNPoints(super.pointer);
    }

    private static native void setNPoints(long pointer, int nPoints);

    public void setNPoints(int nPoints) {
        setNPoints(super.pointer, nPoints);
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

    private static native void setPoints(long pointer, long[] points);

    public void setPoints(FTVector... points) {
        final long[] pointers = FTStruct.makePointerArray(points);
        setPoints(super.pointer, pointers);
    }

    // unsigned char* tags;
    private static native int[] getTags(long pointer);

    public int[] getTags() {
        final int[] values = getTags(super.pointer);
        if(values == null)
            return new int[0];
        return values;
    }

    public String[] getTagsString() {
        final int[] values = getTags(super.pointer);
        if(values == null)
            return new String[0];

        final String[] strings = new String[values.length];
        for(int i = 0; i < strings.length; i++)
            strings[i] = FTTag.decodeString(values[i]);

        return strings;
    }

    private static native void setTags(long pointer, int[] tags);

    public void setTags(int... tags) {
        setTags(super.pointer, tags);
    }

    public void setTags(String... tags) {
        final int[] array = new int[tags.length];
        for(int i = 0; i < array.length; i++)
            array[i] = FTTag.ftMakeTag(tags[i]);

        this.setTags(array);
    }

    // unsigned short* contours;
    private static native int[] getContours(long pointer);

    public int[] getContours() {
        final int[] values = getContours(super.pointer);
        if(values == null)
            return new int[0];
        return values;
    }

    private static native void setContours(long pointer, int[] contours);

    public void setContours(int... contours) {
        setContours(super.pointer, contours);
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

    private static native void setFlags(long pointer, int flags);

    public void setFlags(int flags) {
        setFlags(super.pointer, flags);
    }

    public void setFlags(OutlineFlags flags) {
        this.setFlags(flags.getBits());
    }

    public void setFlags(FTOutlineFlag... flags) {
        this.setFlags(BitMaskable.makeMask(flags));
    }


    public void decompose(FTOutlineFuncs funcInterfaces) {
        final FTError error = FreeType.ftOutlineDecompose(this, funcInterfaces);
        error.checkError();
    }

    public void done(FTLibrary library) {
        final FTError error = FreeType.ftOutlineDone(library, this);
        error.checkError();
    }

    public void check() {
        final FTError error = FreeType.ftOutlineCheck(this);
        error.checkError();
    }

    public void getCBox(FTBBox dstCbox) {
        FreeType.ftOutlineGetCBox(this, dstCbox);
    }

    public FTBBox getCBox() {
        final FTBBox dstCbox = new FTBBox();
        this.getCBox(dstCbox);
        return dstCbox;
    }

    public void translate(float xOffset, float yOffset) {
        FreeType.ftOutlineTranslate(this, xOffset, yOffset);
    }

    public void copy(FTOutline target) {
        final FTError error = FreeType.ftOutlineCopy(this, target);
        error.checkError();
    }

    public void transform(FTMatrix matrix) {
        FreeType.ftOutlineTransform(this, matrix);
    }

    public void embolden(float strength) {
        final FTError error = FreeType.ftOutlineEmbolden(this, strength);
        error.checkError();
    }

    public void embolden(float xStrength, float yStrength) {
        final FTError error = FreeType.ftOutlineEmboldenXY(this, xStrength, yStrength);
        error.checkError();
    }

    public void reverse() {
        FreeType.ftOutlineReverse(this);
    }

    public void getBitmap(FTLibrary library, FTBitmap dstBitmap) {
        final FTError error = FreeType.ftOutlineGetBitmap(library, this, dstBitmap);
        error.checkError();
    }

    public FTBitmap getBitmap(FTLibrary library) {
        final FTBitmap dstBitmap = new FTBitmap();
        this.getBitmap(library, dstBitmap);
        return dstBitmap;
    }

    public void render(FTLibrary library, FTRasterParams params) {
        final FTError error = FreeType.ftOutlineRender(library, this, params);
        error.checkError();
    }

    public FTOrientation getOrientation() {
        return FreeType.ftOutlineGetOrientation(this);
    }

    public FTStrokerBorder getInsideBorder() {
        return FreeType.ftOutlineGetInsideBorder(this);
    }

    public FTStrokerBorder getOutsideBorder() {
        return FreeType.ftOutlineGetOutsideBorder(this);
    }

}
