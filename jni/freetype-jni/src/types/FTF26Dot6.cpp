// typedef signed long FT_F26Dot6;

#include "types/FTF26Dot6.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTF26Dot6_createPointer
  (JNIEnv *, jclass) {

    FT_F26Dot6* pointer = new FT_F26Dot6;
    memset(pointer, 0, sizeof(FT_F26Dot6));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTF26Dot6_freePointer
  (JNIEnv *, jclass, jlong f26dot6PtrRaw) {

    delete reinterpret_cast<FT_F26Dot6*>(f26dot6PtrRaw);
}


JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTF26Dot6_getRawValue
  (JNIEnv *, jclass, jlong f26dot6PtrRaw) {

    const FT_F26Dot6* f26dot6 = reinterpret_cast<FT_F26Dot6*>(f26dot6PtrRaw);
    if(!f26dot6)
        return 0;

    return static_cast<jint>(*f26dot6);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTF26Dot6_setRawValue
  (JNIEnv *, jclass, jlong f26dot6PtrRaw, jint valueRaw) {

    FT_F26Dot6* f26dot6 = reinterpret_cast<FT_F26Dot6*>(f26dot6PtrRaw);
    if(!f26dot6)
        return;

    *f26dot6 = static_cast<jint>(valueRaw);
}
