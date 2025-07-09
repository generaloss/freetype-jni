#!/bin/bash

ANDROID_NDK=/home/user/Dev/sdk/android/ndk/29.0.13113456
ANDROID_API_LEVEL=21
ANDROID_ABIS=("armeabi-v7a" "arm64-v8a" "x86" "x86_64")

DIR="$(pwd)"

build_android() {
    local abi=$1

    echo "Building for Android $abi..."
    rm -rf "$abi"
    mkdir -p "$abi"
    cd "$abi"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=android \
        -DANDROID_NDK="$ANDROID_NDK" \
        -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" \
        -DANDROID_ABI="$abi" \
        -DANDROID_NATIVE_API_LEVEL="$ANDROID_API_LEVEL" \
        -DCMAKE_BUILD_TYPE=Release \
        -DTARGET_ARCH="$abi"
    make -j$(nproc)

    cd ../
    mkdir "${DIR}/out/android/$abi"
    cp "$abi/libfreetype_jni.so" "${DIR}/out/android/$abi/libfreetype_jni.so"
}

set -e

rm -rf out/android
mkdir -p out
mkdir out/android/

mkdir -p build
cd build
mkdir -p android
cd android/

for i in "${!ANDROID_ABIS[@]}"; do
    abi="${ANDROID_ABIS[$i]}"
    build_android "$abi"
done