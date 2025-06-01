#include "types/FTMatrix.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXX
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXY
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYX
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYY
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTMatrix_newStruct
  (JNIEnv *, jclass) {

    FT_Matrix* pointer = new FT_Matrix;
    return reinterpret_cast<jlong>(pointer);
}
