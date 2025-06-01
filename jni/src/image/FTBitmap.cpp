#include "image/FTBitmap.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getRows
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getWidth
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getPitch
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jobject JNICALL Java_generaloss_freetype_image_FTBitmap_getBuffer
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getNumGray
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getPixelMode
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_newStruct
  (JNIEnv *, jclass) {

    FT_Bitmap* pointer = new FT_Bitmap;
    return reinterpret_cast<jlong>(pointer);
}
