#include "glyph/FTGlyph.h"
#include <ft2build.h>
#include FT_GLYPH_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_getLibrary
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyph_getFormat
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_getAdvance
  (JNIEnv *, jclass, jlong) {

    return 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_newStruct
  (JNIEnv *, jclass) {

    FT_Glyph* pointer = new FT_Glyph(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
