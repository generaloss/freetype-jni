#!/bin/bash

ARCHES=("x86_64" "x86") # "aarch64"

DIR="$(pwd)"

build_windows() {
    local arch=$1

    echo "Building for Windows $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=windows \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/windows/$arch.cmake" \
        -DCMAKE_BUILD_TYPE=Release \
        -DTARGET_ARCH="$arch"
    make -j$(nproc)

    cd ../
    mkdir "${DIR}/out/windows/$arch"
    cp "$arch/freetype_jni.dll" "${DIR}/out/windows/$arch/freetype_jni.dll"

    printf "Done.\n\n"
}

set -e

rm -rf out/windows
mkdir -p out
mkdir out/windows/

mkdir -p build
cd build
mkdir -p windows
cd windows/

for arch in "${ARCHES[@]}"; do
    build_windows "$arch"
done