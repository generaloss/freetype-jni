// typedef struct FT_Matrix_ { ... } FT_Matrix;

#include "types/FTMatrix.h"
#include <ft2build.h>
#include FT_FREETYPE_H

// FT_Fixed xx;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXX
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->xx);
}

// FT_Fixed xy;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXY
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->xy);
}

// FT_Fixed yx;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYX
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->yx);
}

// FT_Fixed yy;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYY
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->yy);
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTMatrix_newStruct
  (JNIEnv *, jclass) {

    FT_Matrix* pointer = new FT_Matrix;
    return reinterpret_cast<jlong>(pointer);
}
