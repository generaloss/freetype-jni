#include "freetype/FTLibrary.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTLibrary_newStruct
  (JNIEnv *, jclass) {

    FT_Library* pointer = new FT_Library(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
