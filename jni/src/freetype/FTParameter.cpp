// typedef struct FT_Parameter_ { ... } FT_Parameter;

#include "freetype/FTParameter.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTParameter_createPointer
  (JNIEnv *, jclass) {

    FT_Parameter* pointer = new FT_Parameter;
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_freePointer
  (JNIEnv *, jclass, jlong parameterPtrRaw) {

    FT_Parameter* parameter = reinterpret_cast<FT_Parameter*>(parameterPtrRaw);
    if(!parameter)
        return;

    delete parameter;
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTParameter_getTag
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_setTag
  (JNIEnv *, jclass, jlong, jlong) {

}

JNIEXPORT jobject JNICALL Java_generaloss_freetype_freetype_FTParameter_getData
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_setData
  (JNIEnv *, jclass, jlong, jobject) {

}
