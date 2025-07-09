#!/bin/bash

ARCHES=("x86_64" "aarch64") # "riscv64" "arm32" "powerpc64"

DIR="$(pwd)"

build_linux() {
    local arch=$1

    echo "Building for Linux $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake "${DIR}" \
        -DTARGET_PLATFORM=linux \
        -DCMAKE_TOOLCHAIN_FILE="${DIR}/../toolchains/linux/$arch.cmake" \
        -DCMAKE_BUILD_TYPE=Release \
        -DTARGET_ARCH="$arch"
    make -j$(nproc)

    cd ../
    mkdir "${DIR}/out/linux/$arch"
    cp "$arch/libfreetype_jni.so" "${DIR}/out/linux/$arch/libfreetype_jni.so"
}

set -e

rm -rf out/linux
mkdir -p out
mkdir out/linux/

mkdir -p build
cd build
mkdir -p linux
cd linux/

for arch in "${ARCHES[@]}"; do
    build_linux "$arch"
done