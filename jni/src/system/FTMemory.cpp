#include "system/FTMemory.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_system_FTMemory_newStruct
  (JNIEnv *, jclass) {

    FT_Memory* pointer = new FT_Memory(nullptr);
    return reinterpret_cast<jlong>(pointer);
}
