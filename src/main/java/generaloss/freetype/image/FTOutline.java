package generaloss.freetype.image;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.types.FTVector;

public class FTOutline extends FTStruct { // struct done.

    public FTOutline(long pointer) {
        super(pointer);
    }


    // unsigned short n_contours;
    private static native int getNContours(long pointer);

    /** The number of contours in the outline. */
    public int getNContours() {
        return getNContours(super.pointer);
    }

    // unsigned short n_points;
    private static native int getNPoints(long pointer);

    /** The number of points in the outline. */
    public int getNPoints() {
        return getNPoints(super.pointer);
    }

    // FT_Vector* points;
    private static native long[] getPoints(long pointer);

    /** A pointer to an array of n_points FT_Vector elements, giving the outline's point coordinates. */
    public FTVector[] getPoints() {
        final long[] pointers = getPoints(super.pointer);

        final FTVector[] objects = new FTVector[pointers.length];
        for(int i = 0; i < objects.length; i++)
            objects[i] = FTStructCache.getOrCreate(pointers[i], FTVector::new);

        return objects;
    }

    // unsigned char* tags;
    private static native int getTags(long pointer);

    /** A pointer to an array of n_points chars, giving each outline point's type.
     * If bit 0 is unset, the point is ‘off’ the curve, i.e., a Bezier control point, while it is ‘on’ if set.
     * Bit 1 is meaningful for ‘off’ points only.
     * If set, it indicates a third-order Bezier arc control point; and a second-order control point if unset.
     * If bit 2 is set, bits 5-7 contain the drop-out mode (as defined in the OpenType specification; the value is the same as the argument to the ‘SCANTYPE’ instruction).
     * Bits 3 and 4 are reserved for internal purposes.
     * */
    public int getTags() {
        return getTags(super.pointer);
    }

    // unsigned short* contours;
    private static native int[] getContours(long pointer);

    /** An array of n_contours shorts, giving the end point of each contour within the outline.
     * For example, the first contour is defined by the points ‘0’ to contours[0], the second one is defined by the points contours[0]+1 to contours[1], etc.
     * */
    public int[] getContours() {
        return getContours(super.pointer);
    }

    // int flags;
    private static native int getFlags(long pointer);

    /** A set of bit flags used to characterize the outline and give hints to the scan-converter and hinter on how to convert/grid-fit it.
     * See FT_OUTLINE_XXX.
     * */
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
