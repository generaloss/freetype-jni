#include "types/FTBBox.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_getXMin
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_getYMin
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_getXMax
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_getYMax
  (JNIEnv *, jclass, jlong) {

    return 0;
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_newStruct
  (JNIEnv *, jclass) {

    FT_BBox* pointer = new FT_BBox;
    return reinterpret_cast<jlong>(pointer);
}
