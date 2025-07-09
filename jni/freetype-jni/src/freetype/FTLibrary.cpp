// typedef struct FT_LibraryRec_ { ... } FT_LibraryRec;
// typedef struct FT_LibraryRec_* FT_Library;

#include "freetype/FTLibrary.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include <freetype/internal/ftobjs.h>

// FT_Memory memory;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTLibrary_getMemory
  (JNIEnv *, jclass, jlong libraryPtrRaw) {

    const FT_LibraryRec_* library = reinterpret_cast<FT_LibraryRec_*>(libraryPtrRaw);
    if(!library)
        return 0;

    return reinterpret_cast<jlong>(library->memory);
}
