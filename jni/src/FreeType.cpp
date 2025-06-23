#include "FreeType.h"

#include <jni.h>
#include <ft2build.h>
#include <freetype/internal/ftgloadr.h>
#include <freetype/internal/ftobjs.h>
#include FT_FREETYPE_H
#include FT_GLYPH_H
#include FT_OUTLINE_H
#include FT_STROKER_H


void throwException(JNIEnv* env, const char* message) {
    const jclass exceptionClass = env->FindClass("generaloss/freetype/FTException");
    env->ThrowNew(exceptionClass, message);
}


// ------------------------------
// ---      freetype.h        ---
// ------------------------------


// FT_Error FT_Init_FreeType(FT_Library *alibrary)
// int FT_Init_FreeType(long[] alibrary);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Init_1FreeType
  (JNIEnv *env, jclass, jlongArray libraryPtrRaw) {

    if(!libraryPtrRaw) {
        throwException(env, "Invalid FT_Library destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(libraryPtrRaw);
    if(length < 1) {
        throwException(env, "FT_Library destination pointer array length < 1");
        return 0;
    }

    FT_Library library;
    const FT_Error error = FT_Init_FreeType(&library);

    const jlong libraryPtr = reinterpret_cast<jlong>(library);
    env->SetLongArrayRegion(libraryPtrRaw, 0, 1, &libraryPtr);

    return static_cast<jint>(error);
}

// FT_Error FT_Done_FreeType(FT_Library library)
// int FT_Done_FreeType(long library);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Done_1FreeType
  (JNIEnv *env, jclass, jlong libraryPtrRaw) {

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    printf("Freeing Library %p\n", library);
    const FT_Error error = FT_Done_FreeType(library);
    return static_cast<jint>(error);
}

// FT_Error FT_New_Face(FT_Library library, const char* filepathname, FT_Long face_index, FT_Face *aface)
// int FT_New_Face(long library, String filepathname, long face_index, long[] aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jstring filepathRaw, jlong faceIndexRaw, jlongArray facePtrRaw) {

    if(!facePtrRaw) {
        throwException(env, "Invalid FT_Face destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(facePtrRaw);
    if(length < 1) {
        throwException(env, "FT_Face destination pointer array length < 1");
        return 0;
    }

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const char* filepath = env->GetStringUTFChars(filepathRaw, NULL);
    if(!filepath) {
        throwException(env, "Invalid filepath");
        return 0;
    }

    const FT_Long faceIndex = static_cast<FT_Long>(faceIndexRaw);

    FT_Face face;
    const FT_Error error = FT_New_Face(library, filepath, faceIndex, &face);

    env->ReleaseStringUTFChars(filepathRaw, filepath);

    const jlong facePtr = reinterpret_cast<jlong>(face);
    env->SetLongArrayRegion(facePtrRaw, 0, 1, &facePtr);

    return static_cast<jint>(error);
}

// FT_Error FT_New_Memory_Face(FT_Library library, const FT_Byte* file_base, FT_Long file_size, FT_Long face_index, FT_Face *aface)
// int FT_New_Memory_Face(long library, ByteBuffer file_base, long file_size, long face_index, long[] aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Memory_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jobject fileBaseBuffer, jlong fileSizeRaw, jlong faceIndexRaw, jlongArray facePtrRaw) {

    if(!facePtrRaw) {
        throwException(env, "Invalid FT_Face destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(facePtrRaw);
    if(length < 1) {
        throwException(env, "FT_Face destination pointer array length < 1");
        return 0;
    }

    const FT_Library libraryPtr = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!libraryPtr) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Byte* fileBase = reinterpret_cast<const FT_Byte*>(env->GetDirectBufferAddress(fileBaseBuffer));
    if(!fileBase) {
        throwException(env, "Invalid direct buffer");
        return 0;
    }

    const FT_Long fileSize = static_cast<FT_Long>(fileSizeRaw);
    const FT_Long faceIndex = static_cast<FT_Long>(faceIndexRaw);

    FT_Face face;
    const FT_Error error = FT_New_Memory_Face(libraryPtr, fileBase, fileSize, faceIndex, &face);

    const jlong facePtr = reinterpret_cast<jlong>(face);
    env->SetLongArrayRegion(facePtrRaw, 0, 1, &facePtr);

    return static_cast<jint>(error);
}

// FT_Error FT_Open_Face(FT_Library library, const FT_Open_Args* args, FT_Long face_index, FT_Face *aface)
// int FT_Open_Face(long library, long args, long face_index, long[] aface);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Open_1Face
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jlong argsPtrRaw, jlong faceIndexRaw, jlongArray facePtrRaw) {

    if(!facePtrRaw) {
        throwException(env, "Invalid FT_Face destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(facePtrRaw);
    if(length < 1) {
        throwException(env, "FT_Face destination pointer array length < 1");
        return 0;
    }

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
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

    FT_Face face;
    const FT_Error error = FT_Open_Face(library, argsPtr, faceIndex, &face);

    const jlong facePtr = reinterpret_cast<jlong>(face);
    env->SetLongArrayRegion(facePtrRaw, 0, 1, &facePtr);

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

    if(!filepathRaw) {
        throwException(env, "Filepath is null");
        return 0;
    }

    const char* filepath = env->GetStringUTFChars(filepathRaw, NULL);
    if(!filepath) {
        throwException(env, "Failed to get UTF-8 string from filepath string");
        return 0;
    }

    const FT_Error error = FT_Attach_File(face, filepath);

    env->ReleaseStringUTFChars(filepathRaw, filepath);

    return static_cast<jint>(error);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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
  (JNIEnv *env, jclass, jlong facePtrRaw, jbooleanArray dstDestroyFlag) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_Long refcount = face->internal->refcount;

    printf("Freeing Face %p\n", face);
    const FT_Error error = FT_Done_Face(face);

    if(dstDestroyFlag && error == FT_Err_Ok && refcount == 1) {
        jboolean isDestroyed = JNI_TRUE;
        env->SetBooleanArrayRegion(dstDestroyFlag, 0, 1, &isDestroyed);
    }

    return static_cast<jint>(error);
}

// FT_Error FT_Select_Size(FT_Face face, FT_Int strike_index)
// int FT_Select_Size(long face, int strike_index);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Select_1Size
  (JNIEnv *env, jclass, jlong facePtrRaw, jint strikeIndexRaw) {

   const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong requestPtrRaw) {

   const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
   if(!face) {
       throwException(env, "Invalid FT_Face pointer");
       return 0;
   }

   const FT_Size_Request request = reinterpret_cast<FT_Size_Request>(requestPtrRaw);
   if(!request) {
       throwException(env, "Invalid FT_Size_Request pointer");
       return 0;
   }

   const FT_Error error = FT_Request_Size(face, request);
   return static_cast<jint>(error);
}

// FT_Error FT_Set_Char_Size(FT_Face face, FT_F26Dot6 char_width, FT_F26Dot6 char_height, FT_UInt horz_resolution, FT_UInt vert_resolution)
// int FT_Set_Char_Size(long face, int char_width, int char_height, long horz_resolution, long vert_resolution);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Set_1Char_1Size
  (JNIEnv *env, jclass, jlong facePtrRaw, jint charWidthRaw, jint charHeightRaw, jlong horzResRaw, jlong vertResRaw) {

   const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
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

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    FT_UInt agindex;
    const FT_ULong charcode = FT_Get_First_Char(face, &agindex);

    if(gindexArrayRaw != nullptr) {
        jlong* gindexArray = env->GetLongArrayElements(gindexArrayRaw, NULL);

        const jsize length = env->GetArrayLength(gindexArrayRaw);
        if(length > 0) {
            gindexArray[0] = static_cast<jlong>(agindex);
            env->ReleaseLongArrayElements(gindexArrayRaw, gindexArray, 0);
        }
    }

    return static_cast<jlong>(charcode);
}

// FT_ULong FT_Get_Next_Char(FT_Face face, FT_ULong char_code, FT_UInt *agindex)
// long FT_Get_Next_Char(long face, long char_code, long[] agindex);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Next_1Char
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw, jlongArray gindexArrayRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    FT_UInt agindex;
    const FT_ULong nextCharcode = FT_Get_Next_Char(face, static_cast<FT_ULong>(charCodeRaw), &agindex);

    if(gindexArrayRaw != nullptr) {
        jlong* gindexArray = env->GetLongArrayElements(gindexArrayRaw, NULL);

        const jsize length = env->GetArrayLength(gindexArrayRaw);
        if(length > 0) {
            gindexArray[0] = static_cast<jlong>(agindex);
            env->ReleaseLongArrayElements(gindexArrayRaw, gindexArray, 0);
        }
    }

    return static_cast<jlong>(nextCharcode);
}

// FT_Error FT_Face_Properties(FT_Face face, FT_UInt num_properties, FT_Parameter* properties)
// int FT_Face_Properties(long face, long num_properties, long[] properties);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1Properties
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong numPropertiesRaw, jlongArray propertiesArrayRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt numProperties = static_cast<FT_UInt>(numPropertiesRaw);
    if(numProperties == 0)
        return 0;

    const jsize arrayLength = env->GetArrayLength(propertiesArrayRaw);
    if(arrayLength < static_cast<jsize>(numProperties)) {
        throwException(env, "Too few property pointers in array");
        return 0;
    }

    jlong* pointerArray = env->GetLongArrayElements(propertiesArrayRaw, NULL);
    if(!pointerArray) {
        throwException(env, "Failed to get array elements");
        return 0;
    }

    FT_Parameter* properties = new FT_Parameter[numProperties];
    for(FT_UInt i = 0; i < numProperties; i++) {
        FT_Parameter* src = reinterpret_cast<FT_Parameter*>(pointerArray[i]);
        properties[i] = *src;
    }

    env->ReleaseLongArrayElements(propertiesArrayRaw, pointerArray, JNI_ABORT);

    const FT_Error error = FT_Face_Properties(face, numProperties, properties);

    delete[] properties;

    return static_cast<jint>(error);
}

// FT_UInt FT_Get_Name_Index(FT_Face face, const FT_String* glyph_name)
// long FT_Get_Name_Index(long face, String glyph_name);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Name_1Index
  (JNIEnv *env, jclass, jlong facePtrRaw, jstring glyphNameRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
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
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong glyphIndexRaw, jobject bufferRaw, jlong bufferMaxRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt glyphIndex = static_cast<FT_UInt>(glyphIndexRaw);

    const FT_Pointer buffer = env->GetDirectBufferAddress(bufferRaw);
    if(!buffer) {
        throwException(env, "Invalid buffer");
        return 0;
    }

    const FT_UInt bufferMax = static_cast<FT_UInt>(bufferMaxRaw);

    const FT_Error error = FT_Get_Glyph_Name(face, glyphIndex, buffer, bufferMax);
    return static_cast<jint>(error);
}

// const char* FT_Get_Postscript_Name(FT_Face face)
// String FT_Get_Postscript_Name(long face);
JNIEXPORT jstring JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Postscript_1Name
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return NULL;
    }

    const char* name = FT_Get_Postscript_Name(face);
    if(!name)
        return NULL;

    return env->NewStringUTF(name);
}

