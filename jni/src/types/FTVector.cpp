#include "types/FTVector.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Pos x;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTVector_getRawX
  (JNIEnv *, jclass, jlong vectorPtrRaw) {

    const FT_Vector* vector = reinterpret_cast<FT_Vector*>(vectorPtrRaw);
    if(!vector)
        return 0;

    return static_cast<jint>(vector->x);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTVector_setRawX
  (JNIEnv *, jclass, jlong vectorPtrRaw, jint xRaw) {

    FT_Vector* vector = reinterpret_cast<FT_Vector*>(vectorPtrRaw);
    if(!vector)
        return;

    vector->x = static_cast<FT_Pos>(xRaw);
}

// FT_Pos y;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTVector_getRawY
  (JNIEnv *, jclass, jlong vectorPtrRaw) {

    const FT_Vector* vector = reinterpret_cast<FT_Vector*>(vectorPtrRaw);
    if(!vector)
        return 0;

    return static_cast<jint>(vector->y);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTVector_setRawY
  (JNIEnv *, jclass, jlong vectorPtrRaw, jint yRaw) {

    FT_Vector* vector = reinterpret_cast<FT_Vector*>(vectorPtrRaw);
    if(!vector)
        return;

    vector->y = static_cast<FT_Pos>(yRaw);
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTVector_newStruct
  (JNIEnv *, jclass) {

    FT_Vector* pointer = new FT_Vector;
    return reinterpret_cast<jlong>(pointer);
}
