#include "freetype/FTSize.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSize_getFace
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSize_getMetrics
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSize_newStruct
  (JNIEnv *, jclass) {

    FT_Size* pointer = new FT_Size;
    return reinterpret_cast<jlong>(pointer);
}
