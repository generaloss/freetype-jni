package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineCubicToFuncNative {

    // (const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user)
    void invoke(long control1P, long control2, long to);

}