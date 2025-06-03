// typedef struct FT_GlyphSlotRec_*  FT_GlyphSlot;

#include "freetype/FTGlyphSlot.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Library library;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLibrary
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->library);
}

// FT_Face face;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getFace
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->face);
}

// FT_GlyphSlot next;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getNext
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->next);
}

// FT_UInt glyph_index;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getGlyphIndex
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jlong>(slot->glyph_index);
}

// FT_Glyph_Metrics metrics;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getMetrics
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->metrics);
}

// FT_Fixed linearHoriAdvance;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLinearHoriAdvance
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->linearHoriAdvance);
}

// FT_Fixed linearVertAdvance;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLinearVertAdvance
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->linearVertAdvance);
}

// FT_Vector advance;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getAdvance
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->advance);
}

// FT_Glyph_Format format;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getFormat
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->format);
}

// FT_Bitmap bitmap;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmap
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->bitmap);
}

// FT_Int bitmap_left;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmapLeft
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->bitmap_left);
}

// FT_Int bitmap_top;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getBitmapTop
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->bitmap_top);
}

// FT_Outline outline;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getOutline
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return reinterpret_cast<jlong>(&slot->outline);
}

// FT_UInt num_subglyphs;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getNumSubglyphs
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jlong>(slot->num_subglyphs);
}

// FT_SubGlyph subglyphs;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getSubglyphs
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    return NULL;
}

// FT_Pos lsb_delta;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getLsbDelta
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->lsb_delta);
}

// FT_Pos rsb_delta;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_getRsbDelta
  (JNIEnv *, jclass, jlong slotPtrRaw) {

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot)
        return 0;

    return static_cast<jint>(slot->rsb_delta);
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTGlyphSlot_newStruct
  (JNIEnv *, jclass) {

    FT_GlyphSlot* pointer = new FT_GlyphSlot(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
