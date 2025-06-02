#include "freetype/FTFace.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Long num_faces;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getNumFaces
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jlong>(face->num_faces);
}

// FT_Long face_index;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getFaceIndex
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jlong>(face->face_index);
}

// FT_Long face_flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getFaceFlags
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jint>(face->face_flags);
}

// FT_Long style_flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getStyleFlags
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jint>(face->style_flags);
}

// FT_Long num_glyphs;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getNumGlyphs
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jlong>(face->num_glyphs);
}

// FT_String* family_name;
JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTFace_getFamilyName
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face || !face->family_name)
        return NULL;

    return env->NewStringUTF(face->family_name);
}

// FT_String* style_name;
JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTFace_getStyleName
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face || !face->style_name)
        return NULL;

    return env->NewStringUTF(face->style_name);
}

// FT_Int num_fixed_sizes;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getNumFixedSizes
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jint>(face->num_fixed_sizes);
}

// FT_Bitmap_Size* available_sizes;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTFace_getAvailableSizes
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face || !face->available_sizes)
        return NULL;

    const jlongArray result = env->NewLongArray(face->num_fixed_sizes);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(FT_Int i = 0; i < face->num_fixed_sizes; i++)
        elements[i] = reinterpret_cast<jlong>(&face->available_sizes[i]);

    env->ReleaseLongArrayElements(result, elements, 0);

    return result;
}

// FT_Int num_charmaps;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTFace_getNumCharmaps
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jint>(face->num_charmaps);
}

// FT_CharMap* charmaps;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTFace_getCharmaps
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face || !face->charmaps)
        return NULL;

    const jlongArray result = env->NewLongArray(face->num_charmaps);
    if(!result)
        return NULL;

    jlong* elements = env->GetLongArrayElements(result, NULL);
    for(FT_Int i = 0; i < face->num_charmaps; i++)
        elements[i] = reinterpret_cast<jlong>(face->charmaps[i]);

    env->ReleaseLongArrayElements(result, elements, 0);

    return result;
}

// FT_BBox bbox;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getBBox
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(&face->bbox);
}

// FT_UShort units_per_EM;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getUnitsPerEM
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jlong>(face->units_per_EM);
}

// FT_Short ascender;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getAscender
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->ascender);
}

// FT_Short descender;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getDescender
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->descender);
}

// FT_Short height;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getHeight
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->height);
}

// FT_Short max_advance_width;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getMaxAdvanceWidth
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->max_advance_width);
}

// FT_Short max_advance_height;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getMaxAdvanceHeight
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->max_advance_height);
}

// FT_Short underline_position;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getUnderlinePosition
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->underline_position);
}

// FT_Short underline_thickness;
JNIEXPORT jshort JNICALL Java_generaloss_freetype_freetype_FTFace_getUnderlineThickness
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return static_cast<jshort>(face->underline_thickness);
}

// FT_GlyphSlot glyph;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getGlyph
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(face->glyph);
}

// FT_Size size;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getSize
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(face->size);
}

// FT_CharMap charmap;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_getCharmap
  (JNIEnv *, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(face->charmap);
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTFace_newStruct
  (JNIEnv *, jclass) {

    FT_Face* pointer = new FT_Face;
    return reinterpret_cast<jlong>(pointer);
}