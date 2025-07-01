package generaloss.freetype.image;

import generaloss.freetype.FTStructCache;
import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineConicToFunc {

    // FT_Error _(const FT_Vector* control, const FT_Vector* to, void* user)
    FTError invoke(FTVector control, FTVector to);

    default int invokeNative(long controlPointer, long toPointer) {
        final FTVector control = FTStructCache.getOrCreate(FTVector.class, controlPointer, FTVector::new);
        final FTVector to = FTStructCache.getOrCreate(FTVector.class, toPointer, FTVector::new);

        final FTError error = this.invoke(control, to);
        return error.code;
    }

}
