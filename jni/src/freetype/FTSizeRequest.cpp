#include "freetype/FTSizeRequest.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getType
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getWidth
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getHoriResolution
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_getVertResolution
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeRequest_newStruct
  (JNIEnv *, jclass) {

    FT_Size_Request* pointer = new FT_Size_Request(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
