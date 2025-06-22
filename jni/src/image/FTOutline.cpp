// typedef struct FT_Outline_ { ... } FT_Outline;

#include "image/FTOutline.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <jni.h>

// unsigned short n_contours;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getNContours
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jint>(outline->n_contours);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setNContours
  (JNIEnv *, jclass, jlong outlinePtrRaw, jint nContoursRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return;

    outline->n_contours = static_cast<unsigned short>(nContoursRaw);
}

// unsigned short n_points;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getNPoints
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jint>(outline->n_points);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setNPoints
  (JNIEnv *, jclass, jlong outlinePtrRaw, jint nPointsRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return;

    outline->n_points = static_cast<unsigned short>(nPointsRaw);
}

// FT_Vector* points;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_image_FTOutline_getPoints
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(outline->n_points == 0)
        return NULL;

    const jlongArray result = env->NewLongArray(outline->n_points);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(int i = 0; i < outline->n_points; i++)
        elements[i] = reinterpret_cast<jlong>(&outline->points[i]);

    env->ReleaseLongArrayElements(result, elements, 0);

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setPoints
  (JNIEnv *env, jclass, jlong outlinePtrRaw, jlongArray pointsPtrsRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline || !pointsPtrsRaw)
        return;

    const jsize length = env->GetArrayLength(pointsPtrsRaw);
    jlong* elements = env->GetLongArrayElements(pointsPtrsRaw, NULL);
    if(!elements)
        return;

    // allocate or reallocate points array
    outline->points = static_cast<FT_Vector*>(malloc(sizeof(FT_Vector) * length));
    if(!outline->points) {
        env->ReleaseLongArrayElements(pointsPtrsRaw, elements, JNI_ABORT);
        return;
    }

    for(jsize i = 0; i < length; i++) {
        const FT_Vector* src = reinterpret_cast<FT_Vector*>(elements[i]);
        if(src)
            outline->points[i] = *src;
    }

    outline->n_points = static_cast<short>(length);

    env->ReleaseLongArrayElements(pointsPtrsRaw, elements, JNI_ABORT);
}

// unsigned char* tags;
JNIEXPORT jintArray JNICALL Java_generaloss_freetype_image_FTOutline_getTags
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(!outline->tags || outline->n_points == 0)
        return NULL;

    const jintArray result = env->NewIntArray(outline->n_points);
    if(!result)
        return NULL;

    jint* elements = env->GetIntArrayElements(result, NULL);
    for(int i = 0; i < outline->n_points; i++)
        elements[i] = static_cast<jint>(outline->tags[i]);

    env->ReleaseIntArrayElements(result, elements, 0);

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setTags
  (JNIEnv *env, jclass, jlong outlinePtrRaw, jintArray tagsRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline || !tagsRaw)
        return;

    const jsize length = env->GetArrayLength(tagsRaw);
    jint* elements = env->GetIntArrayElements(tagsRaw, NULL);
    if(!elements)
        return;

    // allocate or reallocate tags array
    outline->tags = static_cast<FT_Byte*>(malloc(sizeof(FT_Byte) * length));
    if(!outline->tags) {
        env->ReleaseIntArrayElements(tagsRaw, elements, JNI_ABORT);
        return;
    }

    for(jsize i = 0; i < length; i++)
        outline->tags[i] = static_cast<FT_Byte>(elements[i]);

    outline->n_points = static_cast<short>(length);

    env->ReleaseIntArrayElements(tagsRaw, elements, JNI_ABORT);
}

// unsigned short* contours;
JNIEXPORT jintArray JNICALL Java_generaloss_freetype_image_FTOutline_getContours
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(!outline->contours || outline->n_contours == 0)
        return NULL;

    const jintArray result = env->NewIntArray(outline->n_contours);
    if(!result)
        return NULL;

    jint* elements = env->GetIntArrayElements(result, NULL);
    for(int i = 0; i < outline->n_contours; i++)
        elements[i] = static_cast<jint>(outline->contours[i]);

    env->ReleaseIntArrayElements(result, elements, 0);

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setContours
  (JNIEnv *env, jclass, jlong outlinePtrRaw, jintArray contoursRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline || !contoursRaw)
        return;

    const jsize length = env->GetArrayLength(contoursRaw);
    jint* elements = env->GetIntArrayElements(contoursRaw, NULL);
    if(!elements)
        return;

    // allocate or reallocate contours array
    outline->contours = static_cast<unsigned short*>(malloc(sizeof(short) * length));
    if(!outline->contours) {
        env->ReleaseIntArrayElements(contoursRaw, elements, JNI_ABORT);
        return;
    }

    for(jsize i = 0; i < length; i++)
        outline->contours[i] = static_cast<unsigned short>(elements[i]);

    outline->n_contours = static_cast<unsigned short>(length);

    env->ReleaseIntArrayElements(contoursRaw, elements, JNI_ABORT);
}

// int flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getFlags
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jint>(outline->flags);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutline_setFlags
  (JNIEnv *, jclass, jlong outlinePtrRaw, jint flagsRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return;

    outline->flags = static_cast<int>(flagsRaw);
}