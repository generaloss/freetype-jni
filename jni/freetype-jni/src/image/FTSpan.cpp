// typedef struct FT_Span_ { ... } FT_Span;

#include "image/FTSpan.h"
#include <jni.h>
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_IMAGE_H

// short x;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTSpan_getX
  (JNIEnv *, jclass, jlong spanPtrRaw) {

    const FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return 0;

    return static_cast<jshort>(span->x);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTSpan_setX
  (JNIEnv *, jclass, jlong spanPtrRaw, jshort xRaw) {

    FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return;

    span->x = static_cast<short>(xRaw);
}

// unsigned short len;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTSpan_getLen
  (JNIEnv *, jclass, jlong spanPtrRaw) {

    const FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return 0;

    return static_cast<jint>(span->len);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTSpan_setLen
  (JNIEnv *, jclass, jlong spanPtrRaw, jint lenRaw) {

    FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return;

    span->len = static_cast<unsigned short>(lenRaw);
}

// unsigned char coverage;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTSpan_getCoverage
  (JNIEnv *, jclass, jlong spanPtrRaw) {

    const FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return 0;

    return static_cast<jshort>(span->coverage);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTSpan_setCoverage
  (JNIEnv *, jclass, jlong spanPtrRaw, jshort coverageRaw) {

    FT_Span* span = reinterpret_cast<FT_Span*>(spanPtrRaw);
    if(!span)
        return;

    span->coverage = static_cast<unsigned char>(coverageRaw);
}
