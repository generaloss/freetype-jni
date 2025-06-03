#include "types/FTF26Dot6.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTF26Dot6_getRawValue
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTF26Dot6_setRawValue
  (JNIEnv *, jclass, jlong, jint) {


}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTF26Dot6_newStruct
  (JNIEnv *, jclass) {

    FT_F26Dot6* pointer = new FT_F26Dot6;
    return reinterpret_cast<jlong>(pointer);
}
