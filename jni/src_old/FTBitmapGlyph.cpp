#include "FTBitmapGlyph.h"
#include <ft2build.h>
#include FT_GLYPH_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getBitmap (JNIEnv *, jclass, jlong glyphPtr) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtr);
    if(!glyph)
        return 0;

    return reinterpret_cast<jlong>(&glyph->bitmap);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getLeft (JNIEnv *, jclass, jlong glyphPtr) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtr);
    if(!glyph)
        return 0;

    return glyph->left;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTBitmapGlyph_getTop (JNIEnv *, jclass, jlong glyphPtr) {

    const FT_BitmapGlyph glyph = reinterpret_cast<FT_BitmapGlyph>(glyphPtr);
    if(!glyph)
        return 0;

    return glyph->top;
}