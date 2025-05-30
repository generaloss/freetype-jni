package generaloss.freetype.stroke;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;
import generaloss.freetype.image.FTOutline;
import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTFixed;
import generaloss.freetype.types.FTVector;

public class FTStroker extends FTStruct {

    public FTStroker(long pointer) {
        super(pointer);
    }


    /** The radius is expressed in the same units as the outline coordinates.
     * The miter_limit multiplied by the radius gives the maximum size of a miter spike, at which it is clipped for FTStrokerLinejoin.MITER_VARIABLE or replaced with a bevel join for FTStrokerLinejoin.MITER_FIXED.
     * This function calls FT_Stroker_Rewind automatically. */
    public void set(float radius, FTStrokerLinecap lineCap, FTStrokerLinejoin lineJoin, float miterLimit) {
        FreeType.ftStrokerSet(this, FTFixed.of(radius), lineCap, lineJoin, FTFixed.of(miterLimit));
    }

    public void rewind() {
        FreeType.ftStrokerRewind(this);
    }

    public void parseOutline(FTOutline outline, boolean opened) {
        final FTError error = FreeType.ftStrokerParseOutline(this, outline, opened);
        error.checkError();
    }

    public void beginSubPath(FTVector to, boolean open) {
        final FTError error = FreeType.ftStrokerBeginSubPath(this, to, open);
        error.checkError();
    }

    public void endSubPath() {
        final FTError error = FreeType.ftStrokerEndSubPath(this);
        error.checkError();
    }

    public void lineTo(FTVector to) {
        final FTError error = FreeType.ftStrokerLineTo(this, to);
        error.checkError();
    }

    public void conicTo(FTVector control, FTVector to) {
        final FTError error = FreeType.ftStrokerConicTo(this, control, to);
        error.checkError();
    }

    public void cubicTo(FTVector control1, FTVector control2, FTVector to) {
        final FTError error = FreeType.ftStrokerCubicTo(this, control1, control2, to);
        error.checkError();
    }

    public void borderCounts(FTStrokerBorder border, long[] anumPoints, long[] anumContours) {
        final FTError error = FreeType.ftStrokerGetBorderCounts(this, border, anumPoints, anumContours);
        error.checkError();
    }

    public void exportBorder(FTStrokerBorder border, FTOutline outline) {
        FreeType.ftStrokerExportBorder(this, border, outline);
    }

    public void getCounts(long[] anumPoints, long[] anumContours) {
        final FTError error = FreeType.ftStrokerGetCounts(this, anumPoints, anumContours);
        error.checkError();
    }

    public void export(FTOutline outline) {
        FreeType.ftStrokerExport(this, outline);
    }

    /** Destroy a stroker object. */
    public void done() {
        FreeType.ftStrokerDone(this);
    }


    private static native long newStruct();

    public static FTStroker newInstance() {
        return new FTStroker(newStruct());
    }

}