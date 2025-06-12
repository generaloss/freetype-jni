// typedef struct FT_GlyphLoadRec_ { ... } FT_GlyphLoadRec, *FT_GlyphLoad;

#include "gload/FTGlyphLoad.h"
#include <ft2build.h>
#include <freetype/internal/ftgloadr.h>
#include FT_FREETYPE_H
#include <jni.h>

// FT_Outline outline;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getOutline
  (JNIEnv *, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return 0;

    return reinterpret_cast<jlong>(&gload->outline);
}

// FT_Vector* extra_points;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getExtraPoints
  (JNIEnv *env, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return NULL;

    const FT_UInt count = gload->outline.n_points;
    jlongArray result = env->NewLongArray(count);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(FT_UInt i = 0; i < count; i++)
        elements[i] = reinterpret_cast<jlong>(&gload->extra_points[i]);

    env->ReleaseLongArrayElements(result, elements, 0);
    return result;
}

// FT_Vector* extra_points2;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getExtraPoints2
  (JNIEnv *env, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return NULL;

    const FT_UInt count = gload->outline.n_points;
    jlongArray result = env->NewLongArray(count);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(FT_UInt i = 0; i < count; i++)
        elements[i] = reinterpret_cast<jlong>(&gload->extra_points2[i]);

    env->ReleaseLongArrayElements(result, elements, 0);
    return result;
}

// FT_UInt num_subglyphs;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getNumSubglyphs
  (JNIEnv *, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return 0;

    return static_cast<jlong>(gload->num_subglyphs);
}

// FT_SubGlyph subglyphs;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getSubglyphs
  (JNIEnv *env, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return NULL;

    const FT_UInt count = gload->num_subglyphs;
    jlongArray result = env->NewLongArray(count);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(FT_UInt i = 0; i < count; i++)
        elements[i] = reinterpret_cast<jlong>(&gload->subglyphs[i]);

    env->ReleaseLongArrayElements(result, elements, 0);
    return result;
}
