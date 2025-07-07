#!/bin/bash

ARCHES=("x86_64" "x86") # "aarch64"

build_windows() {
    local arch=$1

    echo "Building for Windows $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake ../../../ \
        -DTARGET_PLATFORM=windows \
        -DCMAKE_TOOLCHAIN_FILE="../../../toolchains/windows-$arch.cmake" \
        -DCMAKE_BUILD_TYPE=Release \
        -DTARGET_ARCH="$arch"
    make -j$(nproc)

    cd ../
    mkdir "../../out/windows/$arch"
    cp "$arch/freetype_jni.dll" "../../out/windows/$arch/freetype_jni.dll"
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