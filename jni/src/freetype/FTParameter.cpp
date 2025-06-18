// typedef struct FT_Parameter_ { ... } FT_Parameter;

#include "freetype/FTParameter.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTParameter_createPointer
  (JNIEnv *, jclass) {

    FT_Parameter* pointer = new FT_Parameter;
    memset(pointer, 0, sizeof(FT_Parameter));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_freePointer
  (JNIEnv *, jclass, jlong parameterPtrRaw) {

    FT_Parameter* parameter = reinterpret_cast<FT_Parameter*>(parameterPtrRaw);
    if(!parameter)
        return;

    delete parameter;
}


// FT_ULong tag;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTParameter_getTag
  (JNIEnv *, jclass, jlong parameterPtrRaw) {

    const FT_Parameter* parameter = reinterpret_cast<FT_Parameter*>(parameterPtrRaw);
    if(!parameter)
        return 0;

    return static_cast<jlong>(parameter->tag);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_setTag
  (JNIEnv *, jclass, jlong parameterPtrRaw, jlong tag) {

    FT_Parameter* parameter = reinterpret_cast<FT_Parameter*>(parameterPtrRaw);
    if(!parameter)
        return;

    parameter->tag = static_cast<FT_ULong>(tag);
}

// FT_Pointer data;
JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTParameter_setData
  (JNIEnv *env, jclass, jlong parameterPtrRaw, jobject byteBuffer) {

    FT_Parameter* parameter = reinterpret_cast<FT_Parameter*>(parameterPtrRaw);
    if(!parameter)
        return;

    if(!byteBuffer) {
        parameter->data = nullptr;
        return;
    }

    void* bufferPtr = env->GetDirectBufferAddress(byteBuffer);
    parameter->data = bufferPtr;
}
