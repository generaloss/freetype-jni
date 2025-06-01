#include "FreeType.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_GLYPH_H
#include FT_OUTLINE_H
#include FT_STROKER_H
#include <jni.h>


void throwException(JNIEnv* env, const char* message) {
    const jclass exceptionClass = env->FindClass("generaloss/freetype/FTException");
    env->ThrowNew(exceptionClass, message);
}


// ------------------------------
// ---      freetype.h        ---
// ------------------------------


// FT_Error FT_Init_FreeType(FT_Library *alibrary)
// int FT_Init_FreeType(long alibrary);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Init_1FreeType
  (JNIEnv *env, jclass, jlong libraryPtrRaw) {

    FT_Library* libraryPtr = reinterpret_cast<FT_Library*>(libraryPtrRaw);
    if(!libraryPtr) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Error error = FT_Init_FreeType(libraryPtr);
    return static_cast<jint>(error);
}

// FT_Error FT_Done_FreeType(FT_Library library)
// int FT_Done_FreeType(long library);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Done_1FreeType
  (JNIEnv *env, jclass, jlong libraryPtrRaw) {

    FT_Library libraryPtr = *reinterpret_cast<FT_Library*>(libraryPtrRaw);
    if(!libraryPtr) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Error error = FT_Done_FreeType(libraryPtr);
    return static_cast<jint>(error);
}

// FT_Error FT_New_Face(FT_Library library, const char* filepathname, FT_Long face_index, FT_Face *aface)
// int FT_New_Face(long library, String filepathname, long face_index, long aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jstring filepathRaw, jlong faceIndexRaw, jlong facePtrRaw) {

    const FT_Library libraryPtr = *reinterpret_cast<FT_Library*>(libraryPtrRaw);
    if(!libraryPtr) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const char* filepath = env->GetStringUTFChars(filepathRaw, NULL);
    if(!filepath) {
        throwException(env, "Invalid filepath");
        return 0;
    }

    FT_Face* facePtr = reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!facePtr) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Long faceIndex = static_cast<FT_Long>(faceIndexRaw);

    const FT_Error error = FT_New_Face(libraryPtr, filepath, faceIndex, facePtr);

    env->ReleaseStringUTFChars(filepathRaw, filepath);

    return static_cast<jint>(error);
}

// FT_Error FT_New_Memory_Face(FT_Library library, const FT_Byte* file_base, FT_Long file_size, FT_Long face_index, FT_Face *aface)
// int FT_New_Memory_Face(long library, ByteBuffer file_base, long file_size, long face_index, long aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Memory_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jobject fileBaseBuffer, jlong fileSizeRaw, jlong faceIndexRaw, jlong facePtrRaw) {

    const FT_Library libraryPtr = *reinterpret_cast<FT_Library*>(libraryPtrRaw);
    if(!libraryPtr) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Byte* fileBase = reinterpret_cast<const FT_Byte*>(env->GetDirectBufferAddress(fileBaseBuffer));
    if(!fileBase) {
        throwException(env, "Invalid direct buffer");
        return 0;
    }

    FT_Face* facePtr = reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!facePtr) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Long fileSize = static_cast<FT_Long>(fileSizeRaw);
    const FT_Long faceIndex = static_cast<FT_Long>(faceIndexRaw);

    const FT_Error error = FT_New_Memory_Face(libraryPtr, fileBase, fileSize, faceIndex, facePtr);
    return static_cast<jint>(error);
}

// FT_Error FT_Open_Face(FT_Library library, const FT_Open_Args* args, FT_Long face_index, FT_Face *aface)
// int FT_Open_Face(long library, long args, long face_index, long aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Open_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jlong argsPtrRaw, jlong faceIndexRaw, jlong facePtrRaw) {

    const FT_Library library = *reinterpret_cast<FT_Library*>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Open_Args* argsPtr = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!argsPtr) {
        throwException(env, "Invalid FT_Open_Args pointer");
        return 0;
    }

    const FT_Long faceIndex = static_cast<FT_Long>(faceIndexRaw);

    FT_Face* facePtr = reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!facePtr) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Error error = FT_Open_Face(library, argsPtr, faceIndex, facePtr);
    return static_cast<jint>(error);
}

