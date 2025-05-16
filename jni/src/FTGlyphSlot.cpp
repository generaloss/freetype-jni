#include "FTGlyphSlot.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_GLYPH_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getMetrics (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->metrics);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getLinearHoriAdvance (
        JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->linearHoriAdvance;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getLinearVertAdvance (
        JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->linearVertAdvance;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getAdvanceX (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->advance.x;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getAdvanceY (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->advance.y;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getFormat (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->format;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getBitmap (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->bitmap);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getBitmapLeft (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->bitmap_left;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getBitmapTop (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    return slot->bitmap_top;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_renderGlyph (
        JNIEnv *, jclass, jlong slotPtr, jint mode) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return false;

    return FT_Render_Glyph(slot, static_cast<FT_Render_Mode>(mode)) == 0;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_glyph_FTGlyphSlot_getGlyph (JNIEnv *, jclass, jlong slotPtr) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtr);
    if(!slot)
        return 0;

    FT_Glyph glyph;
    if(FT_Get_Glyph(slot, &glyph) == 0)
        return reinterpret_cast<jlong>(glyph);
    return 0;
}
