// typedef struct FT_MemoryRec_* FT_Memory;

#include "system/FTMemory.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_system_FTMemory_createPointer
  (JNIEnv *, jclass) {

    FT_Memory* pointer = new FT_Memory(nullptr);
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_system_FTMemory_freePointer
  (JNIEnv *, jclass, jlong memoryPtrRaw) {

    FT_Memory* memory = reinterpret_cast<FT_Memory*>(memoryPtrRaw);
    if(!memory)
        return;

    delete memory;
}
