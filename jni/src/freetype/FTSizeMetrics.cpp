// typedef struct  FT_Size_Metrics_ { ... } FT_Size_Metrics;

#include "freetype/FTSizeMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_UShort x_ppem;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXppem
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->x_ppem);
}

// FT_UShort y_ppem;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYppem
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->y_ppem);
}

// FT_Fixed x_scale;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXScale
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->x_scale);
}

// FT_Fixed y_scale;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYScale
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->y_scale);
}

// FT_Pos ascender;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getAscender
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->ascender);
}

// FT_Pos descender;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getDescender
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->descender);
}

// FT_Pos height;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getHeight
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->height);
}

// FT_Pos max_advance;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getMaxAdvance
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->max_advance);
}