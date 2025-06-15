// typedef struct FT_Open_Args_ { ... } FT_Open_Args;

#include "freetype/FTOpenArgs.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_createPointer
  (JNIEnv *, jclass) {

    FT_Open_Args* pointer = new FT_Open_Args;
    memset(pointer, 0, sizeof(FT_Open_Args));
    return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_freePointer
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return;

    delete args;
}


// FT_UInt flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getFlags
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return 0;

    return static_cast<jint>(args->flags);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_setFlags
  (JNIEnv *, jclass, jlong argsPtrRaw, jint flags) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return;

    args->flags = static_cast<FT_UInt>(flags);
}

// const FT_Byte* memory_base;
JNIEXPORT jbyte JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getMemoryBase
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !args->memory_base)
        return 0;

    return static_cast<jbyte>(args->memory_base[0]);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_setMemoryBase
  (JNIEnv *env, jclass, jlong argsPtrRaw, jobject buffer, jlong size) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !buffer)
        return;

    void* base = env->GetDirectBufferAddress(buffer);
    if(!base)
        return;

    args->memory_base = reinterpret_cast<const FT_Byte*>(base);
    args->memory_size = static_cast<FT_Long>(size);
}

// FT_Long memory_size;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getMemorySize
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return 0;

    return static_cast<jlong>(args->memory_size);
}

// FT_String* pathname;
JNIEXPORT jstring JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getPathname
  (JNIEnv *env, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !args->pathname)
        return 0;

    return env->NewStringUTF(args->pathname);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_setPathname
  (JNIEnv *env, jclass, jlong argsPtrRaw, jstring pathname) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !pathname)
        return;

    const char* str = env->GetStringUTFChars(pathname, nullptr);
    if(!str)
        return;

    args->pathname = const_cast<FT_String*>(str);
}

// FT_Stream stream;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getStream
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !args->stream)
        return 0;

    return reinterpret_cast<jlong>(args->stream);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_setStream
  (JNIEnv *, jclass, jlong argsPtrRaw, jlong streamPtrRaw) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return;

    const FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    args->stream = stream;
}

// FT_Int num_params;
JNIEXPORT jint JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getNumParams
  (JNIEnv *, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args)
        return 0;

    return static_cast<jint>(args->num_params);
}

// FT_Parameter* params;
JNIEXPORT jlongArray JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_getParams
  (JNIEnv *env, jclass, jlong argsPtrRaw) {

    const FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !args->params)
        return NULL;

    const FT_Int count = args->num_params;
    if(count <= 0)
        return NULL;

    jlongArray array = env->NewLongArray(count);
    if(!array)
        return NULL;

    jlong* elements = new jlong[count];
    for(int i = 0; i < count; i++)
        elements[i] = reinterpret_cast<jlong>(&args->params[i]);

    env->SetLongArrayRegion(array, 0, count, elements);
    delete[] elements;

    return array;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTOpenArgs_setParams
  (JNIEnv *env, jclass, jlong argsPtrRaw, jlongArray paramPtrs) {

    FT_Open_Args* args = reinterpret_cast<FT_Open_Args*>(argsPtrRaw);
    if(!args || !paramPtrs)
        return;

    jsize length = env->GetArrayLength(paramPtrs);
    if(length <= 0)
        return;

    jlong* ptrs = env->GetLongArrayElements(paramPtrs, nullptr);
    if(!ptrs)
        return;

    args->params = reinterpret_cast<FT_Parameter*>(ptrs);
    args->num_params = static_cast<FT_Int>(length);

    env->ReleaseLongArrayElements(paramPtrs, ptrs, 0);
}