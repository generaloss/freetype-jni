// typedef struct FT_SubGlyphRec_* FT_SubGlyph;

#include "gload/FTSubGlyph.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <freetype/internal/ftgloadr.h>

// FT_Int index;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getIndex
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->index);
}

// FT_UShort flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getFlags
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->flags);
}

// FT_Int arg1;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg1
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->arg1);
}

// FT_Int arg2;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg2
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->arg2);
}

// FT_Matrix transform;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getTransform
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return reinterpret_cast<jlong>(&subglyph->transform);
}
