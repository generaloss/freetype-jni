// typedef struct FT_Open_Args_ { ... } FT_Open_Args;

#include "freetype/FTOpenArgs.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_createPointer
  (JNIEnv *, jclass) {

    FT_Open_Args* pointer = new FT_Open_Args;
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_freePointer
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return;

    delete args;
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getFlags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jbyte JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getMemoryBase
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getMemorySize
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getPathname
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getNumParams
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getParams
  (JNIEnv *, jclass, jlong) {

    return NULL;
}
