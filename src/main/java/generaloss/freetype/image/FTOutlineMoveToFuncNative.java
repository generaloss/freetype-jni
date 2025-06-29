package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineMoveToFuncNative {

    // (const FT_Vector* to, void* user)
    void invoke(long to);

}