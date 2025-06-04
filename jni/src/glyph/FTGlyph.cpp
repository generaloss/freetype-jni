// typedef struct FT_GlyphRec_* FT_Glyph;

#include "glyph/FTGlyph.h"
#include <ft2build.h>
#include FT_GLYPH_H

// FT_Library library;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_getLibrary
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return reinterpret_cast<jlong>(glyph->library);
}

// FT_Glyph_Format format;
JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyph_getFormat
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return static_cast<jint>(glyph->format);
}

// FT_Vector advance;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_getAdvance
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return reinterpret_cast<jlong>(&glyph->advance);
}