// FT_Error FT_Get_SubGlyph_Info(FT_GlyphSlot glyph, FT_UInt sub_index, FT_Int *p_index, FT_UInt *p_flags, FT_Int *p_arg1, FT_Int *p_arg2, FT_Matrix *p_transform)
// int FT_Get_SubGlyph_Info(long glyph, long sub_index, int[] p_index, long[] p_flags, int[] p_arg1, int[] p_arg2, long p_transform);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1SubGlyph_1Info
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jlong subIndexRaw, jintArray pIndexArray, jlongArray pFlagsArray, jintArray pArg1Array, jintArray pArg2Array, jlong pTransformPtrRaw) {

    const FT_GlyphSlot glyph = reinterpret_cast<FT_GlyphSlot>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_GlyphSlot pointer");
        return 0;
    }

    const FT_UInt subIndex = static_cast<FT_UInt>(subIndexRaw);

    jint* pIndex = env->GetIntArrayElements(pIndexArray, NULL);
    jlong* pFlags = env->GetLongArrayElements(pFlagsArray, NULL);
    jint* pArg1 = env->GetIntArrayElements(pArg1Array, NULL);
    jint* pArg2 = env->GetIntArrayElements(pArg2Array, NULL);
    FT_Matrix* pTransform = reinterpret_cast<FT_Matrix*>(pTransformPtrRaw);

    const FT_Error error = FT_Get_SubGlyph_Info(glyph, subIndex, pIndex, (FT_UInt*)pFlags, pArg1, pArg2, pTransform);

    env->ReleaseIntArrayElements(pIndexArray, pIndex, 0);
    env->ReleaseLongArrayElements(pFlagsArray, pFlags, 0);
    env->ReleaseIntArrayElements(pArg1Array, pArg1, 0);
    env->ReleaseIntArrayElements(pArg2Array, pArg2, 0);

    return static_cast<jint>(error);
}

