#include "freetype/FTFace.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getNumFaces
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getFaceIndex
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getFaceFlags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getStyleFlags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getNumGlyphs
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTFace_getFamilyName
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTFace_getStyleName
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getNumFixedSizes
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTFace_getAvailableSizes
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getNumCharmaps
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTFace_getCharmaps
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getBBox
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getUnitsPerEM
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getAscender
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getDescender
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getMaxAdvanceWidth
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getMaxAdvanceHeight
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getUnderlinePosition
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getUnderlineThickness
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getGlyph
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getSize
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getCharmap
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_newStruct
  (JNIEnv *, jclass) {

    FT_Face* pointer = new FT_Face;
    return reinterpret_cast<jlong>(pointer);
}