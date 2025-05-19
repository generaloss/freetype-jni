#include "FTCharMap.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_generaloss_freetype_charmap_FTCharMap_getEncoding(JNIEnv*, jclass, jlong charmapPtr) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtr);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->encoding);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_charmap_FTCharMap_getPlatformID(JNIEnv*, jclass, jlong charmapPtr) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtr);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->platform_id);
}

JNIEXPORT jint JNICALL Java_generaloss_freetype_charmap_FTCharMap_getEncodingID(JNIEnv*, jclass, jlong charmapPtr) {

    const FT_CharMap charmap = reinterpret_cast<FT_CharMap>(charmapPtr);
    if(!charmap)
        return 0;

    return static_cast<jint>(charmap->encoding_id);
}