// FT_UShort FT_Get_FSType_Flags(FT_Face face)
// int FT_Get_FSType_Flags(long face);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1FSType_1Flags
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UShort flags = FT_Get_FSType_Flags(face);
    return static_cast<jint>(flags);
}

// FT_UInt FT_Face_GetCharVariantIndex(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
// long FT_Face_GetCharVariantIndex(long face, long charcode, long variantSelector);
JNIEXPORT jlong JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharVariantIndex
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw, jlong variantSelectorRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong charCode = static_cast<FT_ULong>(charCodeRaw);
    const FT_ULong variantSelector = static_cast<FT_ULong>(variantSelectorRaw);

    const FT_UInt index = FT_Face_GetCharVariantIndex(face, charCode, variantSelector);
    return static_cast<jlong>(index);
}

// FT_Int FT_Face_GetCharVariantIsDefault(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
// int FT_Face_GetCharVariantIsDefault(long face, long charcode, long variantSelector);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharVariantIsDefault
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw, jlong variantSelectorRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong charCode = static_cast<FT_ULong>(charCodeRaw);
    const FT_ULong variantSelector = static_cast<FT_ULong>(variantSelectorRaw);

    const FT_Int isDefault = FT_Face_GetCharVariantIsDefault(face, charCode, variantSelector);
    return static_cast<jint>(isDefault);
}

// FT_UInt32* FT_Face_GetVariantSelectors(FT_Face face)
// long FT_Face_GetVariantSelectors(long face);
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetVariantSelectors
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_UInt32* selectors = FT_Face_GetVariantSelectors(face);
    if(!selectors)
        return NULL;

    int count = 0;
    while(selectors[count] != 0)
        count++;

    const jlongArray result = env->NewLongArray(count);
    if(!result) {
        throwException(env, "Failed to allocate jlongArray");
        return NULL;
    }

    jlong* buffer = new jlong[count];
    for(int i = 0; i < count; i++)
        buffer[i] = static_cast<jlong>(selectors[i]);

    env->SetLongArrayRegion(result, 0, count, buffer);
    delete[] buffer;

    return result;
}

// FT_UInt32* FT_Face_GetVariantsOfChar(FT_Face face, FT_ULong charcode)
// long FT_Face_GetVariantsOfChar(long face, long charcode);
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetVariantsOfChar
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong charCodeRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong charCode = static_cast<FT_ULong>(charCodeRaw);

    const FT_UInt32* variants = FT_Face_GetVariantsOfChar(face, charCode);
    if(!variants)
        return NULL;

    int count = 0;
    while(variants[count] != 0)
        count++;

    const jlongArray result = env->NewLongArray(count);
    if(!result) {
        throwException(env, "Failed to allocate jlongArray");
        return NULL;
    }

    jlong* buffer = new jlong[count];
    for(int i = 0; i < count; i++)
        buffer[i] = static_cast<jlong>(variants[i]);

    env->SetLongArrayRegion(result, 0, count, buffer);
    delete[] buffer;

    return result;
}

// FT_UInt32* FT_Face_GetCharsOfVariant(FT_Face face, FT_ULong variantSelector)
// long FT_Face_GetCharsOfVariant(long face, long variantSelector);
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1GetCharsOfVariant
  (JNIEnv *env, jclass, jlong facePtrRaw, jlong variantSelectorRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return 0;
    }

    const FT_ULong variantSelector = static_cast<FT_ULong>(variantSelectorRaw);

    const FT_UInt32* chars = FT_Face_GetCharsOfVariant(face, variantSelector);
    if(!chars)
        return NULL;

    int count = 0;
    while(chars[count] != 0)
        count++;

    const jlongArray result = env->NewLongArray(count);
    if(!result) {
        throwException(env, "Failed to allocate jlongArray");
        return NULL;
    }

    jlong* buffer = new jlong[count];
    for(int i = 0; i < count; i++)
        buffer[i] = static_cast<jlong>(chars[i]);

    env->SetLongArrayRegion(result, 0, count, buffer);
    delete[] buffer;

    return result;
}

