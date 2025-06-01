#include "freetype/FTGlyphMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getWidth
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriBearingX
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriBearingY
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getHoriAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertBearingX
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertBearingY
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_getVertAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphMetrics_newStruct
  (JNIEnv *, jclass) {

    FT_Glyph_Metrics* pointer = new FT_Glyph_Metrics;
    return reinterpret_cast<jlong>(pointer);
}
