// typedef struct FT_Raster_Params_ { ..  } FT_Raster_Params;

#include "image/FTRasterParams.h"
#include <jni.h>
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_IMAGE_H


struct RasterCallbackContext {
    JavaVM* jvm = nullptr;
    jobject graySpans = nullptr;
    jobject blackSpans = nullptr;
    jobject bitTest = nullptr;
    jobject bitSet = nullptr;
};

// stubs
void ft_gray_spans_func_stub(int y, int count, const FT_Span* spans, void* user) { }
void ft_black_spans_func_stub(int y, int count, const FT_Span* spans, void* user) { }
int ft_bit_test_func_stub(int y, int x, void* user) {
    return 0;
}
void ft_bit_set_func_stub(int y, int x, void* user) { }


JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTRasterParams_createPointer
  (JNIEnv* env, jclass) {

    FT_Raster_Params* params = new FT_Raster_Params();

    params->gray_spans = ft_gray_spans_func_stub;
    params->black_spans = ft_black_spans_func_stub;
    params->bit_test = ft_bit_test_func_stub;
    params->bit_set = ft_bit_set_func_stub;

    RasterCallbackContext* callbacks = new RasterCallbackContext();
    env->GetJavaVM(&callbacks->jvm);
    params->user = callbacks;

    return reinterpret_cast<jlong>(params);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_freePointer
  (JNIEnv* env, jclass, jlong paramsPtrRaw) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(params->user);
    if(callbacks) {
        if(callbacks->graySpans) env->DeleteGlobalRef(callbacks->graySpans);
        if(callbacks->blackSpans) env->DeleteGlobalRef(callbacks->blackSpans);
        if(callbacks->bitTest) env->DeleteGlobalRef(callbacks->bitTest);
        if(callbacks->bitSet) env->DeleteGlobalRef(callbacks->bitSet);
        delete callbacks;
    }

    delete params;
}


// const FT_Bitmap* target;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTRasterParams_getTarget
  (JNIEnv *, jclass, jlong paramsPtrRaw) {

    const FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return 0;

    return reinterpret_cast<jlong>(params->target);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setTarget
  (JNIEnv *, jclass, jlong paramsPtrRaw, jlong targetPtrRaw) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    params->target = reinterpret_cast<FT_Bitmap*>(targetPtrRaw);
}

// const void* source;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTRasterParams_getSource
  (JNIEnv *, jclass, jlong paramsPtrRaw) {

    const FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return 0;

    return reinterpret_cast<jlong>(params->source);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setSource
  (JNIEnv *, jclass, jlong paramsPtrRaw, jlong sourcePtrRaw) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    params->source = reinterpret_cast<void*>(sourcePtrRaw);
}

// int flags;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTRasterParams_getFlags
  (JNIEnv *, jclass, jlong paramsPtrRaw) {

    const FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return 0;

    return static_cast<jint>(params->flags);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setFlags
  (JNIEnv *, jclass, jlong paramsPtrRaw, jint flagsRaw) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    params->flags = static_cast<int>(flagsRaw);
}

