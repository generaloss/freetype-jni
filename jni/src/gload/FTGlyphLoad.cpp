#include "gload/FTGlyphLoad.h"
#include <ft2build.h>
#include FT_FREETYPE_H

typedef struct FT_GlyphLoadRec_* FT_GlyphLoad;

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getOutline
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getExtraPoints
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getExtraPoints2
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getNumSubglyphs
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getSubglyphs
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_newStruct
  (JNIEnv *, jclass) {

    FT_GlyphLoad* pointer = new FT_GlyphLoad;
    return reinterpret_cast<jlong>(pointer);
}
