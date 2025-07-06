#!/bin/bash

ANDROID_NDK=/opt/android-ndk # /home/user/Dev/sdk/android/ndk/29.0.13599879 #
ANDROID_API_LEVEL=21
ANDROID_ABIS=("armeabi-v7a" "arm64-v8a" "x86" "x86_64")

DIR="$(pwd)"

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
        -DPNG_PNG_INCLUDE_DIR="$DIR/../libs/libpng/include" \
        -DPNG_LIBRARY="$DIR/../libs/libpng/lib/android/$abi/libpng.a" \
        -DCMAKE_C_FLAGS="-I$DIR/../libs/harfbuzz/include/harfbuzz" \
        -DCMAKE_EXE_LINKER_FLAGS="$DIR/../libs/harfbuzz/lib/android/$abi/libharfbuzz.a" \
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

for i in "${!ANDROID_ABIS[@]}"; do
    abi="${ANDROID_ABIS[$i]}"
    build_android "$abi"
done