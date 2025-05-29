package generaloss.freetype.stroke;

import generaloss.freetype.freetype.FTLibrary;
import generaloss.freetype.types.FTError;

// ftstroke.h
public class FreeTypeStroke {


    // FT_StrokerBorder FT_Outline_GetInsideBorder(FT_Outline* outline)


    // FT_StrokerBorder FT_Outline_GetOutsideBorder(FT_Outline* outline)


    // FT_Error FT_Stroker_New(FT_Library library, FT_Stroker *astroker)
    public static native int FT_Stroker_New(long library, long dstStroker);

    public static FTError ftStrokerNew(FTLibrary library, FTStroker dstStroker) {
        final int code = FT_Stroker_New(library.getPointer(), dstStroker.getPointer());
        return FTError.byCode(code);
    }


    // void FT_Stroker_Set(FT_Stroker stroker, FT_Fixed radius, FT_Stroker_LineCap line_cap, FT_Stroker_LineJoin line_join, FT_Fixed mite


    // void FT_Stroker_Rewind(FT_Stroker stroker)


    // FT_Error FT_Stroker_ParseOutline(FT_Stroker stroker, FT_Outline* outline, FT_Bool opened)


    // FT_Error FT_Stroker_BeginSubPath(FT_Stroker stroker, FT_Vector* to, FT_Bool open)


    // FT_Error FT_Stroker_EndSubPath(FT_Stroker stroker)


    // FT_Error FT_Stroker_LineTo(FT_Stroker stroker, FT_Vector* to)


    // FT_Error FT_Stroker_ConicTo(FT_Stroker stroker, FT_Vector* control, FT_Vector* to)


    // FT_Error FT_Stroker_CubicTo(FT_Stroker stroker, FT_Vector* control1, FT_Vector* control2, FT_Vector* to)


    // FT_Error FT_Stroker_GetBorderCounts(FT_Stroker stroker, FT_StrokerBorder border, FT_UInt *anum_points, FT_UInt *anum_contours)


    // void FT_Stroker_ExportBorder(FT_Stroker stroker, FT_StrokerBorder border, FT_Outline* outline)


    // FT_Error FT_Stroker_GetCounts(FT_Stroker stroker, FT_UInt *anum_points, FT_UInt *anum_contours)


    // void FT_Stroker_Export(FT_Stroker stroker, FT_Outline* outline)


    // void FT_Stroker_Done(FT_Stroker stroker)


    // FT_Error FT_Glyph_Stroke(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool destroy)


    // FT_Error FT_Glyph_StrokeBorder(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool inside, FT_Bool destroy)


}
