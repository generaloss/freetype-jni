package generaloss.freetype.image;

import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineCubicToFunc {

    // FT_Error _(const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user)
    FTError invoke(final FTVector control1, final FTVector control2, final FTVector to);

}
