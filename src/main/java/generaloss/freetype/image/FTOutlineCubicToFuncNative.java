package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineCubicToFuncNative {

    // FT_Error _(const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user)
    int invoke(long control1P, long control2, long to);

}