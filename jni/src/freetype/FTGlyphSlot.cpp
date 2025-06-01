#include "freetype/FTGlyphSlot.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLibrary
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getFace
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getNext
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getGlyphIndex
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getMetrics
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLinearHoriAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLinearVertAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getFormat
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmap
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmapLeft
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmapTop
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getOutline
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getNumSubglyphs
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getSubglyphs
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLsbDelta
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getRsbDelta
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_newStruct
  (JNIEnv *, jclass) {

    FT_GlyphSlot* pointer = new FT_GlyphSlot;
    return reinterpret_cast<jlong>(pointer);
}
