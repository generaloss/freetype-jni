#include "FTGlyph.h"
#include <ft2build.h>
#include FT_STROKER_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_strokeBorder (
        JNIEnv *, jclass, jlong glyphPtr, jlong strokerPtr, jboolean inside) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtr);
    if(!glyph)
        return 0;

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtr);
    if(!stroker)
        return 0;

    FT_Glyph newGlyph;
    if(FT_Glyph_Copy(glyph, &newGlyph) != 0)
        return 0;

    if(FT_Glyph_StrokeBorder(&newGlyph, stroker, inside, 1) != 0){
        FT_Done_Glyph(newGlyph);
        return 0;
    }

    return reinterpret_cast<jlong>(newGlyph);
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyph_toBitmap (JNIEnv *, jclass, jlong glyphPtr, jint mode) {

    FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtr);
    if(!glyph)
        return 0;

    if(FT_Glyph_To_Bitmap(&glyph, static_cast<FT_Render_Mode>(mode), nullptr, 1) != 0)
        return 0;

    return reinterpret_cast<jlong>(glyph);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_glyph_FTGlyph_done (JNIEnv *, jclass, jlong glyphPtr) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtr);
    if(!glyph)
        return;

    FT_Done_Glyph(glyph);
}
