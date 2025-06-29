// typedef struct FT_Outline_Funcs_ { ... } FT_Outline_Funcs;

#include "image/FTOutlineFuncs.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_createPointer
  (JNIEnv *, jclass) {

    FT_Outline_Funcs* pointer = new FT_Outline_Funcs();
    memset(pointer, 0, sizeof(FT_Outline_Funcs));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_freePointer
  (JNIEnv *, jclass, jlong funcsPtrRaw) {

    delete reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
}


// FT_Outline_MoveToFunc move_to;
JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setMoveTo
  (JNIEnv *, jclass, jlong funcsPtrRaw, jobject) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->move_to = 0;
}

// FT_Outline_LineToFunc line_to;
JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setLineTo
  (JNIEnv *, jclass, jlong funcsPtrRaw, jobject) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->line_to = 0;
}

// FT_Outline_ConicToFunc conic_to;
JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setConicTo
  (JNIEnv *, jclass, jlong funcsPtrRaw, jobject) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->conic_to = 0;
}

// FT_Outline_CubicToFunc cubic_to;
JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setCubicTo
  (JNIEnv *, jclass, jlong funcsPtrRaw, jobject) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->cubic_to = 0;
}

// int shift;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_getShift
  (JNIEnv *, jclass, jlong funcsPtrRaw) {

    const FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return 0;

    return static_cast<jint>(funcs->shift);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setShift
  (JNIEnv *, jclass, jlong funcsPtrRaw, jint shiftRaw) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->shift = static_cast<int>(shiftRaw);
}

// FT_Pos delta;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_getDelta
  (JNIEnv *, jclass, jlong funcsPtrRaw) {

    const FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return 0;

    return static_cast<jint>(funcs->delta);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setDelta
  (JNIEnv *, jclass, jlong funcsPtrRaw, jint deltaRaw) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->delta = static_cast<int>(deltaRaw);
}