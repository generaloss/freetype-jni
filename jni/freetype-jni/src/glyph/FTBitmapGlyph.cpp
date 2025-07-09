// typedef struct FT_BitmapGlyphRec_* FT_BitmapGlyph;

#include "glyph/FTBitmapGlyph.h"
#include <ft2build.h>
#include FT_GLYPH_H

// FT_GlyphRec root;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getRoot
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return reinterpret_cast<jlong>(&glyph->root);
}

// FT_Int left;
JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getLeft
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return static_cast<jlong>(glyph->left);
}

// FT_Int top;
JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getTop
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return static_cast<jlong>(glyph->top);
}

// FT_Bitmap bitmap;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getBitmap
  (JNIEnv *, jclass, jlong glyphPtrRaw) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtrRaw);
    if(!glyph)
        return 0;

    return reinterpret_cast<jlong>(&glyph->bitmap);
}
