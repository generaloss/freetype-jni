// typedef struct FT_Matrix_ { ... } FT_Matrix;

#include "types/FTMatrix.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_types_FTMatrix_createPointer
  (JNIEnv *, jclass) {

    FT_Matrix* pointer = new FT_Matrix;
    memset(pointer, 0, sizeof(FT_Matrix));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTMatrix_freePointer
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    delete reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
}


// FT_Fixed xx;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXX
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->xx);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTMatrix_setXX
  (JNIEnv *, jclass, jlong matrixPtrRaw, jint valueRaw) {

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return;

    matrix->xx = static_cast<FT_Fixed>(valueRaw);
}

// FT_Fixed xy;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getXY
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->xy);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTMatrix_setXY
  (JNIEnv *, jclass, jlong matrixPtrRaw, jint valueRaw) {

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return;

    matrix->xy = static_cast<FT_Fixed>(valueRaw);
}

// FT_Fixed yx;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYX
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->yx);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTMatrix_setYX
  (JNIEnv *, jclass, jlong matrixPtrRaw, jint valueRaw) {

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return;

    matrix->yx = static_cast<FT_Fixed>(valueRaw);
}

// FT_Fixed yy;
JNIEXPORT jint JNICALL Java_generaloss_freetype_types_FTMatrix_getYY
  (JNIEnv *, jclass, jlong matrixPtrRaw) {

    const FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return 0;

    return static_cast<jint>(matrix->yy);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_types_FTMatrix_setYY
  (JNIEnv *, jclass, jlong matrixPtrRaw, jint valueRaw) {

    FT_Matrix* matrix = reinterpret_cast<FT_Matrix*>(matrixPtrRaw);
    if(!matrix)
        return;

    matrix->yy = static_cast<FT_Fixed>(valueRaw);
}
