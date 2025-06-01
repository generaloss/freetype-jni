#include "image/FTOutline.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getNContours
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getNPoints
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_image_FTOutline_getPoints
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getTags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jintArray JNICALL Java_generaloss_freetype_image_FTOutline_getContours
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getFlags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTOutline_newStruct
  (JNIEnv *, jclass) {

    FT_Outline* pointer = new FT_Outline;
    return reinterpret_cast<jlong>(pointer);
}
