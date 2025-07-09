// typedef struct FT_Glyph_Metrics_ { ... } FT_Glyph_Metrics;

#include "freetype/FTGlyphMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Pos width;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getWidth
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->width);
}

// FT_Pos height;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHeight
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->height);
}

// FT_Pos horiBearingX;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriBearingX
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->horiBearingX);
}

// FT_Pos horiBearingY;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriBearingY
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->horiBearingY);
}

// FT_Pos horiAdvance;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriAdvance
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->horiAdvance);
}

// FT_Pos vertBearingX;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertBearingX
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertBearingX);
}

// FT_Pos vertBearingY;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertBearingY
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertBearingY);
}

// FT_Pos vertAdvance;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertAdvance
  (JNIEnv *, jclass, jlong metricsPtrRaw) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtrRaw);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertAdvance);
}