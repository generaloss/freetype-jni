// typedef struct FT_Size_RequestRec_ *FT_Size_Request;

#include "freetype/FTSizeRequest.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_createPointer
  (JNIEnv *, jclass) {

    FT_Size_Request* pointer = new FT_Size_Request(nullptr);
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_freePointer
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    FT_Size_Request* request = reinterpret_cast<FT_Size_Request*>(requestPtrRaw);
    if(!request)
        return;

    delete request;
}


JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getType
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getWidth
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHeight
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHoriResolution
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getVertResolution
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    return 0;
}
