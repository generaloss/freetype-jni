#!/bin/bash

ARCHES=("x86_64" "aarch64")
ARCHES_CMAKE=("x86_64" "arm64")

DIR="$(pwd)"

build_macos() {
    local arch=$1
    local arch_cmake=$2

    echo "Building for MacOS $arch_cmake..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=macos \
        -DCMAKE_OSX_ARCHITECTURES="${arch_cmake}" \
        -DCMAKE_BUILD_TYPE=Release \
        -DTARGET_ARCH="$arch"
    make -j$(nproc)

    cd ../
    mkdir "${DIR}/out/macos/$arch"
    cp "$arch/libfreetype_jni.dylib" "${DIR}/out/macos/$arch/libfreetype_jni.dylib"

    printf "Done.\n\n"
}

set -e

rm -rf out/macos
mkdir -p out
mkdir out/macos/

mkdir -p build
cd build
mkdir -p macos
cd macos/

for i in "${!ARCHES[@]}"; do
    build_macos "${ARCHES[${i}]}" "${ARCHES_CMAKE[${i}]}"
done