// FT_Error FT_Attach_File(FT_Face face, const char* filepathname)
// int FT_Attach_File(long face, String filepathname);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Attach_1File
  (JNIEnv *env, jclass, jlong facePtrRaw, jstring filepathRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const char* filepath = env->GetStringUTFChars(filepathRaw, NULL);
    if(!filepath) {
        throwException(env, "Invalid filepath");
        return 0;
    }

    const FT_Error error = FT_Attach_File(face, filepath);

    env->ReleaseStringUTFChars(filepathRaw, filepath);

    return static_cast<jint>(error);;
}

// FT_Error FT_Attach_Stream(FT_Face face, const FT_Open_Args* parameters)
// int FT_Attach_Stream(long face, long parameters);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Attach_1Stream
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong argsPtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Open_Args* argsPtr = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!argsPtr) {
        throwException(env, "Invalid FT_Open_Args pointer");
        return 0;
    }

    const FT_Error error = FT_Attach_Stream(face, argsPtr);
    return static_cast<jint>(error);
}

// FT_Error FT_Reference_Face(FT_Face face)
// int FT_Reference_Face(long face);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Reference_1Face
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Error error = FT_Reference_Face(face);
    return static_cast<jint>(error);
}

// FT_Error FT_Done_Face(FT_Face face)
// int FT_Done_Face(long face);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Done_1Face
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Error error = FT_Done_Face(face);
    return static_cast<jint>(error);
}

// FT_Error FT_Select_Size(FT_Face face, FT_Int strike_index)
// int FT_Select_Size(long face, int strike_index);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Select_1Size
  (JNIEnv *env, jclass, jlong facePtrRaw, jint strikeIndexRaw) {

   const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
   if(!face) {
       throwException(env, "Invalid FT_Face pointer");
       return 0;
   }

   const FT_Int strikeIndex = static_cast<FT_Int>(strikeIndexRaw);

   const FT_Error error = FT_Select_Size(face, strikeIndex);
   return static_cast<jint>(error);
}

// FT_Error FT_Request_Size(FT_Face face, FT_Size_Request req)
// int FT_Request_Size(long face, long req);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Request_1Size
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong reqPtrRaw) {

   const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
   if(!face) {
       throwException(env, "Invalid FT_Face pointer");
       return 0;
   }

   const FT_Size_Request req = *reinterpret_cast<FT_Size_Request*>(reqPtrRaw);
   if(!req) {
       throwException(env, "Invalid FT_Size_Request pointer");
       return 0;
   }

   const FT_Error error = FT_Request_Size(face, req);
   return static_cast<jint>(error);
}

// FT_Error FT_Set_Char_Size(FT_Face face, FT_F26Dot6 char_width, FT_F26Dot6 char_height, FT_UInt horz_resolution, FT_UInt vert_resolution)
// int FT_Set_Char_Size(long face, int char_width, int char_height, long horz_resolution, long vert_resolution);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Set_1Char_1Size
  (JNIEnv *env, jclass, jlong facePtrRaw, jint charWidthRaw, jint charHeightRaw, jlong horzResRaw, jlong vertResRaw) {

   const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
   if(!face) {
       throwException(env, "Invalid FT_Face pointer");
       return 0;
   }

   const FT_F26Dot6 charWidth = static_cast<FT_F26Dot6>(charWidthRaw);
   const FT_F26Dot6 charHeight = static_cast<FT_F26Dot6>(charHeightRaw);
   const FT_UInt horzRes = static_cast<FT_UInt>(horzResRaw);
   const FT_UInt vertRes = static_cast<FT_UInt>(vertResRaw);

   const FT_Error error = FT_Set_Char_Size(face, charWidth, charHeight, horzRes, vertRes);
   return static_cast<jint>(error);
}

