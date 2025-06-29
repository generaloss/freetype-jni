package generaloss.freetype.image;

import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineConicToFunc {

    // (const FT_Vector* control, const FT_Vector* to, void* user)
    void invoke(final FTVector control, final FTVector to);

}