// FT_Fixed FT_RoundFix(FT_Fixed a)
// int FT_RoundFix(long a);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1RoundFix
  (JNIEnv *env, jclass, jlong aRaw) {

    const FT_Fixed* aPtr = reinterpret_cast<FT_Fixed*>(aRaw);
    if(!aPtr) {
        throwException(env, "Invalid FT_Fixed pointer");
        return 0;
    }

    return static_cast<jint>(FT_RoundFix(*aPtr));
}

// FT_Fixed FT_CeilFix(FT_Fixed a)
// int FT_CeilFix(long a);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1CeilFix
  (JNIEnv *env, jclass, jlong aRaw) {

    const FT_Fixed* aPtr = reinterpret_cast<FT_Fixed*>(aRaw);
    if(!aPtr) {
         throwException(env, "Invalid FT_Fixed pointer");
         return 0;
     }

    return static_cast<jint>(FT_CeilFix(*aPtr));
}

// FT_Fixed FT_FloorFix(FT_Fixed a)
// int FT_FloorFix(long a);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1FloorFix
  (JNIEnv *env, jclass, jlong aRaw) {

    const FT_Fixed* aPtr = reinterpret_cast<FT_Fixed*>(aRaw);
    if(!aPtr) {
        throwException(env, "Invalid FT_Fixed pointer");
        return 0;
    }

    return static_cast<jint>(FT_FloorFix(*aPtr));
}

// void FT_Vector_Transform(FT_Vector* vector, const FT_Matrix* matrix)
// void FT_Vector_Transform(long vector, long matrix);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Vector_1Transform
  (JNIEnv *env, jclass, jlong vectorPtrRaw, jlong matrixPtrRaw) {

    FT_Vector* vector = reinterpret_cast<FT_Vector*>(vectorPtrRaw);
    if(!vector) {
        throwException(env, "Invalid FT_Vector pointer");
        return;
    }

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix) {
        throwException(env, "Invalid FT_Matrix pointer");
        return;
    }

    FT_Vector_Transform(vector, matrix);
}

// void FT_Library_Version(FT_Library library, FT_Int *amajor, FT_Int *aminor, FT_Int *apatch)
// void FT_Library_Version(long library, int[] amajor, int[] aminor, int[] apatch);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Library_1Version
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jintArray majorArray, jintArray minorArray, jintArray patchArray) {

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return;
    }

    jint* major = env->GetIntArrayElements(majorArray, NULL);
    jint* minor = env->GetIntArrayElements(minorArray, NULL);
    jint* patch = env->GetIntArrayElements(patchArray, NULL);

    FT_Int amajor, aminor, apatch;
    FT_Library_Version(library, &amajor, &aminor, &apatch);

    major[0] = static_cast<jint>(amajor);
    minor[0] = static_cast<jint>(aminor);
    patch[0] = static_cast<jint>(apatch);

    env->ReleaseIntArrayElements(majorArray, major, 0);
    env->ReleaseIntArrayElements(minorArray, minor, 0);
    env->ReleaseIntArrayElements(patchArray, patch, 0);
}

// FT_Bool FT_Face_CheckTrueTypePatents(FT_Face face)
// boolean FT_Face_CheckTrueTypePatents(long face);
JNIEXPORT jboolean JNICALL Java_generaloss_freetype_FreeType_FT_1Face_1CheckTrueTypePatents
  (JNIEnv *env, jclass, jlong facePtrRaw) {

    const FT_Face face = reinterpret_cast<FT_Face>(facePtrRaw);
    if(!face) {
        throwException(env, "Invalid FT_Face pointer");
        return JNI_FALSE;
    }

    const FT_Bool result = FT_Face_CheckTrueTypePatents(face);
    return static_cast<jboolean>(result);
}


// ------------------------------
// ---      ftgloadr.h        ---
// ------------------------------


// FT_Error FT_GlyphLoader_New(FT_Memory memory, FT_GlyphLoader *aloader)
// int FT_GlyphLoader_New(long memory, long aloader);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1New
  (JNIEnv *env, jclass, jlong memoryPtrRaw, jlongArray dstLoaderPtrRaw) {

    const FT_Memory memory = reinterpret_cast<FT_Memory>(memoryPtrRaw);
    if(!memory) {
        throwException(env, "Invalid FT_Memory pointer");
        return 0;
    }

    FT_GlyphLoader loader;
    const FT_Error error = FT_GlyphLoader_New(memory, &loader);

    const jlong loaderPtr = reinterpret_cast<jlong>(loader);
    env->SetLongArrayRegion(dstLoaderPtrRaw, 0, 1, &loaderPtr);

    return static_cast<jint>(error);
}

// FT_Error FT_GlyphLoader_CreateExtra(FT_GlyphLoader loader)
// int FT_GlyphLoader_CreateExtra(long loader);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CreateExtra
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return 0;
    }

    const FT_Error error = FT_GlyphLoader_CreateExtra(loader);
    return static_cast<jint>(error);
}

// void FT_GlyphLoader_Done(FT_GlyphLoader loader)
// void FT_GlyphLoader_Done(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Done
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return;
    }

    printf("Freeing GlyphLoader %p\n", loader);
    FT_GlyphLoader_Done(loader);
}

// void FT_GlyphLoader_Reset(FT_GlyphLoader loader)
// void FT_GlyphLoader_Reset(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Reset
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return;
    }

    FT_GlyphLoader_Reset(loader);
}

// void FT_GlyphLoader_Rewind(FT_GlyphLoader loader)
// void FT_GlyphLoader_Rewind(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Rewind
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return;
    }

    FT_GlyphLoader_Rewind(loader);
}

