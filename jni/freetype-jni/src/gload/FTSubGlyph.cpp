// typedef struct FT_SubGlyphRec_* FT_SubGlyph;

#include "gload/FTSubGlyph.h"
#include <ft2build.h>
#include <freetype/internal/ftgloadr.h>
#include FT_FREETYPE_H

// FT_Int index;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getIndex
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->index);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTSubGlyph_setIndex
  (JNIEnv *, jclass, jlong subglyphPtrRaw, jint indexRaw) {

    FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(subglyph)
        subglyph->index = static_cast<FT_Int>(indexRaw);
}

// FT_UShort flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getFlags
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->flags);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTSubGlyph_setFlags
  (JNIEnv *, jclass, jlong subglyphPtrRaw, jint flagsRaw) {

    FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(subglyph)
        subglyph->flags = static_cast<FT_Int>(flagsRaw);
}

// FT_Int arg1;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg1
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->arg1);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTSubGlyph_setArg1
  (JNIEnv *, jclass, jlong subglyphPtrRaw, jint arg1Raw) {

    FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(subglyph)
        subglyph->arg1 = static_cast<FT_Int>(arg1Raw);
}

// FT_Int arg2;
JNIEXPORT jint JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getArg2
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return static_cast<jint>(subglyph->arg2);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTSubGlyph_setArg2
  (JNIEnv *, jclass, jlong subglyphPtrRaw, jint arg2Raw) {

    FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(subglyph)
        subglyph->arg2 = static_cast<FT_Int>(arg2Raw);
}

// FT_Matrix transform;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTSubGlyph_getTransform
  (JNIEnv *, jclass, jlong subglyphPtrRaw) {

    const FT_SubGlyph subglyph = reinterpret_cast<FT_SubGlyph>(subglyphPtrRaw);
    if(!subglyph)
        return 0;

    return reinterpret_cast<jlong>(&subglyph->transform);
}
