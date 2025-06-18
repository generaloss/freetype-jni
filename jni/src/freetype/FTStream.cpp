// typedef struct FT_StreamRec_* FT_Stream;

#include "freetype/FTStream.h"
#include <ft2build.h>
#include FT_FREETYPE_H


struct StreamUserData {
    jobject readCallback = nullptr;
    jobject closeCallback = nullptr;
    JavaVM* jvm = nullptr; // чтобы получить JNIEnv в потоках
};


JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTStream_createPointer
  (JNIEnv *env, jclass) {

    FT_Stream stream = new FT_StreamRec();
    memset(stream, 0, sizeof(FT_StreamRec));
    return reinterpret_cast<jlong>(stream);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTStream_freePointer
  (JNIEnv *, jclass, jlong streamPtrRaw) {

    FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    auto* userData = static_cast<StreamUserData*>(stream->descriptor.pointer);
    if(userData) {
        JNIEnv* env;
        userData->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr);

        if(userData->readCallback)
            env->DeleteGlobalRef(userData->readCallback);
        if(userData->closeCallback)
            env->DeleteGlobalRef(userData->closeCallback);

        delete userData;
    }

    delete stream;
}

// unsigned long size;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTStream_getSize
  (JNIEnv *, jclass, jlong streamPtrRaw) {

    const FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return 0;

    return static_cast<jlong>(stream->size);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTStream_setSize
  (JNIEnv *, jclass, jlong streamPtrRaw, jlong sizeRaw) {

    FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    stream->size = static_cast<unsigned long>(sizeRaw);
}

// unsigned long pos;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTStream_getPos
  (JNIEnv *, jclass, jlong streamPtrRaw) {

    const FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return 0;

    return static_cast<jlong>(stream->pos);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTStream_setPos
  (JNIEnv *, jclass, jlong streamPtrRaw, jlong posRaw) {

    FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    stream->pos = static_cast<unsigned long>(posRaw);
}

// FT_Stream_IoFunc read;
unsigned long stream_read(FT_Stream stream, unsigned long offset, unsigned char* buffer, unsigned long count) {
    auto* userData = static_cast<StreamUserData*>(stream->descriptor.pointer);
    if(!userData || !userData->readCallback)
        return 0;

    JNIEnv* env = nullptr;
    userData->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr);

    jclass callbackClass = env->GetObjectClass(userData->readCallback);
    jmethodID readMethod = env->GetMethodID(callbackClass, "read", "(J[BJ)J");
    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return 0;
    }

    jbyteArray byteArray = env->NewByteArray((jsize)count);
    jlong bytesRead = env->CallLongMethod(userData->readCallback, readMethod, (jlong) offset, byteArray, (jlong) count);
    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        env->DeleteLocalRef(byteArray);
        return 0;
    }

    if(bytesRead > 0)
        env->GetByteArrayRegion(byteArray, 0, (jsize)bytesRead, reinterpret_cast<jbyte*>(buffer));

    env->DeleteLocalRef(byteArray);
    return static_cast<unsigned long>(bytesRead);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTStream_setRead
  (JNIEnv *env, jclass, jlong streamPtrRaw, jobject javaReadFunc) {

    FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    auto* userData = static_cast<StreamUserData*>(stream->descriptor.pointer);
    if(!userData) {
        userData = new StreamUserData();
        env->GetJavaVM(&userData->jvm);
        stream->descriptor.pointer = userData;
    }

    userData->readCallback = env->NewGlobalRef(javaReadFunc);
    stream->read = stream_read;
}

// FT_Stream_CloseFunc close;
void stream_close(FT_Stream stream) {
    auto* userData = static_cast<StreamUserData*>(stream->descriptor.pointer);
    if(!userData || !userData->closeCallback)
        return;

    JNIEnv* env = nullptr;
    userData->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr);

    jclass callbackClass = env->GetObjectClass(userData->closeCallback);
    jmethodID closeMethod = env->GetMethodID(callbackClass, "close", "()V");

    env->CallVoidMethod(userData->closeCallback, closeMethod);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_freetype_FTStream_setClose
  (JNIEnv *env, jclass, jlong streamPtrRaw, jobject javaCloseFunc) {

    FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return;

    auto* userData = static_cast<StreamUserData*>(stream->descriptor.pointer);
    if(!userData) {
        userData = new StreamUserData();
        env->GetJavaVM(&userData->jvm);
        stream->descriptor.pointer = userData;
    }

    userData->closeCallback = env->NewGlobalRef(javaCloseFunc);
    stream->close = stream_close;
}

// FT_Memory memory;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_freetype_FTStream_getMemory
  (JNIEnv *, jclass, jlong streamPtrRaw) {

    const FT_Stream stream = reinterpret_cast<FT_Stream>(streamPtrRaw);
    if(!stream)
        return 0;

    return reinterpret_cast<jlong>(&stream->memory);
}