#include "FTSize.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_face_FTSize_getMetrics (JNIEnv *, jclass, jlong sizePtr) {

    const FT_Size size = reinterpret_cast<FT_Size>(sizePtr);
    if(!size)
        return 0;

    return reinterpret_cast<jlong>(&size->metrics);
}