// FT_Error FT_Set_Pixel_Sizes(FT_Face face, FT_UInt pixel_width, FT_UInt pixel_height)
// int FT_Set_Pixel_Sizes(long face, long pixel_width, long pixel_height);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Set_1Pixel_1Sizes
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong pixelWidthRaw, jlong pixelHeightRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt pixelWidth = static_cast<FT_UInt>(pixelWidthRaw);
    const FT_UInt pixelHeight = static_cast<FT_UInt>(pixelHeightRaw);

    const FT_Error error = FT_Set_Pixel_Sizes(face, pixelWidth, pixelHeight);
    return static_cast<jint>(error);
}

// FT_Error FT_Load_Glyph(FT_Face face, FT_UInt glyph_index, FT_Int32 load_flags)
// int FT_Load_Glyph(long face, long glyph_index, int load_flags);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Load_1Glyph
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong glyphIndexRaw, jint loadFlagsRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt glyphIndex = static_cast<FT_UInt>(glyphIndexRaw);
    const FT_Int32 loadFlags = static_cast<FT_ULong>(loadFlagsRaw);

    const FT_Error error = FT_Load_Glyph(face, glyphIndex, loadFlags);
    return static_cast<jint>(error);
}

// FT_Error FT_Load_Char(FT_Face face, FT_ULong char_code, FT_Int32 load_flags)
// int FT_Load_Char(long face, long char_code, int load_flags);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Load_1Char
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw, jint loadFlagsRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong charCode = static_cast<FT_ULong>(charCodeRaw);
    const FT_Int32 loadFlags = static_cast<FT_ULong>(loadFlagsRaw);

    const FT_Error error = FT_Load_Char(face, charCode, loadFlags);
    return static_cast<jint>(error);
}

// void FT_Set_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
// void FT_Set_Transform(long face, long matrix, long delta);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Set_1Transform
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong matrixPtrRaw, jlong deltaPtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return;
    }

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    FT_Vector* delta = reinterpret_cast<FT_Vector*>(deltaPtrRaw);

    FT_Set_Transform(face, matrix, delta);
}

// void FT_Get_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
// void FT_Get_Transform(long face, long matrix, long delta);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Transform
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong matrixPtrRaw, jlong deltaPtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return;
    }

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    FT_Vector* delta = reinterpret_cast<FT_Vector*>(deltaPtrRaw);

    FT_Get_Transform(face, matrix, delta);
}

// FT_Error FT_Render_Glyph(FT_GlyphSlot slot, FT_Render_Mode render_mode)
// int FT_Render_Glyph(long slot, int render_mode);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Render_1Glyph
  (JNIEnv *env, jclass, jlong slotPtrRaw, jint renderModeRaw) {

    const FT_GlyphSlot slot = *reinterpret_cast<FT_GlyphSlot*>(slotPtrRaw);
    if(!slot) {
        throwException(env, "Invalid FT_GlyphSlot pointer");
        return 0;
    }

    const FT_Render_Mode renderMode = static_cast<FT_Render_Mode>(renderModeRaw);

    const FT_Error error = FT_Render_Glyph(slot, renderMode);
    return static_cast<jint>(error);
}

// FT_Error FT_Get_Kerning(FT_Face face, FT_UInt left_glyph, FT_UInt right_glyph, FT_UInt kern_mode, FT_Vector *akerning)
// int FT_Get_Kerning(long face, long left_glyph, long right_glyph, int kern_mode, long akerning);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Kerning
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong leftGlyphRaw, jlong rightGlyphRaw, jint kernModeRaw, jlong kerningPtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    FT_Vector* kerning = reinterpret_cast<FT_Vector*>(kerningPtrRaw);
    if(!kerning) {
        throwException(env, "Invalid FT_Vector pointer");
        return 0;
    }

    const FT_UInt leftGlyph = static_cast<FT_UInt>(leftGlyphRaw);
    const FT_UInt rightGlyph = static_cast<FT_UInt>(rightGlyphRaw);
    const FT_UInt kernMode = static_cast<FT_UInt>(kernModeRaw);

    const FT_Error error = FT_Get_Kerning(face, leftGlyph, rightGlyph, kernMode, kerning);
    return static_cast<jint>(error);
}

