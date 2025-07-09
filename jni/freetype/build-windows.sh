#!/bin/bash

ARCHES=("x86_64" "x86")

DIR="$(pwd)"
LIBS_DIR="${DIR}/../libs"

build_windows() {
    local arch=$1

    echo "Building for Windows $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=windows \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/windows/${arch}.cmake" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release \
        -DFT_DISABLE_ZLIB=FALSE \
        -DZLIB_INCLUDE_DIR="${LIBS_DIR}/zlib/include" \
        -DZLIB_LIBRARY="${LIBS_DIR}/zlib/lib/windows/${arch}/libz.a" \
        -DFT_DISABLE_BZIP2=TRUE \
        -DFT_DISABLE_PNG=FALSE \
        -DPNG_PNG_INCLUDE_DIR="${LIBS_DIR}/libpng/include" \
        -DPNG_LIBRARY="${LIBS_DIR}/libpng/lib/windows/${arch}/libpng.a" \
        -DFT_DISABLE_HARFBUZZ=FALSE \
        -DHarfBuzz_INCLUDE_DIR="${LIBS_DIR}/harfbuzz/include/harfbuzz" \
        -DHarfBuzz_LIBRARY="${LIBS_DIR}/harfbuzz/lib/windows/${arch}/libharfbuzz.a" \
        -DFT_DISABLE_BROTLI=TRUE

    make -j$(nproc)

    cd ../
    mkdir "${DIR}/lib/windows/$arch"
    cp "$arch/libfreetype.a" "${DIR}/lib/windows/$arch/libfreetype.a"
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