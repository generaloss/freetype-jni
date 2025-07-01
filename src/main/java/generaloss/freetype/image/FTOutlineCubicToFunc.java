package generaloss.freetype.image;

import generaloss.freetype.FTStructCache;
import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineCubicToFunc {

    // FT_Error _(const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user)
    FTError invoke(FTVector control1, FTVector control2, FTVector to);

    default int invokeNative(long control1Pointer, long control2Pointer, long toPointer) {
        final FTVector control1 = FTStructCache.getOrCreate(FTVector.class, control1Pointer, FTVector::new);
        final FTVector control2 = FTStructCache.getOrCreate(FTVector.class, control2Pointer, FTVector::new);
        final FTVector to = FTStructCache.getOrCreate(FTVector.class, toPointer, FTVector::new);

        final FTError error = this.invoke(control1, control2, to);
        return error.code;
    }

}
