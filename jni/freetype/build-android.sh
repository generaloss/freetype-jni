#!/bin/bash

ANDROID_NDK=/home/user/Dev/sdk/android/ndk/29.0.13113456
ANDROID_API_LEVEL=21
ANDROID_ABIS=("armeabi-v7a" "arm64-v8a" "x86" "x86_64")

DIR="$(pwd)"

build_android() {
    local abi=$1

    echo "Building for Android ${abi}..."
    rm -rf "${abi}"
    mkdir -p "${abi}"
    cd "${abi}"

    cmake ../../../ \
        -DANDROID_NDK="${ANDROID_NDK}" \
        -DCMAKE_TOOLCHAIN_FILE="${ANDROID_NDK}/build/cmake/android.toolchain.cmake" \
        -DANDROID_ABI="${abi}" \
        -DANDROID_NATIVE_API_LEVEL="${ANDROID_API_LEVEL}" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release \
        -DFT_DISABLE_ZLIB=FALSE \
        -DFT_DISABLE_BZIP2=TRUE \
        -DFT_DISABLE_PNG=FALSE \
        -DFT_DISABLE_HARFBUZZ=FALSE \
        -DFT_DISABLE_BROTLI=TRUE \
        -DZLIB_INCLUDE_DIR="${DIR}/../libs/zlib/include" \
        -DZLIB_LIBRARY="${DIR}/../libs/zlib/lib/android/${abi}/libz.a" \
        -DHarfBuzz_INCLUDE_DIR="${DIR}/../libs/harfbuzz/include/harfbuzz" \
        -DHarfBuzz_LIBRARY="${DIR}/../libs/harfbuzz/lib/android/${abi}/libharfbuzz.a" \
        -DPNG_PNG_INCLUDE_DIR="${DIR}/../libs/libpng/include" \
        -DPNG_LIBRARY="${DIR}/../libs/libpng/lib/android/${abi}/libpng.a"

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
    build_android "${abi}"
done