// FT_Error FT_Get_Track_Kerning(FT_Face face, FT_Fixed point_size, FT_Int degree, FT_Fixed* akerning)
// int FT_Get_Track_Kerning(long face, int point_size, int degree, long akerning);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Track_1Kerning
  (JNIEnv *env, jclass, jlong facePtrRaw, jint pointSizeRaw, jint degreeRaw, jlong kerningPtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    FT_Fixed* kerning = reinterpret_cast<FT_Fixed*>(kerningPtrRaw);
    if(!kerning) {
        throwException(env, "Invalid FT_Fixed pointer");
        return 0;
    }

    const FT_Fixed pointSize = static_cast<FT_Fixed>(pointSizeRaw);
    const FT_Int degree = static_cast<FT_Int>(degreeRaw);

    const FT_Error error = FT_Get_Track_Kerning(face, pointSize, degree, kerning);
    return static_cast<jint>(error);
}

// FT_Error FT_Select_Charmap(FT_Face face, FT_Encoding encoding)
// int FT_Select_Charmap(long face, int encoding);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Select_1Charmap
  (JNIEnv *env, jclass, jlong facePtrRaw, jint encodingRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Encoding encoding = static_cast<FT_Encoding>(encodingRaw);

    const FT_Error error = FT_Select_Charmap(face, encoding);
    return static_cast<jint>(error);
}

// FT_Error FT_Set_Charmap(FT_Face face, FT_CharMap charmap)
// int FT_Set_Charmap(long face, long charmap);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Set_1Charmap
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charmapPtrRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_CharMap charmap = *reinterpret_cast<FT_CharMap*>(charmapPtrRaw);
    if(!charmap) {
        throwException(env, "Invalid FT_CharMap pointer");
        return 0;
    }

    const FT_Error error = FT_Set_Charmap(face, charmap);
    return static_cast<jint>(error);
}

// FT_Int FT_Get_Charmap_Index(FT_CharMap charmap)
// int FT_Get_Charmap_Index(long charmap);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Charmap_1Index
  (JNIEnv *env, jclass, jlong charmapPtrRaw) {

    const FT_CharMap charmap = *reinterpret_cast<FT_CharMap*>(charmapPtrRaw);
    if(!charmap) {
        throwException(env, "Invalid FT_CharMap pointer");
        return 0;
    }

    const FT_Int index = FT_Get_Charmap_Index(charmap);
    return static_cast<jint>(index);
}

// FT_UInt FT_Get_Char_Index(FT_Face face, FT_ULong charcode)
// long FT_Get_Char_Index(long face, long charcode);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Char_1Index
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong charCode = static_cast<FT_ULong>(charCodeRaw);

    const FT_UInt index = FT_Get_Char_Index(face, charCode);
    return static_cast<jlong>(index);
}

// FT_ULong FT_Get_First_Char(FT_Face face, FT_UInt *agindex)
// long FT_Get_First_Char(long face, long[] agindex);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1First_1Char
  (JNIEnv *env, jclass, jlong facePtrRaw, jlongArray gindexArrayRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    jlong* gindexArray = env->GetLongArrayElements(gindexArrayRaw, NULL);
    if(!gindexArray) {
        throwException(env, "Invalid gindex array");
        return 0;
    }

    FT_UInt agindex = 0;
    const FT_ULong charcode = FT_Get_First_Char(face, &agindex);
    gindexArray[0] = static_cast<jlong>(agindex);

    env->ReleaseLongArrayElements(gindexArrayRaw, gindexArray, 0);

    return static_cast<jlong>(charcode);
}

