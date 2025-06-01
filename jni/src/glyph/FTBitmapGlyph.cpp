#include "glyph/FTBitmapGlyph.h"
#include <ft2build.h>
#include FT_GLYPH_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getRoot
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getLeft
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getTop
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getBitmap
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_newStruct
  (JNIEnv *, jclass) {

    FT_BitmapGlyph* pointer = new FT_BitmapGlyph;
    return reinterpret_cast<jlong>(pointer);
}
