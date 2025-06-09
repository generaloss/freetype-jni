// typedef struct FT_GlyphLoadRec_ { ... } FT_GlyphLoadRec, *FT_GlyphLoad;

#include "gload/FTGlyphLoad.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <freetype/internal/ftgloadr.h>

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_createPointer
  (JNIEnv *, jclass) {

    FT_GlyphLoad* pointer = new FT_GlyphLoad(nullptr);
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_freePointer
  (JNIEnv *, jclass, jlong gloadPtrRaw) {

    FT_GlyphLoad* gload = reinterpret_cast<FT_GlyphLoad*>(gloadPtrRaw);
    if(!gload)
        return;

    delete gload;
}


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