// FT_SpanFunc gray_spans;
void ft_gray_spans_func(int y, int count, const FT_Span* spans, void* user) {

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(user);
    if(!callbacks || !callbacks->graySpans)
        return;

    JNIEnv* env = nullptr;
    if(callbacks->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return;

    jclass cls = env->GetObjectClass(callbacks->graySpans);
    jmethodID methodID = env->GetMethodID(cls, "invokeNative", "(II[J)V");
    if(!methodID)
        return;

    const jlongArray spansArray = env->NewLongArray(count);
    if(!spansArray)
        return;

    jlong* spansPointers = new jlong[count];
    for(int i = 0; i < count; i++)
        spansPointers[i] = reinterpret_cast<jlong>(&spans[i]);

    env->SetLongArrayRegion(spansArray, 0, count, spansPointers);

    env->CallVoidMethod(callbacks->graySpans, methodID, (jint) y, (jlong) count, spansArray);

    env->DeleteLocalRef(spansArray);
    env->DeleteLocalRef(cls);

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setGraySpans
  (JNIEnv* env, jclass, jlong paramsPtrRaw, jobject javaGraySpansFunc) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(params->user);
    if(!callbacks)
        return;

    if(callbacks->graySpans)
        env->DeleteGlobalRef(callbacks->graySpans);
    callbacks->graySpans = env->NewGlobalRef(javaGraySpansFunc);

    params->gray_spans = ft_gray_spans_func;
}

// FT_SpanFunc black_spans;
void ft_black_spans_func(int y, int count, const FT_Span* spans, void* user) {

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(user);
    if(!callbacks || !callbacks->blackSpans)
        return;

    JNIEnv* env = nullptr;
    if(callbacks->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return;

    jclass cls = env->GetObjectClass(callbacks->blackSpans);
    jmethodID methodID = env->GetMethodID(cls, "invokeNative", "(II[J)V");
    if(!methodID)
        return;

    const jlongArray spansArray = env->NewLongArray(count);
    if(!spansArray)
        return;

    jlong* spansPointers = new jlong[count];
    for(int i = 0; i < count; i++)
        spansPointers[i] = reinterpret_cast<jlong>(&spans[i]);

    env->SetLongArrayRegion(spansArray, 0, count, spansPointers);

    env->CallVoidMethod(callbacks->blackSpans, methodID, (jint) y, (jlong) count, spansArray);

    env->DeleteLocalRef(spansArray);
    env->DeleteLocalRef(cls);

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setBlackSpans
  (JNIEnv* env, jclass, jlong paramsPtrRaw, jobject javaBlackSpansFunc) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(params->user);
    if(!callbacks)
        return;

    if(callbacks->blackSpans)
        env->DeleteGlobalRef(callbacks->blackSpans);
    callbacks->blackSpans = env->NewGlobalRef(javaBlackSpansFunc);

    params->black_spans = ft_black_spans_func;
}

// FT_Raster_BitTest_Func bit_test;
int ft_bit_test_func(int y, int x, void* user) {

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(user);
    if(!callbacks || !callbacks->bitTest)
    return 0;

    JNIEnv* env = nullptr;
    if(callbacks->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return 0;

    jclass cls = env->GetObjectClass(callbacks->bitTest);
    jmethodID methodID = env->GetMethodID(cls, "invoke", "(II)I");
    if(!methodID)
        return 0;

    const jint result = env->CallIntMethod(callbacks->bitTest, methodID, (jint) x, (jint) y);

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return 0;
    }

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setBitTest
  (JNIEnv* env, jclass, jlong paramsPtrRaw, jobject javaBitTestFunc) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(params->user);
    if(!callbacks)
        return;

    if(callbacks->bitTest)
        env->DeleteGlobalRef(callbacks->bitTest);
    callbacks->bitTest = env->NewGlobalRef(javaBitTestFunc);

    params->bit_test = ft_bit_test_func;
}

// FT_Raster_BitSet_Func bit_set;
void ft_bit_set_func(int y, int x, void* user) {

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(user);
    if(!callbacks || !callbacks->bitSet)
        return;

    JNIEnv* env = nullptr;
    if(callbacks->jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return;

    jclass cls = env->GetObjectClass(callbacks->bitSet);
    jmethodID methodID = env->GetMethodID(cls, "invoke", "(II)V");
    if(!methodID)
        return;

    env->CallVoidMethod(callbacks->bitSet, methodID, (jint) x, (jint) y);

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTRasterParams_setBitSet
  (JNIEnv* env, jclass, jlong paramsPtrRaw, jobject javaBitSetFunc) {

    FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return;

    RasterCallbackContext* callbacks = reinterpret_cast<RasterCallbackContext*>(params->user);
    if(!callbacks)
        return;

    if(callbacks->bitSet)
        env->DeleteGlobalRef(callbacks->bitSet);
    callbacks->bitSet = env->NewGlobalRef(javaBitSetFunc);

    params->bit_set = ft_bit_set_func;
}

// FT_BBox clip_box;
JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTRasterParams_getClipBox
  (JNIEnv *, jclass, jlong paramsPtrRaw) {

    const FT_Raster_Params* params = reinterpret_cast<FT_Raster_Params*>(paramsPtrRaw);
    if(!params)
        return 0;

    return reinterpret_cast<jlong>(&params->clip_box);
}
