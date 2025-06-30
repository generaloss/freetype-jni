package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineConicToFuncNative {

    // FT_Error _(const FT_Vector* control, const FT_Vector* to, void* user)
    int invoke(long control, long to);

}