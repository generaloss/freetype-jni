#!/bin/bash

ARCHES=("x86_64" "aarch64") # "riscv64" "arm32" "powerpc64"

DIR="$(pwd)"

build_linux() {
    local arch=$1

    echo "Building for Linux ${arch}..."
    rm -rf "${arch}"
    mkdir -p "${arch}"
    cd "${arch}"

    cmake ../../../ \
        -DTARGET_PLATFORM=linux \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/linux-${arch}.cmake" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release \
        -DFT_DISABLE_ZLIB=FALSE \
        -DFT_DISABLE_BZIP2=TRUE \
        -DFT_DISABLE_PNG=FALSE \
        -DFT_DISABLE_HARFBUZZ=FALSE \
        -DFT_DISABLE_BROTLI=TRUE

    make -j$(nproc)

    cd ../
    mkdir "../../lib/linux/$arch"
    cp "$arch/libfreetype.a" "../../lib/linux/$arch/libfreetype.a"
}

set -e

rm -rf lib/linux
mkdir -p lib
mkdir lib/linux/

mkdir -p build
cd build
mkdir -p linux
cd linux/

for arch in "${ARCHES[@]}"; do
    build_linux "${arch}"
done