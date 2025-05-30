#include "FTBitmap.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getRows (JNIEnv *, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return 0;

    return bitmap->rows;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getWidth (JNIEnv *, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return 0;

    return bitmap->width;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getPitch (JNIEnv *, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return 0;

    return bitmap->pitch;
}

JNIEXPORT jobject JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getBuffer (JNIEnv *env, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return nullptr;

    return env->NewDirectByteBuffer(bitmap->buffer, bitmap->rows * bitmap->pitch);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getNumGray (JNIEnv *, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return 0;

    return bitmap->num_grays;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_bitmap_FTBitmap_getPixelMode (JNIEnv *, jclass, jlong bitmapPtr) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtr);
    if(!bitmap)
        return FT_PIXEL_MODE_NONE;

    return bitmap->pixel_mode;
}