// FT_Error FT_GlyphLoader_CheckPoints(FT_GlyphLoader loader, FT_UInt n_points, FT_UInt n_contours)
// int FT_GlyphLoader_CheckPoints(long loader, long n_points, long n_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CheckPoints
  (JNIEnv *env, jclass, jlong loaderPtrRaw, jlong nPointsRaw, jlong nContoursRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return 0;
    }

    const FT_UInt nPoints = static_cast<FT_UInt>(nPointsRaw);
    const FT_UInt nContours = static_cast<FT_UInt>(nContoursRaw);

    const FT_Error error = FT_GlyphLoader_CheckPoints(loader, nPoints, nContours);
    return static_cast<jint>(error);
}

// FT_Error FT_GlyphLoader_CheckSubGlyphs(FT_GlyphLoader loader, FT_UInt n_subs)
// int FT_GlyphLoader_CheckSubGlyphs(long loader, long n_subs);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1CheckSubGlyphs
  (JNIEnv *env, jclass, jlong loaderPtrRaw, jlong nSubsRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return 0;
    }

    const FT_UInt nSubs = static_cast<FT_UInt>(nSubsRaw);

    const FT_Error error = FT_GlyphLoader_CheckSubGlyphs(loader, nSubs);
    return static_cast<jint>(error);
}

// void FT_GlyphLoader_Prepare(FT_GlyphLoader loader)
// void FT_GlyphLoader_Prepare(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Prepare
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return;
    }

    FT_GlyphLoader_Prepare(loader);
}

// void FT_GlyphLoader_Add(FT_GlyphLoader loader)
// void FT_GlyphLoader_Add(long loader);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1GlyphLoader_1Add
  (JNIEnv *env, jclass, jlong loaderPtrRaw) {

    const FT_GlyphLoader loader = reinterpret_cast<FT_GlyphLoader>(loaderPtrRaw);
    if(!loader) {
        throwException(env, "Invalid FT_GlyphLoader pointer");
        return;
    }

    FT_GlyphLoader_Add(loader);
}


// ------------------------------
// ---       ftglyph.h        ---
// ------------------------------


// FT_Error FT_New_Glyph(FT_Library library, FT_Glyph_Format format, FT_Glyph *aglyph)
// int FT_New_Glyph(long library, int format, long aglyph);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1New_1Glyph
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jint formatRaw, jlongArray glyphPtrRaw) {

    if(!glyphPtrRaw) {
        throwException(env, "Invalid FT_Glyph destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(glyphPtrRaw);
    if(length < 1) {
        throwException(env, "FT_Glyph destination pointer array length < 1");
        return 0;
    }

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    const FT_Glyph_Format format = static_cast<FT_Glyph_Format>(formatRaw);

    FT_Glyph glyph;
    const FT_Error error = FT_New_Glyph(library, format, &glyph);

    const jlong glyphPtr = reinterpret_cast<jlong>(glyph);
    env->SetLongArrayRegion(glyphPtrRaw, 0, 1, &glyphPtr);

    return static_cast<jint>(error);
}

// FT_Error FT_Get_Glyph(FT_GlyphSlot slot, FT_Glyph *aglyph)
// int FT_Get_Glyph(long slot, long aglyph);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Get_1Glyph
  (JNIEnv *env, jclass, jlong slotPtrRaw, jlongArray glyphPtrRaw) {

    if(!glyphPtrRaw) {
        throwException(env, "Invalid FT_Glyph destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(glyphPtrRaw);
    if(length < 1) {
        throwException(env, "FT_Glyph destination pointer array length < 1");
        return 0;
    }

    const FT_GlyphSlot slot = reinterpret_cast<FT_GlyphSlot>(slotPtrRaw);
    if(!slot) {
        throwException(env, "Invalid FT_GlyphSlot pointer");
        return 0;
    }

    FT_Glyph glyph;
    const FT_Error error = FT_Get_Glyph(slot, &glyph);

    if(glyph && error == FT_Err_Ok) {
        const jlong glyphPtr = reinterpret_cast<jlong>(glyph);
        env->SetLongArrayRegion(glyphPtrRaw, 0, 1, &glyphPtr);
    }

    return static_cast<jint>(error);
}

// FT_Error FT_Glyph_Copy(FT_Glyph source, FT_Glyph *target)
// int FT_Glyph_Copy(long source, long target);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Copy
  (JNIEnv *env, jclass, jlong sourcePtrRaw, jlongArray dstTargetPtrRaw) {

    const FT_Glyph source = reinterpret_cast<FT_Glyph>(sourcePtrRaw);
    if(!source) {
        throwException(env, "Invalid source FT_Glyph pointer");
        return 0;
    }

    if(!dstTargetPtrRaw || env->GetArrayLength(dstTargetPtrRaw) < 1) {
        throwException(env, "Invalid FT_Glyph destination pointer array");
        return 0;
    }

    FT_Glyph target;
    const FT_Error error = FT_Glyph_Copy(source, &target);

    if(target && error == FT_Err_Ok) {
        const jlong targetPtr = reinterpret_cast<jlong>(target);
        env->SetLongArrayRegion(dstTargetPtrRaw, 0, 1, &targetPtr);
    }

    return static_cast<jint>(error);
}

// FT_Error FT_Glyph_Transform(FT_Glyph glyph, const FT_Matrix* matrix, const FT_Vector* delta)
// int FT_Glyph_Transform(long glyph, long matrix, long delta);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Transform
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jlong matrixPtrRaw, jlong deltaPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return 0;
    }

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    const FT_Vector* delta = reinterpret_cast<FT_Vector*>(deltaPtrRaw);

    const FT_Error error = FT_Glyph_Transform(glyph, matrix, delta);
    return static_cast<jint>(error);
}

// void FT_Glyph_Get_CBox(FT_Glyph glyph, FT_UInt bbox_mode, FT_BBox *acbox)
// void FT_Glyph_Get_CBox(long glyph, long bboxMode, long acbox);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Get_1CBox
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jlong bboxModeRaw, jlong cboxPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return;
    }

    const FT_UInt bboxMode = static_cast<FT_UInt>(bboxModeRaw);

    FT_BBox* cbox = reinterpret_cast<FT_BBox*>(cboxPtrRaw);
    if(!cbox) {
        throwException(env, "Invalid FT_BBox pointer");
        return;
    }

    FT_Glyph_Get_CBox(glyph, bboxMode, cbox);
}