// FT_ULong FT_Get_Next_Char(FT_Face face, FT_ULong char_code, FT_UInt *agindex)
// long FT_Get_Next_Char(long face, long char_code, long[] agindex);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Next_1Char
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw, jlongArray gindexArrayRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    jlong* gindexArray = env->GetLongArrayElements(gindexArrayRaw, NULL);
    if(!gindexArray) {
        throwException(env, "Invalid gindex array");
        return 0;
    }

    FT_UInt agindex = 0;
    const FT_ULong nextCharcode = FT_Get_Next_Char(face, static_cast<FT_ULong>(charCodeRaw), &agindex);
    gindexArray[0] = static_cast<jlong>(agindex);

    env->ReleaseLongArrayElements(gindexArrayRaw, gindexArray, 0);

    return static_cast<jlong>(nextCharcode);
}

// FT_Error FT_Face_Properties(FT_Face face, FT_UInt num_properties, FT_Parameter* properties)
// int FT_Face_Properties(long face, long num_properties, long[] properties);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1Properties
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong numPropertiesRaw, jlongArray propertiesArrayRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt numProperties = static_cast<FT_UInt>(numPropertiesRaw);
    jlong* propertiesArray = env->GetLongArrayElements(propertiesArrayRaw, NULL);
    if(!propertiesArray) {
        throwException(env, "Invalid properties array");
        return 0;
    }

    FT_Parameter* properties = reinterpret_cast<FT_Parameter*>(propertiesArray);
    const FT_Error error = FT_Face_Properties(face, numProperties, properties);

    env->ReleaseLongArrayElements(propertiesArrayRaw, propertiesArray, 0);

    return static_cast<jint>(error);
}

// FT_UInt FT_Get_Name_Index(FT_Face face, const FT_String* glyph_name)
// long FT_Get_Name_Index(long face, String glyph_name);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Name_1Index
  (JNIEnv *env, jclass, jlong facePtrRaw, jstring glyphNameRaw) {

    const FT_Face face = *reinterpret_cast<FT_Face*>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const char* glyphName = env->GetStringUTFChars(glyphNameRaw, NULL);
    if(!glyphName) {
        throwException(env, "Invalid glyph name");
        return 0;
    }

    const FT_UInt index = FT_Get_Name_Index(face, glyphName);

    env->ReleaseStringUTFChars(glyphNameRaw, glyphName);

    return static_cast<jlong>(index);
}

// FT_Error FT_Get_Glyph_Name(FT_Face face, FT_UInt glyph_index, FT_Pointer buffer, FT_UInt buffer_max)
// int FT_Get_Glyph_Name(long face, long glyph_index, ByteBuffer buffer, long buffer_max);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Glyph_1Name
  (JNIEnv *, jclass, jlong, jlong, jobject, jlong) {

    return 0;
}

// const char* FT_Get_Postscript_Name(FT_Face face)
// String FT_Get_Postscript_Name(long face);
JNIEXPORT jstring JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Postscript_1Name
  (JNIEnv *, jclass, jlong) {

    return NULL;
}

// FT_Error FT_Get_SubGlyph_Info(FT_GlyphSlot glyph, FT_UInt sub_index, FT_Int *p_index, FT_UInt *p_flags, FT_Int *p_arg1, FT_Int *p_arg2, FT_Matrix *p_transform)
// int FT_Get_SubGlyph_Info(long glyph, long sub_index, int[] p_index, long[] p_flags, int[] p_arg1, int[] p_arg2, long p_transform);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1SubGlyph_1Info
  (JNIEnv *, jclass, jlong, jlong, jintArray, jlongArray, jintArray, jintArray, jlong) {

    return 0;
}

