#include "types/FTVector.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTVector_getX
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTVector_setX
  (JNIEnv *, jclass, jlong, jlong) {


}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTVector_getY
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTVector_setY
  (JNIEnv *, jclass, jlong, jlong) {


}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTVector_newStruct
  (JNIEnv *, jclass) {

    FT_Vector* pointer = new FT_Vector;
    return reinterpret_cast<jlong>(pointer);
}
