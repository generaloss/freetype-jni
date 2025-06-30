package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineMoveToFuncNative {

    // FT_Error _(const FT_Vector* to, void* user)
    int invoke(long to);

}