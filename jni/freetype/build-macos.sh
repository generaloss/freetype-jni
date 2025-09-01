#!/bin/bash

ARCHES=("x86_64" "aarch64")
ARCHES_CMAKE=("x86_64" "arm64")

DIR="$(pwd)"

build_macos() {
    local arch=$1
    local arch_cmake=$2

    echo "Building for MacOS ${arch_cmake}..."
    rm -rf "${arch}"
    mkdir -p "${arch}"
    cd "${arch}"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=macos \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/macos/${arch_cmake}.cmake" \
        -DCMAKE_OSX_ARCHITECTURES="${arch_cmake}" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release \
        -DFT_DISABLE_ZLIB=FALSE \
        -DFT_DISABLE_BZIP2=TRUE \
        -DFT_DISABLE_PNG=FALSE \
        -DFT_DISABLE_HARFBUZZ=FALSE \
        -DFT_DISABLE_BROTLI=TRUE

    make -j$(sysctl -n hw.ncpu)

    cd ../
    mkdir "${DIR}/lib/macos/$arch"
    cp "$arch/libfreetype.a" "${DIR}/lib/macos/$arch/libfreetype.a"

    printf "Done.\n\n"
}

set -e

rm -rf lib/macos
mkdir -p lib
mkdir lib/macos/

mkdir -p build
cd build
mkdir -p macos
cd macos/

for i in "${!ARCHES[@]}"; do
    build_macos "${ARCHES[${i}]}" "${ARCHES_CMAKE[${i}]}"
done