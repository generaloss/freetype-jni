#!/bin/bash

ARCHES=("x86_64" "x86") # "aarch64"

DIR="$(pwd)"

build_windows() {
    local arch=$1

    echo "Building for Windows $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake ../../../ \
        -DTARGET_PLATFORM=windows \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/windows-${arch}.cmake" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release \
        -DFT_DISABLE_ZLIB=FALSE \
        -DZLIB_INCLUDE_DIR="${DIR}/../libs/zlib/include" \
        -DZLIB_LIBRARY="${DIR}/../libs/zlib/lib/windows/${arch}/libz.a" \
        -DFT_DISABLE_BZIP2=TRUE \
        -DFT_DISABLE_PNG=FALSE \
        -DPNG_PNG_INCLUDE_DIR="${DIR}/../libs/libpng/include" \
        -DPNG_LIBRARY="${DIR}/../libs/libpng/lib/windows/${arch}/libpng.a" \
        -DFT_DISABLE_HARFBUZZ=FALSE \
        -DHarfBuzz_INCLUDE_DIR="${DIR}/../libs/harfbuzz/include/harfbuzz" \
        -DHarfBuzz_LIBRARY="${DIR}/../libs/harfbuzz/lib/windows/${arch}/libharfbuzz.a" \
        -DFT_DISABLE_BROTLI=TRUE

    make -j$(nproc)

    cd ../
    mkdir "../../lib/windows/$arch"
    cp "$arch/libfreetype.a" "../../lib/windows/$arch/libfreetype.a"
}

set -e

rm -rf lib/windows
mkdir -p lib
mkdir lib/windows/

mkdir -p build
cd build
mkdir -p windows
cd windows/

for arch in "${ARCHES[@]}"; do
    build_windows "$arch"
done