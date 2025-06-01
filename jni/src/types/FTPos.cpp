#include "types/FTPos.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTPos_getRawValue
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTPos_newStruct
  (JNIEnv *, jclass) {

    FT_Pos* pointer = new FT_Pos;
    return reinterpret_cast<jlong>(pointer);
}
