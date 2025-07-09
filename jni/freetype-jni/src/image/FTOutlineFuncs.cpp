// typedef struct FT_Outline_Funcs_ { ... } FT_Outline_Funcs;

#include "image/FTOutlineFuncs.h"
#include <jni.h>
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_OUTLINE_H
#include <unordered_map>
#include <mutex>


// jvm
JavaVM* jvmGlobal = nullptr;

// move_to
std::unordered_map<FT_Outline_Funcs*, jobject> moveToCallbackMap;
std::mutex moveToCallbackMapMutex;

int ft_move_to_func_stub(const FT_Vector* to, void* user) {
    return 0;
}

// line_to
std::unordered_map<FT_Outline_Funcs*, jobject> lineToCallbackMap;
std::mutex lineToCallbackMapMutex;

int ft_line_to_func_stub(const FT_Vector* to, void* user) {
    return 0;
}

// conic_to
std::unordered_map<FT_Outline_Funcs*, jobject> conicToCallbackMap;
std::mutex conicToCallbackMapMutex;

int ft_conic_to_func_stub(const FT_Vector* control, const FT_Vector* to, void* user) {
    return 0;
}

// cubic_to
std::unordered_map<FT_Outline_Funcs*, jobject> cubicToCallbackMap;
std::mutex cubicToCallbackMapMutex;

int ft_cubic_to_func_stub(const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user) {
    return 0;
}


JNIEXPORT jlong JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_createPointer
  (JNIEnv *, jclass) {

    FT_Outline_Funcs* pointer = new FT_Outline_Funcs();
    pointer->move_to = ft_move_to_func_stub;
    pointer->line_to = ft_line_to_func_stub;
    pointer->conic_to = ft_conic_to_func_stub;
    pointer->cubic_to = ft_cubic_to_func_stub;
    return reinterpret_cast<jlong>(pointer);
}

void deleteMappedFuncCallback(JNIEnv* env, FT_Outline_Funcs* funcs, std::unordered_map<FT_Outline_Funcs*, jobject>& map, std::mutex& mapMutex) {
    jobject callbackToDelete = nullptr;

    std::lock_guard<std::mutex> lock(mapMutex);
    auto it = map.find(funcs);

    if(it != map.end()) {
        callbackToDelete = it->second;
        map.erase(it);
    }

    if(callbackToDelete)
        env->DeleteGlobalRef(callbackToDelete);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_freePointer
  (JNIEnv* env, jclass, jlong funcsPtrRaw) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);

    deleteMappedFuncCallback(env, funcs, moveToCallbackMap, moveToCallbackMapMutex);
    deleteMappedFuncCallback(env, funcs, lineToCallbackMap, lineToCallbackMapMutex);
    deleteMappedFuncCallback(env, funcs, conicToCallbackMap, conicToCallbackMapMutex);
    deleteMappedFuncCallback(env, funcs, cubicToCallbackMap, cubicToCallbackMapMutex);

    delete funcs;
}


// FT_Outline_MoveToFunc move_to;
int ft_move_to_func(const FT_Vector* to, void* user) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(user);

    jobject javaMoveToFunc = nullptr;
    std::lock_guard<std::mutex> lock(moveToCallbackMapMutex);
    auto it = moveToCallbackMap.find(funcs);
    if(it != moveToCallbackMap.end())
        javaMoveToFunc = it->second;
    if(!javaMoveToFunc)
        return 0;

    JNIEnv* env = nullptr;
    if(jvmGlobal->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return -1;

    const jclass callbackClass = env->GetObjectClass(javaMoveToFunc);
    const jmethodID invokeMethod = env->GetMethodID(callbackClass, "invokeNative", "(J)I");

    const jint result = env->CallIntMethod(
        javaMoveToFunc,
        invokeMethod,
        reinterpret_cast<jlong>(to)
    );

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return -1;
    }

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setMoveTo
  (JNIEnv* env, jclass, jlong funcsPtrRaw, jobject javaMoveToFunc) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    if(!jvmGlobal)
        env->GetJavaVM(&jvmGlobal);

    const jobject javaMoveToFuncGlobal = env->NewGlobalRef(javaMoveToFunc);
    std::lock_guard<std::mutex> lock(moveToCallbackMapMutex);

    auto it = moveToCallbackMap.find(funcs);
    if(it != moveToCallbackMap.end())
        env->DeleteGlobalRef(it->second);

    moveToCallbackMap[funcs] = javaMoveToFuncGlobal;

    funcs->move_to = ft_move_to_func;
}

