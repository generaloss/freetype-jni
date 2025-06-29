package generaloss.freetype.image;

import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineMoveToFunc {

    // (const FT_Vector* to, void* user)
    void invoke(final FTVector to);

}
