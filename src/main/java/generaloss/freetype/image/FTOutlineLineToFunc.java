package generaloss.freetype.image;

import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineLineToFunc {

    // FT_Error _(const FT_Vector* to, void* user)
    FTError invoke(final FTVector to);

}
