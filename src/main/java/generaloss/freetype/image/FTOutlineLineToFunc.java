package generaloss.freetype.image;

import generaloss.freetype.FTStructCache;
import generaloss.freetype.types.FTError;
import generaloss.freetype.types.FTVector;

@FunctionalInterface
public interface FTOutlineLineToFunc {

    // FT_Error _(const FT_Vector* to, void* user)
    FTError invoke(FTVector to);

    default int invokeNative(long toPointer) {
        final FTVector to = FTStructCache.getOrCreate(FTVector.class, toPointer, FTVector::new);
        final FTError error = this.invoke(to);
        return error.code;
    }

}
