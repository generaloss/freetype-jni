#include "types/FTFixed.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTFixed_getRawValue
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTFixed_newStruct
  (JNIEnv *, jclass) {

    FT_Fixed* pointer = new FT_Fixed;
    return reinterpret_cast<jlong>(pointer);
}
