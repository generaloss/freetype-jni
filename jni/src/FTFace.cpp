#include "FTFace.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getFaceFlags (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->face_flags;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getStyleFlags (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->style_flags;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getNumGlyphs (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->num_glyphs;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getAscender (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->ascender;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getDescender (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->descender;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getHeight (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->height;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getMaxAdvanceWidth (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->max_advance_width;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getMaxAdvanceHeight (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->max_advance_height;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getUnderlinePosition (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->underline_position;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getUnderlineThickness (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return face->underline_thickness;
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_face_FTFace_getGlyph (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(face->glyph);
}

JNIEXPORT jlong JNICALL Java_generaloss_freetype_face_FTFace_getSize (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return reinterpret_cast<jlong>(face->size);
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_selectCharmap(
        JNIEnv *, jclass, jlong facePtr, jint encoding) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return FT_Select_Charmap(face, static_cast<FT_Encoding>(encoding)) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_selectSize (
        JNIEnv *, jclass, jlong facePtr, jint strikeIndex) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return FT_Select_Size(face, strikeIndex) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_setCharSize (
        JNIEnv *, jclass, jlong facePtr, jint charWidth, jint charHeight, jint horzRes, jint vertRes) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return FT_Set_Char_Size(face, charWidth, charHeight, horzRes, vertRes) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_setPixelSizes (
        JNIEnv *, jclass, jlong facePtr, jint width, jint height) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return FT_Set_Pixel_Sizes(face, width, height) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_loadGlyph (
        JNIEnv *, jclass, jlong facePtr, jint glyphIndex, jint loadFlags) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return FT_Load_Glyph(face, glyphIndex, loadFlags) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_loadChar (
        JNIEnv *, jclass, jlong facePtr, jint charCode, jint loadFlags) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face || !face->charmap)
        return false;

    return FT_Load_Char(face, charCode, loadFlags) == 0;
}

JNIEXPORT jboolean JNICALL Java_generaloss_freetype_face_FTFace_hasKerning (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return false;

    return (face->face_flags & FT_FACE_FLAG_KERNING) != 0;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getKerning (
        JNIEnv *env, jclass, jlong facePtr, jint leftGlyph, jint rightGlyph, jint kernMode) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    FT_Vector kerning;
    if(FT_Get_Kerning(face, leftGlyph, rightGlyph, kernMode, &kerning) != 0)
        return 0;

    return kerning.x;
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_face_FTFace_getCharIndex (
        JNIEnv *, jclass, jlong facePtr, jint charCode) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return 0;

    return FT_Get_Char_Index(face, charCode);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_face_FTFace_doneFace (JNIEnv *, jclass, jlong facePtr) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtr);
    if(!face)
        return;

    FT_Done_Face(face);
}
