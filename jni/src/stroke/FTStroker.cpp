#include "stroke/FTStroker.h"
#include <ft2build.h>
#include FT_STROKER_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_stroke_FTStroker_newStruct
  (JNIEnv *, jclass) {

    FT_Stroker* pointer = new FT_Stroker(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
