// typedef struct FT_GlyphLoaderRec_ { ... } FT_GlyphLoaderRec, *FT_GlyphLoader;

#include "gload/FTGlyphLoader.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <freetype/internal/ftgloadr.h>

JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_createPointer
  (JNIEnv *, jclass) {

    FT_GlyphLoader* pointer = new FT_GlyphLoader(nullptr);
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_freePointer
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    FT_GlyphLoader* loader = reinterpret_cast<FT_GlyphLoader*>(loaderPtrRaw);
    if(!loader)
        return;

    delete loader;
}


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
