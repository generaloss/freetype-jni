#include "gload/FTGlyphLoader.h"
#include <ft2build.h>
#include FT_FREETYPE_H

typedef struct FT_GlyphLoaderRec_* FT_GlyphLoader;

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMemory
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxPoints
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxContours
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxSubglyphs
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getUseExtra
  (JNIEnv *, jclass, jlong) {

    return false;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getBase
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getCurrent
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_newStruct
  (JNIEnv *, jclass) {

    FT_GlyphLoader* pointer = new FT_GlyphLoader(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
