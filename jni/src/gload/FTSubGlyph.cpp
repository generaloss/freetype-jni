#include "gload/FTSubGlyph.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getIndex
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getFlags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg1
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg2
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getTransform
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTSubGlyph_newStruct
  (JNIEnv *, jclass) {

    FT_SubGlyph* pointer = new FT_SubGlyph;
    return reinterpret_cast<jlong>(pointer);
}