// FT_Error FT_Glyph_To_Bitmap(FT_Glyph* the_glyph, FT_Render_Mode render_mode, const FT_Vector* origin, FT_Bool destroy)
// int FT_Glyph_To_Bitmap(long the_glyph, int render_mode, long origin, boolean destroy, long[] dstBitmapGlyphPointer);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1To_1Bitmap
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jint renderModeRaw, jlong originPtrRaw, jboolean destroyRaw, jlongArray dstGlyphPtrRaw) {

    FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return 0;
    }

    const FT_Render_Mode renderMode = static_cast<FT_Render_Mode>(renderModeRaw);
    const FT_Vector* origin = reinterpret_cast<FT_Vector*>(originPtrRaw);
    const FT_Bool destroy = static_cast<FT_Bool>(destroyRaw);

    jlong* dstGlyphPtr = env->GetLongArrayElements(dstGlyphPtrRaw, NULL);
    if(!dstGlyphPtr) {
        throwException(env, "Invalid destination array");
        return 0;
    }

    const FT_Error error = FT_Glyph_To_Bitmap(&glyph, renderMode, origin, destroy);
    dstGlyphPtr[0] = reinterpret_cast<jlong>(glyph);

    env->ReleaseLongArrayElements(dstGlyphPtrRaw, dstGlyphPtr, 0);

    return static_cast<jint>(error);
}

// void FT_Done_Glyph(FT_Glyph glyph)
// void FT_Done_Glyph(long glyph);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Done_1Glyph
  (JNIEnv *env, jclass, jlong glyphPtrRaw) {

    const FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return;
    }

    printf("Freeing Glyph %p\n", glyph);
    FT_Done_Glyph(glyph);
}

// void FT_Matrix_Multiply(const FT_Matrix* a, FT_Matrix* b)
// void FT_Matrix_Multiply(long a, long b);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Matrix_1Multiply
  (JNIEnv *env, jclass, jlong aPtrRaw, jlong bPtrRaw) {

    const FT_Matrix* a = reinterpret_cast<FT_Matrix*>(aPtrRaw);
    FT_Matrix* b = reinterpret_cast<FT_Matrix*>(bPtrRaw);
    if(!a || !b) {
        throwException(env, "Invalid FT_Matrix pointer");
        return;
    }

    FT_Matrix_Multiply(a, b);
}

// FT_Error FT_Matrix_Invert(FT_Matrix* matrix)
// int FT_Matrix_Invert(long matrix);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Matrix_1Invert
  (JNIEnv *env, jclass, jlong matrixPtrRaw) {

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix) {
        throwException(env, "Invalid FT_Matrix pointer");
        return 0;
    }

    const FT_Error error = FT_Matrix_Invert(matrix);
    return static_cast<jint>(error);
}


// ------------------------------
// ---      ftoutln.h        ---
// ------------------------------


// FT_Error FT_Outline_New(FT_Library library, FT_UInt numPoints, FT_Int numContours, FT_Outline *anoutline)
// int FT_Outline_New(long library, long numPoints, int numContours, long[] dstOutlinePointer);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1New
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jlong numPointsRaw, jlong numContoursRaw, jlongArray dstOutlinePointer) {

    if(libraryPtrRaw == 0) {
        throwException(env, "Invalid FT_Library pointer");
        return static_cast<jint>(FT_Err_Invalid_Library_Handle);
    }

    if(dstOutlinePointer == nullptr) {
        throwException(env, "Destination array is null");
        return static_cast<jint>(FT_Err_Invalid_Argument);
    }

    const jsize arrayLen = env->GetArrayLength(dstOutlinePointer);
    if(arrayLen < 1) {
        throwException(env, "Destination array length < 1");
        return static_cast<jint>(FT_Err_Invalid_Argument);
    }

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    const FT_UInt numPoints = static_cast<FT_UInt>(numPointsRaw);
    const FT_Int numContours = static_cast<FT_Int>(numContoursRaw);

    FT_Outline* outline = new FT_Outline();
    const FT_Error error = FT_Outline_New(library, numPoints, numContours, outline);

    if(error == FT_Err_Ok) {
        jlong resultPtr = reinterpret_cast<jlong>(outline);
        env->SetLongArrayRegion(dstOutlinePointer, 0, 1, &resultPtr);
    }else{
        delete outline;
    }

    return static_cast<jint>(error);
}

// FT_Error FT_Outline_Done(FT_Library library, FT_Outline* outline)
// int FT_Outline_Done(long library, long outline);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1Done
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jlong outlinePtrRaw) {

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return 0;
    }

    printf("Freeing outline: %p (points=%p, tags=%p, contours=%p)\n", outline, outline->points, outline->tags, outline->contours);
    const FT_Error error = FT_Outline_Done(library, outline);
    delete outline;
    return static_cast<jint>(error);
}


// ------------------------------
// ---      ftstroke.h        ---
// ------------------------------


// FT_StrokerBorder FT_Outline_GetInsideBorder(FT_Outline* outline)
// int FT_Outline_GetInsideBorder(long outline);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1GetInsideBorder
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return 0;
    }

    const FT_StrokerBorder border = FT_Outline_GetInsideBorder(outline);
    return static_cast<jint>(border);
}

