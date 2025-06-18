// typedef signed long FT_Fixed;

#include "types/FTFixed.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTFixed_createPointer
  (JNIEnv *, jclass) {

    FT_Fixed* pointer = new FT_Fixed;
    memset(pointer, 0, sizeof(FT_Fixed));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTFixed_freePointer
  (JNIEnv *, jclass, jlong fixedPtrRaw) {

    FT_Fixed* fixed = reinterpret_cast<FT_Fixed*>(fixedPtrRaw);
    if(!fixed)
        return;

    delete fixed;
}


JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTFixed_getRawValue
  (JNIEnv *, jclass, jlong fixedPtrRaw) {

    const FT_Fixed* fixed = reinterpret_cast<FT_Fixed*>(fixedPtrRaw);
    if(!fixed)
        return 0;

    return static_cast<jint>(*fixed);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTFixed_setRawValue
  (JNIEnv *, jclass, jlong fixedPtrRaw, jint valueRaw) {

    FT_Fixed* fixed = reinterpret_cast<FT_Fixed*>(fixedPtrRaw);
    if(!fixed)
        return;

    *fixed = static_cast<jint>(valueRaw);
}
