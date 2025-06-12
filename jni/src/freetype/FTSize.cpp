// typedef struct FT_SizeRec_* FT_Size;

#include "freetype/FTSize.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Face face;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSize_getFace
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Size size = reinterpret_cast<FT_Size>(sizePtrRaw);
    if(!size)
        return 0;

    return reinterpret_cast<jlong>(size->face);
}

// FT_Size_Metrics metrics;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTSize_getMetrics
  (JNIEnv *, jclass, jlong sizePtrRaw) {

    const FT_Size size = reinterpret_cast<FT_Size>(sizePtrRaw);
    if(!size)
        return 0;

    return reinterpret_cast<jlong>(&size->metrics);
}