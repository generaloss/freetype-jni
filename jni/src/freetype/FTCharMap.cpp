// typedef struct FT_CharMapRec_* FT_CharMap;

#include "freetype/FTCharMap.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Face face;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTCharMap_getFace
  (JNIEnv *, jclass, jlong charmapPtrRaw) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
    if(!charmap)
        return 0;

    return reinterpret_cast<jlong>(charmap->face);
}

// FT_Encoding encoding;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTCharMap_getEncoding
  (JNIEnv *, jclass, jlong charmapPtrRaw) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->encoding);
}

// FT_UShort platform_id;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTCharMap_getPlatformID
  (JNIEnv *, jclass, jlong charmapPtrRaw) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->platform_id);
}

// FT_UShort encoding_id;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTCharMap_getEncodingID
  (JNIEnv *, jclass, jlong charmapPtrRaw) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtrRaw);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->encoding_id);
}