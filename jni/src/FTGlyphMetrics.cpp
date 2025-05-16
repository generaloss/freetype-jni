#include "FTGlyphMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getWidth (JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return metrics->width;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getHeight (JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return metrics->height;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getHoriBearingX (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return metrics->horiBearingX;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getHoriBearingY (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return metrics->horiBearingY;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getHoriAdvance (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return metrics->horiAdvance;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getVertBearingX (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertBearingX);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getVertBearingY (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertBearingY);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphMetrics_getVertAdvance (
        JNIEnv *, jclass, jlong metricsPtr) {

    const FT_Glyph_Metrics* metrics = reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr);
    if(!metrics)
        return 0;

    return static_cast<jint>(metrics->vertAdvance);
}
