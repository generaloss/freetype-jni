package generaloss.freetype.image;

@FunctionalInterface
interface FTOutlineLineToFuncNative {

    // (const FT_Vector* to, void* user)
    void invoke(long to);

}