#include "freetype/FTSizeMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_UShort x_ppem;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXppem
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->x_ppem);
}

// FT_UShort y_ppem;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYppem
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->y_ppem);
}

// FT_Fixed x_scale;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getXScale
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->x_scale);
}

// FT_Fixed y_scale;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getYScale
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->y_scale);
}

// FT_Pos ascender;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getAscender
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->ascender);
}

// FT_Pos descender;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getDescender
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->descender);
}

// FT_Pos height;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getHeight
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->height);
}

// FT_Pos max_advance;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_getMaxAdvance
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    FT_Size_Metrics* metrics = reinterpret_cast<FT_Size_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return reinterpret_cast<jlong>(&metrics->max_advance);
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSizeMetrics_newStruct
  (JNIEnv *, jclass) {

    FT_Size_Metrics* pointer = new FT_Size_Metrics;
    return reinterpret_cast<jlong>(pointer);
}
