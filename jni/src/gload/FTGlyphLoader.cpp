// typedef struct FT_GlyphLoaderRec_ { ... } FT_GlyphLoaderRec, *FT_GlyphLoader;

#include "gload/FTGlyphLoader.h"
#include <ft2build.h>
#include <freetype/internal/ftgloadr.h>
#include FT_FREETYPE_H

// FT_Memory memory;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMemory
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return reinterpret_cast<jlong>(loader->memory);
}

// FT_UInt max_points;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxPoints
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return static_cast<jlong>(loader->max_points);
}

// FT_UInt max_contours;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxContours
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return static_cast<jlong>(loader->max_contours);
}

// FT_UInt max_subglyphs;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getMaxSubglyphs
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return static_cast<jlong>(loader->max_subglyphs);
}

// FT_Bool use_extra;
JNIEXPORT jboolean JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getUseExtra
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return static_cast<jboolean>(loader->use_extra);
}

// FT_GlyphLoadRec base;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getBase
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return reinterpret_cast<jlong>(&loader->base);
}

// FT_GlyphLoadRec current;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoader_getCurrent
  (JNIEnv *, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader)
        return 0;

    return reinterpret_cast<jlong>(&loader->current);
}
