// typedef struct FT_BBox_ { ... } FT_BBox;

#include "types/FTBBox.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTBBox_createPointer
  (JNIEnv *, jclass) {

    FT_BBox* pointer = new FT_BBox();
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTBBox_freePointer
  (JNIEnv *, jclass, jlong bboxPtrRaw) {

    delete reinterpret_cast<FT_BBox*>(bboxPtrRaw);
}


// FT_Pos xMin;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTBBox_getXMinRaw
  (JNIEnv *, jclass, jlong bboxPtrRaw) {

    const FT_BBox* box = reinterpret_cast<FT_BBox*>(bboxPtrRaw);
    if(!box)
        return 0;

    return static_cast<jint>(box->xMin);
}

// FT_Pos yMin;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTBBox_getYMinRaw
  (JNIEnv *, jclass, jlong bboxPtrRaw) {

    const FT_BBox* box = reinterpret_cast<FT_BBox*>(bboxPtrRaw);
    if(!box)
        return 0;

    return static_cast<jint>(box->yMin);
}

// FT_Pos xMax;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTBBox_getXMaxRaw
  (JNIEnv *, jclass, jlong bboxPtrRaw) {

    const FT_BBox* box = reinterpret_cast<FT_BBox*>(bboxPtrRaw);
    if(!box)
        return 0;

    return static_cast<jint>(box->xMax);
}

// FT_Pos yMax;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTBBox_getYMaxRaw
  (JNIEnv *, jclass, jlong bboxPtrRaw) {

    const FT_BBox* box = reinterpret_cast<FT_BBox*>(bboxPtrRaw);
    if(!box)
        return 0;

    return static_cast<jint>(box->yMax);
}
