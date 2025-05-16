#!/bin/bash

ARCHES=("x86_64" "i686") # "aarch64"

build_windows() {
    local arch=$1

    echo "Building for Windows $arch..."
    rm -rf "$arch"
    mkdir -p "$arch"
    cd "$arch"

    cmake ../../../ \
        -DTARGET_PLATFORM=windows \
        -DCMAKE_TOOLCHAIN_FILE="../../../../toolchains/windows-${arch}.cmake" \
        -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
        -DCMAKE_BUILD_TYPE=Release
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