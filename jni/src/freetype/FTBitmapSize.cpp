// typedef struct FT_Bitmap_Size_ { ... } FT_Bitmap_Size;

#include "freetype/FTBitmapSize.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Short height;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getHeight
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return 0;

    return static_cast<jshort>(size->height);
}

// FT_Short width;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getWidth
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return 0;

    return static_cast<jshort>(size->width);
}

// FT_Pos size;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getSize
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return 0;

    return static_cast<jint>(size->size);
}

// FT_Pos x_ppem;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getXppem
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return 0;

    return static_cast<jint>(size->x_ppem);
}

// FT_Pos y_ppem;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTBitmapSize_getYppem
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Bitmap_Size* size = reinterpret_cast<FT_Bitmap_Size*>(sizePtrRaw);
    if(!size)
        return 0;

    return static_cast<jint>(size->y_ppem);
}