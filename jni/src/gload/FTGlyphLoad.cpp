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

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_setExtraPoints
  (JNIEnv *env, jclass, jlong gloadPtrRaw, jlongArray extraPointsRaw) {

    FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload || !extraPointsRaw)
        return;

    const jsize length = env->GetArrayLength(extraPointsRaw);
    jlong* elements = env->GetLongArrayElements(extraPointsRaw, nullptr);

    gload->extra_points = static_cast<FT_Vector*>(malloc(sizeof(FT_Vector) * length));
    if(!gload->extra_points) {
        env->ReleaseLongArrayElements(extraPointsRaw, elements, JNI_ABORT);
        return;
    }

    for(jsize i = 0; i < length; i++) {
        const FT_Vector* src = reinterpret_cast<FT_Vector*>(elements[i]);
        if(src)
            gload->extra_points[i] = *src;
    }

    env->ReleaseLongArrayElements(extraPointsRaw, elements, JNI_ABORT);
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

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_setExtraPoints2
  (JNIEnv *env, jclass, jlong gloadPtrRaw, jlongArray extraPoints2Raw) {

    FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload || !extraPoints2Raw)
        return;

    const jsize length = env->GetArrayLength(extraPoints2Raw);
    jlong* elements = env->GetLongArrayElements(extraPoints2Raw, nullptr);

    gload->extra_points2 = static_cast<FT_Vector*>(malloc(sizeof(FT_Vector) * length));
    if(!gload->extra_points2) {
        env->ReleaseLongArrayElements(extraPoints2Raw, elements, JNI_ABORT);
        return;
    }

    for(jsize i = 0; i < length; i++) {
        const FT_Vector* src = reinterpret_cast<FT_Vector*>(elements[i]);
        if(src)
            gload->extra_points2[i] = *src;
    }

    env->ReleaseLongArrayElements(extraPoints2Raw, elements, JNI_ABORT);
}

// FT_UInt num_subglyphs;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_getNumSubglyphs
  (JNIEnv *, jclass, jlong gloadPtrRaw) {

    const FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(!gload)
        return 0;

    return static_cast<jlong>(gload->num_subglyphs);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_gload_FTGlyphLoad_setNumSubglyphs
  (JNIEnv *, jclass, jlong gloadPtrRaw, jint numSubglyphsRaw) {

    FT_GlyphLoad gload = reinterpret_cast<FT_GlyphLoad>(gloadPtrRaw);
    if(gload)
        gload->num_subglyphs = static_cast<FT_UInt>(numSubglyphsRaw);
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
