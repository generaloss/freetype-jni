package generaloss.freetype.image;

import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineCubicToFunc {

    // (const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user)
    void invoke(final FTVector control1, final FTVector control2, final FTVector to);

}