// FT_StrokerBorder FT_Outline_GetOutsideBorder(FT_Outline* outline)
// int FT_Outline_GetOutsideBorder(long outline);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Outline_1GetOutsideBorder
  (JNIEnv *env, jclass, jlong outlinePtrRaw) {

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return 0;
    }

    const FT_StrokerBorder border = FT_Outline_GetOutsideBorder(outline);
    return static_cast<jint>(border);
}

// FT_Error FT_Stroker_New(FT_Library library, FT_Stroker *astroker)
// int FT_Stroker_New(long library, long[] dstStrokerPointer);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1New
  (JNIEnv *env, jclass, jlong libraryPtrRaw, jlongArray strokerPtrRaw) {

    const FT_Library library = reinterpret_cast<FT_Library>(libraryPtrRaw);
    if(!library) {
        throwException(env, "Invalid FT_Library pointer");
        return 0;
    }

    if(!strokerPtrRaw) {
        throwException(env, "Invalid FT_Stroker destination pointer array");
        return 0;
    }

    const jsize length = env->GetArrayLength(strokerPtrRaw);
    if(length < 1) {
        throwException(env, "FT_Stroker destination pointer array length < 1");
        return 0;
    }

    FT_Stroker stroker;
    const FT_Error error = FT_Stroker_New(library, &stroker);

    const jlong strokerPtr = reinterpret_cast<jlong>(stroker);
    env->SetLongArrayRegion(strokerPtrRaw, 0, 1, &strokerPtr);

    return static_cast<jint>(error);
}

// void FT_Stroker_Set(FT_Stroker stroker, FT_Fixed radius, FT_Stroker_LineCap line_cap, FT_Stroker_LineJoin line_join, FT_Fixed miter_limit)
// void FT_Stroker_Set(long stroker, int radius, int line_cap, int line_join, int miter_limit);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Set
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong radiusRaw, jint lineCapRaw, jint lineJoinRaw, jlong miterLimitRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return;
    }

    const FT_Fixed radius = static_cast<FT_Fixed>(radiusRaw);
    const FT_Stroker_LineCap lineCap = static_cast<FT_Stroker_LineCap>(lineCapRaw);
    const FT_Stroker_LineJoin lineJoin = static_cast<FT_Stroker_LineJoin>(lineJoinRaw);
    const FT_Fixed miterLimit = static_cast<FT_Fixed>(miterLimitRaw);

    FT_Stroker_Set(stroker, radius, lineCap, lineJoin, miterLimit);
}

// void FT_Stroker_Rewind(FT_Stroker stroker)
// void FT_Stroker_Rewind(long stroker);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Rewind
  (JNIEnv *env, jclass, jlong strokerPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return;
    }

    FT_Stroker_Rewind(stroker);
}

// FT_Error FT_Stroker_ParseOutline(FT_Stroker stroker, FT_Outline* outline, FT_Bool opened)
// int FT_Stroker_ParseOutline(long stroker, long outline, boolean opened);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ParseOutline
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong outlinePtrRaw, jboolean openedRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return 0;
    }

    const FT_Bool opened = static_cast<FT_Bool>(openedRaw);

    const FT_Error error = FT_Stroker_ParseOutline(stroker, outline, opened);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_BeginSubPath(FT_Stroker stroker, FT_Vector* to, FT_Bool open)
// int FT_Stroker_BeginSubPath(long stroker, long to, boolean open);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1BeginSubPath
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong toPtrRaw, jboolean openRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    FT_Vector* to = reinterpret_cast<FT_Vector*>(toPtrRaw);
    if(!to) {
        throwException(env, "Invalid FT_Vector pointer");
        return 0;
    }

    const FT_Bool open = static_cast<FT_Bool>(openRaw);

    const FT_Error error = FT_Stroker_BeginSubPath(stroker, to, open);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_EndSubPath(FT_Stroker stroker)
// int FT_Stroker_EndSubPath(long stroker);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1EndSubPath
  (JNIEnv *env, jclass, jlong strokerPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    const FT_Error error = FT_Stroker_EndSubPath(stroker);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_LineTo(FT_Stroker stroker, FT_Vector* to)
// int FT_Stroker_LineTo(long stroker, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1LineTo
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong toPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    FT_Vector* to = reinterpret_cast<FT_Vector*>(toPtrRaw);
    if(!to) {
        throwException(env, "Invalid FT_Vector pointer");
        return 0;
    }

    const FT_Error error = FT_Stroker_LineTo(stroker, to);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_ConicTo(FT_Stroker stroker, FT_Vector* control, FT_Vector* to)
// int FT_Stroker_ConicTo(long stroker, long control, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ConicTo
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong controlPtrRaw, jlong toPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    FT_Vector* control = reinterpret_cast<FT_Vector*>(controlPtrRaw);
    FT_Vector* to = reinterpret_cast<FT_Vector*>(toPtrRaw);
    if(!control || !to) {
        throwException(env, "Invalid FT_Vector pointer");
        return 0;
    }

    const FT_Error error = FT_Stroker_ConicTo(stroker, control, to);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_CubicTo(FT_Stroker stroker, FT_Vector* control1, FT_Vector* control2, FT_Vector* to)
// int FT_Stroker_CubicTo(long stroker, long control1, long control2, long to);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1CubicTo
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong control1PtrRaw, jlong control2PtrRaw, jlong toPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    FT_Vector* control1 = reinterpret_cast<FT_Vector*>(control1PtrRaw);
    FT_Vector* control2 = reinterpret_cast<FT_Vector*>(control2PtrRaw);
    FT_Vector* to = reinterpret_cast<FT_Vector*>(toPtrRaw);
    if(!control1 || !control2 || !to) {
        throwException(env, "Invalid FT_Vector pointer");
        return 0;
    }

    const FT_Error error = FT_Stroker_CubicTo(stroker, control1, control2, to);
    return static_cast<jint>(error);
}

