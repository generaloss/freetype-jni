// typedef signed long FT_Pos;

#include "types/FTPos.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTPos_createPointer
  (JNIEnv *, jclass) {

    FT_Pos* pointer = new FT_Pos;
    memset(pointer, 0, sizeof(FT_Pos));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTPos_freePointer
  (JNIEnv *, jclass, jlong posPtrRaw) {

    delete reinterpret_cast<FT_Matrix*>(posPtrRaw);
}


JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTPos_getRawValue
  (JNIEnv *, jclass, jlong posPtrRaw) {

    const FT_Pos* pos = reinterpret_cast<FT_Pos*>(posPtrRaw);
    if(!pos)
        return 0;

    return static_cast<jint>(*pos);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTPos_setRawValue
  (JNIEnv *, jclass, jlong posPtrRaw, jint valueRaw) {

    FT_Pos* pos = reinterpret_cast<FT_Pos*>(posPtrRaw);
    if(!pos)
        return;

    *pos = static_cast<jint>(valueRaw);
}
