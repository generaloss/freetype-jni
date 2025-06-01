#include "freetype/FTSizeMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXppem
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYppem
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXScale
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYScale
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getAscender
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getDescender
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getMaxAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_newStruct
  (JNIEnv *, jclass) {

    FT_Size_Metrics* pointer = new FT_Size_Metrics;
    return reinterpret_cast<jlong>(pointer);
}