// FT_Error FT_Stroker_GetBorderCounts(FT_Stroker stroker, FT_StrokerBorder border, FT_UInt *anum_points, FT_UInt *anum_contours)
// int FT_Stroker_GetBorderCounts(long stroker, int border, long[] anum_points, long[] anum_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1GetBorderCounts
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jint borderRaw, jlongArray numPointsArray, jlongArray numContoursArray) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    const FT_StrokerBorder border = static_cast<FT_StrokerBorder>(borderRaw);
    jlong* numPoints = env->GetLongArrayElements(numPointsArray, NULL);
    jlong* numContours = env->GetLongArrayElements(numContoursArray, NULL);

    FT_UInt anum_points, anum_contours;
    const FT_Error error = FT_Stroker_GetBorderCounts(stroker, border, &anum_points, &anum_contours);

    numPoints[0] = static_cast<jlong>(anum_points);
    numContours[0] = static_cast<jlong>(anum_contours);

    env->ReleaseLongArrayElements(numPointsArray, numPoints, 0);
    env->ReleaseLongArrayElements(numContoursArray, numContours, 0);

    return static_cast<jint>(error);
}

// void FT_Stroker_ExportBorder(FT_Stroker stroker, FT_StrokerBorder border, FT_Outline* outline)
// void FT_Stroker_ExportBorder(long stroker, long border, long outline);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1ExportBorder
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong borderRaw, jlong outlinePtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return;
    }

    const FT_StrokerBorder border = static_cast<FT_StrokerBorder>(borderRaw);

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return;
    }

    FT_Stroker_ExportBorder(stroker, border, outline);
}

// FT_Error FT_Stroker_GetCounts(FT_Stroker stroker, FT_UInt *anum_points, FT_UInt *anum_contours)
// int FT_Stroker_GetCounts(long stroker, long[] anum_points, long[] anum_contours);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1GetCounts
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlongArray numPointsArray, jlongArray numContoursArray) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    jlong* numPoints = env->GetLongArrayElements(numPointsArray, NULL);
    jlong* numContours = env->GetLongArrayElements(numContoursArray, NULL);

    FT_UInt anum_points, anum_contours;
    const FT_Error error = FT_Stroker_GetCounts(stroker, &anum_points, &anum_contours);

    numPoints[0] = static_cast<jlong>(anum_points);
    numContours[0] = static_cast<jlong>(anum_contours);

    env->ReleaseLongArrayElements(numPointsArray, numPoints, 0);
    env->ReleaseLongArrayElements(numContoursArray, numContours, 0);

    return static_cast<jint>(error);
}

// void FT_Stroker_Export(FT_Stroker stroker, FT_Outline* outline)
// void FT_Stroker_Export(long stroker, long outline);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Export
  (JNIEnv *env, jclass, jlong strokerPtrRaw, jlong outlinePtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return;
    }

    FT_Outline* outline = reinterpret_cast<FT_Outline*>(outlinePtrRaw);
    if(!outline) {
        throwException(env, "Invalid FT_Outline pointer");
        return;
    }

    FT_Stroker_Export(stroker, outline);
}

// void FT_Stroker_Done(FT_Stroker stroker)
// void FT_Stroker_Done(long stroker);
JNIEXPORT void JNICALL Java_generaloss_freetype_FreeType_FT_1Stroker_1Done
  (JNIEnv *env, jclass, jlong strokerPtrRaw) {

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return;
    }

    printf("FT_Stroker_Done: %p\n", stroker);
    FT_Stroker_Done(stroker);
}

// FT_Error FT_Glyph_Stroke(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool destroy)
// int FT_Glyph_Stroke(long pglyph, long stroker, boolean destroy);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1Stroke
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jlong strokerPtrRaw, jboolean destroyRaw, jlongArray dstGlyphPtrRaw) {

    FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return 0;
    }

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    const FT_Bool destroy = static_cast<FT_Bool>(destroyRaw);

    const FT_Error error = FT_Glyph_Stroke(&glyph, stroker, destroy);

    const jlong dstGlyphPtr = reinterpret_cast<jlong>(glyph);
    env->SetLongArrayRegion(dstGlyphPtrRaw, 0, 1, &dstGlyphPtr);

    return static_cast<jint>(error);
}

// FT_Error FT_Glyph_StrokeBorder(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool inside, FT_Bool destroy)
// int FT_Glyph_StrokeBorder(long pglyph, long stroker, boolean inside, boolean destroy);
JNIEXPORT jint JNICALL Java_generaloss_freetype_FreeType_FT_1Glyph_1StrokeBorder
  (JNIEnv *env, jclass, jlong glyphPtrRaw, jlong strokerPtrRaw, jboolean insideRaw, jboolean destroyRaw, jlongArray dstGlyphPtrRaw) {

    FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtrRaw);
    if(!glyph) {
        throwException(env, "Invalid FT_Glyph pointer");
        return 0;
    }

    const FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtrRaw);
    if(!stroker) {
        throwException(env, "Invalid FT_Stroker pointer");
        return 0;
    }

    const FT_Bool inside = static_cast<FT_Bool>(insideRaw);
    const FT_Bool destroy = static_cast<FT_Bool>(destroyRaw);

    const FT_Error error = FT_Glyph_StrokeBorder(&glyph, stroker, inside, destroy);

    const jlong dstGlyphPtr = reinterpret_cast<jlong>(glyph);
    env->SetLongArrayRegion(dstGlyphPtrRaw, 0, 1, &dstGlyphPtr);

    return static_cast<jint>(error);
}