// FT_Outline_LineToFunc line_to;
int ft_line_to_func(const FT_Vector* to, void* user) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(user);

    jobject javaLineToFunc = nullptr;
    std::lock_guard<std::mutex> lock(lineToCallbackMapMutex);
    auto it = lineToCallbackMap.find(funcs);
    if(it != lineToCallbackMap.end())
        javaLineToFunc = it->second;
    if(!javaLineToFunc)
        return 0;

    JNIEnv* env = nullptr;
    if(jvmGlobal->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
       return -1;

    const jclass callbackClass = env->GetObjectClass(javaLineToFunc);
    const jmethodID invokeMethod = env->GetMethodID(callbackClass, "invokeNative", "(J)I");

    const jint result = env->CallIntMethod(
        javaLineToFunc,
        invokeMethod,
        reinterpret_cast<jlong>(to)
    );

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return -1;
    }

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setLineTo
  (JNIEnv* env, jclass, jlong funcsPtrRaw, jobject javaLineToFunc) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    if(!jvmGlobal)
        env->GetJavaVM(&jvmGlobal);

    const jobject javaLineToFuncGlobal = env->NewGlobalRef(javaLineToFunc);
    std::lock_guard<std::mutex> lock(lineToCallbackMapMutex);

    auto it = lineToCallbackMap.find(funcs);
    if(it != lineToCallbackMap.end())
        env->DeleteGlobalRef(it->second);

    lineToCallbackMap[funcs] = javaLineToFuncGlobal;

    funcs->line_to = ft_line_to_func;
}

// FT_Outline_ConicToFunc conic_to;
int ft_conic_to_func(const FT_Vector* control, const FT_Vector* to, void* user) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(user);

    jobject javaConicToFunc = nullptr;
    std::lock_guard<std::mutex> lock(conicToCallbackMapMutex);
    auto it = conicToCallbackMap.find(funcs);
    if(it != conicToCallbackMap.end())
        javaConicToFunc = it->second;
    if(!javaConicToFunc)
        return 0;

    JNIEnv* env = nullptr;
    if(jvmGlobal->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
       return -1;

    const jclass callbackClass = env->GetObjectClass(javaConicToFunc);
    const jmethodID invokeMethod = env->GetMethodID(callbackClass, "invokeNative", "(JJ)I");

    const jint result = env->CallIntMethod(
        javaConicToFunc,
        invokeMethod,
        reinterpret_cast<jlong>(control),
        reinterpret_cast<jlong>(to)
    );

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return -1;
    }

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setConicTo
  (JNIEnv* env, jclass, jlong funcsPtrRaw, jobject javaConicToFunc) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    if(!jvmGlobal)
        env->GetJavaVM(&jvmGlobal);

    const jobject javaConicToFuncGlobal = env->NewGlobalRef(javaConicToFunc);
    std::lock_guard<std::mutex> lock(conicToCallbackMapMutex);

    auto it = conicToCallbackMap.find(funcs);
    if(it != conicToCallbackMap.end())
        env->DeleteGlobalRef(it->second);

    conicToCallbackMap[funcs] = javaConicToFuncGlobal;

    funcs->conic_to = ft_conic_to_func;
}

// FT_Outline_CubicToFunc cubic_to;
int ft_cubic_to_func(const FT_Vector* control1, const FT_Vector* control2, const FT_Vector* to, void* user) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(user);

    jobject javaCubicToFunc = nullptr;
    std::lock_guard<std::mutex> lock(cubicToCallbackMapMutex);
    auto it = cubicToCallbackMap.find(funcs);
    if(it != cubicToCallbackMap.end())
        javaCubicToFunc = it->second;
    if(!javaCubicToFunc)
        return 0;

    JNIEnv* env = nullptr;
    if(jvmGlobal->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr) != 0)
        return -1;

    const jclass callbackClass = env->GetObjectClass(javaCubicToFunc);
    const jmethodID invokeMethod = env->GetMethodID(callbackClass, "invokeNative", "(JJJ)I");

    const jint result = env->CallIntMethod(
        javaCubicToFunc,
        invokeMethod,
        reinterpret_cast<jlong>(control1),
        reinterpret_cast<jlong>(control2),
        reinterpret_cast<jlong>(to)
    );

    if(env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return -1;
    }

    return result;
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setCubicTo
  (JNIEnv* env, jclass, jlong funcsPtrRaw, jobject javaCubicToFunc) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    if(!jvmGlobal)
        env->GetJavaVM(&jvmGlobal);

    const jobject javaCubicToFuncGlobal = env->NewGlobalRef(javaCubicToFunc);
    std::lock_guard<std::mutex> lock(cubicToCallbackMapMutex);

    auto it = cubicToCallbackMap.find(funcs);
    if(it != cubicToCallbackMap.end())
        env->DeleteGlobalRef(it->second);

    cubicToCallbackMap[funcs] = javaCubicToFuncGlobal;

    funcs->cubic_to = ft_cubic_to_func;
}

// int shift;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_getShift
  (JNIEnv *, jclass, jlong funcsPtrRaw) {

    const FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return 0;

    return static_cast<jint>(funcs->shift);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setShift
  (JNIEnv *, jclass, jlong funcsPtrRaw, jint shiftRaw) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->shift = static_cast<int>(shiftRaw);
}

// FT_Pos delta;
JNIEXPORT jint JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_getDelta
  (JNIEnv *, jclass, jlong funcsPtrRaw) {

    const FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return 0;

    return static_cast<jint>(funcs->delta);
}

JNIEXPORT void JNICALL Java_generaloss_freetype_image_FTOutlineFuncs_setDelta
  (JNIEnv *, jclass, jlong funcsPtrRaw, jint deltaRaw) {

    FT_Outline_Funcs* funcs = reinterpret_cast<FT_Outline_Funcs*>(funcsPtrRaw);
    if(!funcs)
        return;

    funcs->delta = static_cast<int>(deltaRaw);
}