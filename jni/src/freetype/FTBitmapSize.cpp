// typedef struct FT_Bitmap_Size_ { ... } FT_Bitmap_Size;

#include "freetype/FTBitmapSize.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_createPointer
  (JNIEnv *, jclass) {

    FT_Bitmap_Size* pointer = new FT_Bitmap_Size;
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_freePointer
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return;

    delete size;
}


JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getWidth
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getSize
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getXppem
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getYppem
  (JNIEnv *, jclass, jlong) {

    return 0;
}