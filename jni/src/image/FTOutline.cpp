// typedef struct FT_Outline_ { ... } FT_Outline;

#include "image/FTOutline.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <jni.h>

// unsigned short n_contours;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTOutline_getNContours
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jshort>(outline->n_contours);
}

// unsigned short n_points;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_image_FTOutline_getNPoints
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jshort>(outline->n_points);
}

// FT_Vector* points;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_image_FTOutline_getPoints
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(!outline->points || outline->n_points == 0)
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

// unsigned char* tags;
JNIEXPORT jshortArray JNICALL Java_generaloss_freetype_image_FTOutline_getTags
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(!outline->tags || outline->n_points == 0)
        return NULL;

    const jshortArray result = env->NewShortArray(outline->n_points);
    if(!result)
        return NULL;

    jshort* elements = env->GetShortArrayElements(result, NULL);
    for(int i = 0; i < outline->n_points; i++)
        elements[i] = static_cast<jshort>(outline->tags[i]);

    env->ReleaseShortArrayElements(result, elements, 0);

    return result;
}

// unsigned short* contours;
JNIEXPORT jshortArray JNICALL Java_generaloss_freetype_image_FTOutline_getContours
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return NULL;

    if(!outline->contours || outline->n_contours == 0)
        return NULL;

    const jshortArray result = env->NewShortArray(outline->n_contours);
    if(!result)
        return NULL;

    jshort* elements = env->GetShortArrayElements(result, NULL);
    for(int i = 0; i < outline->n_contours; i++)
        elements[i] = static_cast<jshort>(outline->contours[i]);

    env->ReleaseShortArrayElements(result, elements, 0);

    return result;
}

// int flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutline_getFlags
  (JNIEnv *, jclass, jlong outlinePtrRaw) {

    const FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline)
        return 0;

    return static_cast<jint>(outline->flags);
}