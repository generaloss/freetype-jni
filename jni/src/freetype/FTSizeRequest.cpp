// typedef struct FT_Size_RequestRec_ *FT_Size_Request;

#include "freetype/FTSizeRequest.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_createPointer
  (JNIEnv *, jclass) {

    FT_Size_Request pointer = new FT_Size_RequestRec();
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_freePointer
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    delete request;
}


// FT_Size_Request_Type type;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getType
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return 0;

    return static_cast<jint>(request->type);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_setType
  (JNIEnv *, jclass, jlong requestPtrRaw, jint typeRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    request->type = static_cast<FT_Size_Request_Type>(typeRaw);
}

// FT_Long width;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getWidth
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return 0;

    return static_cast<jlong>(request->width);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_setWidth
  (JNIEnv *, jclass, jlong requestPtrRaw, jlong widthRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    request->width = static_cast<FT_Long>(widthRaw);
}

// FT_Long height;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHeight
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return 0;

    return static_cast<jlong>(request->height);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_setHeight
  (JNIEnv *, jclass, jlong requestPtrRaw, jlong heightRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    request->height = static_cast<FT_Long>(heightRaw);
}

// FT_UInt horiResolution;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHoriResolution
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return 0;

    return static_cast<jlong>(request->horiResolution);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_setHoriResolution
  (JNIEnv *, jclass, jlong requestPtrRaw, jlong horiResolutionRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    request->horiResolution = static_cast<FT_UInt>(horiResolutionRaw);
}

// FT_UInt vertResolution;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getVertResolution
  (JNIEnv *, jclass, jlong requestPtrRaw) {

    const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return 0;

    return static_cast<jlong>(request->vertResolution);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_setVertResolution
  (JNIEnv *, jclass, jlong requestPtrRaw, jlong vertResolutionRaw) {

    FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
    if(!request)
        return;

    request->vertResolution = static_cast<FT_UInt>(vertResolutionRaw);
}
