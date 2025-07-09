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

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setRows
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jlong rowsRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    bitmap->rows = static_cast<unsigned int>(rowsRaw);
}

// unsigned int width;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTBitmap_getWidth
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jlong>(bitmap->width);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setWidth
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jlong widthRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    bitmap->width = static_cast<unsigned int>(widthRaw);
}

// int pitch;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getPitch
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jint>(bitmap->pitch);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setPitch
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jint pitchRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    bitmap->pitch = static_cast<int>(pitchRaw);
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

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setBuffer__JLjava_nio_ByteBuffer_2
  (JNIEnv* env, jclass, jlong bitmapPtrRaw, jobject bufferRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    if(bitmap->buffer) {
        free(bitmap->buffer);
        bitmap->buffer = nullptr;
    }

    void* bufferAddress = env->GetDirectBufferAddress(bufferRaw);
    if(!bufferAddress)
        return;

    bitmap->buffer = reinterpret_cast<unsigned char*>(bufferAddress);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setBuffer__JJ
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jlong sizeRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    const size_t size = static_cast<size_t>(sizeRaw);
    if(size <= 0)
        return;

    if(bitmap->buffer) {
        free(bitmap->buffer);
        bitmap->buffer = nullptr;
    }

    bitmap->buffer = reinterpret_cast<unsigned char*>(malloc(size));
    if(bitmap->buffer)
        memset(bitmap->buffer, 0, size);
}

// unsigned short num_grays;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTBitmap_getNumGrays
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jint>(bitmap->num_grays);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setNumGrays
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jint numGraysRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    bitmap->num_grays = static_cast<unsigned short>(numGraysRaw);
}

// unsigned char pixel_mode;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTBitmap_getPixelMode
  (JNIEnv *, jclass, jlong bitmapPtrRaw) {

    const FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return 0;

    return static_cast<jshort>(bitmap->pixel_mode);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTBitmap_setPixelMode
  (JNIEnv *, jclass, jlong bitmapPtrRaw, jint pixelModeRaw) {

    FT_Bitmap* bitmap = reinterpret_cast<FT_Bitmap*>(bitmapPtrRaw);
    if(!bitmap)
        return;

    bitmap->pixel_mode = static_cast<unsigned char>(pixelModeRaw);
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