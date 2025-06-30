// typedef struct FT_Bitmap_ { ... } FT_Bitmap;

#include "image/FTBitmap.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_createPointer
  (JNIEnv *, jclass) {

    FT_Bitmap* pointer = new FT_Bitmap();
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_freePointer
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    delete reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
}


// unsigned int rows;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_getRows
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jlong>(bitmap->rows);
}

// unsigned int width;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_getWidth
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jlong>(bitmap->width);
}

// int pitch;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getPitch
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jint>(bitmap->pitch);
}

// unsigned char* buffer;
JNIEXPORT jobject JNICALL Java_generaloss_freetype_image_FTBitmap_getBuffer
  (JNIEnv* env, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap || !bitmap->buffer)
        return NULL;

    const unsigned int size = (bitmap->rows * bitmap->pitch);
    return env->NewDirectByteBuffer(bitmap->buffer, size);
}

// unsigned short num_grays;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getNumGrays
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jint>(bitmap->num_grays);
}

// unsigned char pixel_mode;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTBitmap_getPixelMode
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jshort>(bitmap->pixel_mode);
}

// unsigned char palette_mode;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTBitmap_getPaletteMode
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jshort>(bitmap->palette_mode);
}

// void* palette;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_getPalettePointer
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap || !bitmap->palette)
        return 0;

    return reinterpret_cast<jlong>(bitmap->palette);
}