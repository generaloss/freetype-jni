package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineConicToFuncNative {

    // (const FT_Vector* control, const FT_Vector* to, void* user)
    void invoke(long control, long to);

}