// FT_UShort FT_Get_FSType_Flags(FT_Face face)
// int FT_Get_FSType_Flags(long face);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1FSType_1Flags
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_UInt FT_Face_GetCharVariantIndex(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
// long FT_Face_GetCharVariantIndex(long face, long charcode, long variantSelector);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharVariantIndex
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// FT_Int FT_Face_GetCharVariantIsDefault(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
// int FT_Face_GetCharVariantIsDefault(long face, long charcode, long variantSelector);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharVariantIsDefault
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// FT_UInt32* FT_Face_GetVariantSelectors(FT_Face face)
// int FT_Face_GetVariantSelectors(long face);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetVariantSelectors
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_UInt32* FT_Face_GetVariantsOfChar(FT_Face face, FT_ULong charcode)
// int FT_Face_GetVariantsOfChar(long face, long charcode);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetVariantsOfChar
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_UInt32* FT_Face_GetCharsOfVariant(FT_Face face, FT_ULong variantSelector)
// int FT_Face_GetCharsOfVariant(long face, long variantSelector);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharsOfVariant
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Long FT_MulDiv(FT_Long a, FT_Long b, FT_Long c)
// long FT_MulDiv(long a, long b, long c);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1MulDiv
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// FT_Long FT_MulFix(FT_Long a, FT_Long b)
// long FT_MulFix(long a, long b);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1MulFix
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Long FT_DivFix(FT_Long a, FT_Long b)
// long FT_DivFix(long a, long b);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1DivFix
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Fixed FT_RoundFix(FT_Fixed a)
// long FT_RoundFix(long a);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1RoundFix
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_Fixed FT_CeilFix(FT_Fixed a)
// long FT_CeilFix(long a);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1CeilFix
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_Fixed FT_FloorFix(FT_Fixed a)
// long FT_FloorFix(long a);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1FloorFix
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// void FT_Vector_Transform(FT_Vector* vector, const FT_Matrix* matrix)
// void FT_Vector_Transform(long vector, long matrix);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Vector_1Transform
  (JNIEnv *, jclass, jlong, jlong) {


}

// void FT_Library_Version(FT_Library library, FT_Int *amajor, FT_Int *aminor, FT_Int *apatch)
// void FT_Library_Version(long library, int[] amajor, int[] aminor, int[] apatch);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Library_1Version
  (JNIEnv *, jclass, jlong, jintArray, jintArray, jintArray) {


}

// FT_Bool FT_Face_CheckTrueTypePatents(FT_Face face)
// boolean FT_Face_CheckTrueTypePatents(long face);
JNIEXPORT jboolean JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1CheckTrueTypePatents
  (JNIEnv *, jclass, jlong) {

    return false;
}

// FT_Bool FT_Face_SetUnpatentedHinting(FT_Face face, FT_Bool value)
// boolean FT_Face_SetUnpatentedHinting(long face, boolean value);
JNIEXPORT jboolean JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1SetUnpatentedHinting
  (JNIEnv *, jclass, jlong, jboolean) {

    return false;
}


// ------------------------------
// ---      ftgloadr.h        ---
// ------------------------------


// FT_Error FT_GlyphLoader_New(FT_Memory memory, FT_GlyphLoader *aloader)
// int FT_GlyphLoader_New(long memory, long aloader);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1New
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Error FT_GlyphLoader_CreateExtra(FT_GlyphLoader loader)
// int FT_GlyphLoader_CreateExtra(long loader);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CreateExtra
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// void FT_GlyphLoader_Done(FT_GlyphLoader loader)
// void FT_GlyphLoader_Done(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Done
  (JNIEnv *, jclass, jlong) {


}

// void FT_GlyphLoader_Reset(FT_GlyphLoader loader)
// void FT_GlyphLoader_Reset(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Reset
  (JNIEnv *, jclass, jlong) {


}

// void FT_GlyphLoader_Rewind(FT_GlyphLoader loader)
// void FT_GlyphLoader_Rewind(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Rewind
  (JNIEnv *, jclass, jlong) {


}

// FT_Error FT_GlyphLoader_CheckPoints(FT_GlyphLoader loader, FT_UInt n_points, FT_UInt n_contours)
// int FT_GlyphLoader_CheckPoints(long loader, long n_points, long n_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CheckPoints
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// FT_Error FT_GlyphLoader_CheckSubGlyphs(FT_GlyphLoader loader, FT_UInt n_subs)
// int FT_GlyphLoader_CheckSubGlyphs(long loader, long n_subs);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CheckSubGlyphs
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// void FT_GlyphLoader_Prepare(FT_GlyphLoader loader)
// void FT_GlyphLoader_Prepare(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Prepare
  (JNIEnv *, jclass, jlong) {


}

// void FT_GlyphLoader_Add(FT_GlyphLoader loader)
// void FT_GlyphLoader_Add(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Add
  (JNIEnv *, jclass, jlong) {


}


// ------------------------------
// ---       ftglyph.h        ---
// ------------------------------


// FT_Error FT_New_Glyph(FT_Library library, FT_Glyph_Format format, FT_Glyph *aglyph)
// int FT_New_Glyph(long library, int format, long aglyph);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Glyph
  (JNIEnv *, jclass, jlong, jint, jlong) {

    return 0;
}

// FT_Error FT_Get_Glyph(FT_GlyphSlot slot, FT_Glyph *aglyph)
// int FT_Get_Glyph(long slot, long aglyph);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Glyph
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Error FT_Glyph_Copy(FT_Glyph source, FT_Glyph *target)
// int FT_Glyph_Copy(long source, long target);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Copy
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Error FT_Glyph_Transform(FT_Glyph glyph, const FT_Matrix* matrix, const FT_Vector* delta)
// int FT_Glyph_Transform(long glyph, long matrix, long delta);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Transform
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// void FT_Glyph_Get_CBox(FT_Glyph glyph, FT_UInt bbox_mode, FT_BBox *acbox)
// void FT_Glyph_Get_CBox(long glyph, long bboxMode, long acbox);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Get_1CBox
  (JNIEnv *, jclass, jlong, jlong, jlong) {


}

// FT_Error FT_Glyph_To_Bitmap(FT_Glyph* the_glyph, FT_Render_Mode render_mode, const FT_Vector* origin, FT_Bool destroy)
// int FT_Glyph_To_Bitmap(long the_glyph, int render_mode, long origin, boolean destroy, long[] dstBitmapGlyphPointer);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1To_1Bitmap
  (JNIEnv *, jclass, jlong, jint, jlong, jboolean, jlongArray) {

    return 0;
}

// void FT_Done_Glyph(FT_Glyph glyph)
// void FT_Done_Glyph(long glyph);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Done_1Glyph
  (JNIEnv *, jclass, jlong) {


}

// void FT_Matrix_Multiply(const FT_Matrix* a, FT_Matrix* b)
// void FT_Matrix_Multiply(long a, long b);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Matrix_1Multiply
  (JNIEnv *, jclass, jlong, jlong) {


}

// FT_Error FT_Matrix_Invert(FT_Matrix* matrix)
// int FT_Matrix_Invert(long matrix);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Matrix_1Invert
  (JNIEnv *, jclass, jlong) {

    return 0;
}


// ------------------------------
// ---      ftstroke.h        ---
// ------------------------------


// FT_StrokerBorder FT_Outline_GetInsideBorder(FT_Outline* outline)
// int FT_Outline_GetInsideBorder(long outline);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1GetInsideBorder
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_StrokerBorder FT_Outline_GetOutsideBorder(FT_Outline* outline)
// int FT_Outline_GetOutsideBorder(long outline);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1GetOutsideBorder
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_Error FT_Stroker_New(FT_Library library, FT_Stroker *astroker)
// int FT_Stroker_New(long library, long dstStroker);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1New
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// void FT_Stroker_Set(FT_Stroker stroker, FT_Fixed radius, FT_Stroker_LineCap line_cap, FT_Stroker_LineJoin line_join, FT_Fixed miter_limit)
// void FT_Stroker_Set(long stroker, int radius, int line_cap, int line_join, int miter_limit);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Set
  (JNIEnv *, jclass, jlong, jint, jint, jint, jint) {


}

// void FT_Stroker_Rewind(FT_Stroker stroker)
// void FT_Stroker_Rewind(long stroker);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Rewind
  (JNIEnv *, jclass, jlong) {


}

// FT_Error FT_Stroker_ParseOutline(FT_Stroker stroker, FT_Outline* outline, FT_Bool opened)
// int FT_Stroker_ParseOutline(long stroker, long outline, boolean opened);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ParseOutline
  (JNIEnv *, jclass, jlong, jlong, jboolean) {

    return 0;
}

// FT_Error FT_Stroker_BeginSubPath(FT_Stroker stroker, FT_Vector* to, FT_Bool open)
// int FT_Stroker_BeginSubPath(long stroker, long to, boolean open);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1BeginSubPath
  (JNIEnv *, jclass, jlong, jlong, jboolean) {

    return 0;
}

// FT_Error FT_Stroker_EndSubPath(FT_Stroker stroker)
// int FT_Stroker_EndSubPath(long stroker);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1EndSubPath
  (JNIEnv *, jclass, jlong) {

    return 0;
}

// FT_Error FT_Stroker_LineTo(FT_Stroker stroker, FT_Vector* to)
// int FT_Stroker_LineTo(long stroker, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1LineTo
  (JNIEnv *, jclass, jlong, jlong) {

    return 0;
}

// FT_Error FT_Stroker_ConicTo(FT_Stroker stroker, FT_Vector* control, FT_Vector* to)
// int FT_Stroker_ConicTo(long stroker, long control, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ConicTo
  (JNIEnv *, jclass, jlong, jlong, jlong) {

    return 0;
}

// FT_Error FT_Stroker_CubicTo(FT_Stroker stroker, FT_Vector* control1, FT_Vector* control2, FT_Vector* to)
// int FT_Stroker_CubicTo(long stroker, long control1, long control2, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1CubicTo
  (JNIEnv *, jclass, jlong, jlong, jlong, jlong) {

    return 0;
}

// FT_Error FT_Stroker_GetBorderCounts(FT_Stroker stroker, FT_StrokerBorder border, FT_UInt *anum_points, FT_UInt *anum_contours)
// int FT_Stroker_GetBorderCounts(long stroker, int border, long[] anum_points, long[] anum_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1GetBorderCounts
  (JNIEnv *, jclass, jlong, jint, jlongArray, jlongArray) {

    return 0;
}

// void FT_Stroker_ExportBorder(FT_Stroker stroker, FT_StrokerBorder border, FT_Outline* outline)
// void FT_Stroker_ExportBorder(long stroker, long border, long outline);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ExportBorder
  (JNIEnv *, jclass, jlong, jlong, jlong) {


}

// FT_Error FT_Stroker_GetCounts(FT_Stroker stroker, FT_UInt *anum_points, FT_UInt *anum_contours)
// int FT_Stroker_GetCounts(long stroker, long[] anum_points, long[] anum_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1GetCounts
  (JNIEnv *, jclass, jlong, jlongArray, jlongArray) {

    return 0;
}

// void FT_Stroker_Export(FT_Stroker stroker, FT_Outline* outline)
// void FT_Stroker_Export(long stroker, long outline);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Export
  (JNIEnv *, jclass, jlong, jlong) {


}

// void FT_Stroker_Done(FT_Stroker stroker)
// void FT_Stroker_Done(long stroker);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Done
  (JNIEnv *, jclass, jlong) {


}

// FT_Error FT_Glyph_Stroke(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool destroy)
// int FT_Glyph_Stroke(long pglyph, long stroker, boolean destroy);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Stroke
  (JNIEnv *, jclass, jlong, jlong, jboolean) {

    return 0;
}

// FT_Error FT_Glyph_StrokeBorder(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool inside, FT_Bool destroy)
// int FT_Glyph_StrokeBorder(long pglyph, long stroker, boolean inside, boolean destroy);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1StrokeBorder
  (JNIEnv *, jclass, jlong, jlong, jboolean, jboolean) {

    return 0;
}
