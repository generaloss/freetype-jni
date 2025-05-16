#!/bin/bash

ANDROID_NDK=/home/user/Dev/sdk/android/ndk/29.0.13113456
ANDROID_API_LEVEL=21
ANDROID_ABIS=("armeabi-v7a" "arm64-v8a" "x86" "x86_64")

build_android() {
    local abi=$1

    echo "Building for Android $abi..."
    rm -rf "$abi"
    mkdir -p "$abi"
    cd "$abi"

    cmake ../../../ \
        -DANDROID_NDK="$ANDROID_NDK" \
        -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" \
        -DANDROID_ABI="$abi" \
        -DANDROID_NATIVE_API_LEVEL="${ANDROID_API_LEVEL}" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release
    make -j$(nproc)

    cd ../
    mkdir "../../lib/android/$abi"
    cp "$abi/libfreetype.a" "../../lib/android/$abi/libfreetype.a"
}

set -e

rm -rf lib/android
mkdir -p lib
mkdir lib/android/

mkdir -p build
cd build
mkdir -p android
cd android/

for abi in "${ANDROID_ABIS[@]}"; do
    build_android "$abi"
done