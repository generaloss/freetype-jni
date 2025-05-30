#include "FTStroker.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_STROKER_H

JNIEXPORT void JNICALL Java_generaloss_freetype_stroker_FTStroker_set (
        JNIEnv *, jclass, jlong strokerPtr, jint radius, jint lineCap, jint lineJoin, jint miterLimit) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtr);
    if(!stroker)
        return;

    FT_Stroker_Set(
        stroker, radius,
        static_cast<FT_Stroker_LineCap>(lineCap), static_cast<FT_Stroker_LineJoin>(lineJoin),
        miterLimit
    );
}

JNIEXPORT void JNICALL Java_generaloss_freetype_stroker_FTStroker_done (JNIEnv *, jclass, jlong strokerPtr) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtr);
    if(!stroker)
        return;

    FT_Stroker_Done(stroker);
}