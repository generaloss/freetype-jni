#!/bin/bash

set -e

TARGET_HEADERS_DIR='../target/headers/'
INCLUDE_DIR='./include/'

if [ ! -d "$TARGET_HEADERS_DIR" ]; then
    echo "Compile headers first."
    exit 1
fi

rm -rf "$INCLUDE_DIR"*

for header_file in "$TARGET_HEADERS_DIR"*; do
    if [ -f "$header_file" ]; then
        filename=$(basename "$header_file")

        if [[ $filename =~ ^generaloss_freetype_([^_]+)_([^_]+)\.h$ ]]; then
            package_name="${BASH_REMATCH[1]}"
            header_name="${BASH_REMATCH[2]}"

            target_dir="$INCLUDE_DIR$package_name"
            mkdir -p "$target_dir"
            cp "$header_file" "$target_dir/$header_name.h"
            echo "Copied $filename to $target_dir/$header_name.h"

        elif [[ $filename =~ ^generaloss_freetype_([^_]+)\.h$ ]]; then
            header_name="${BASH_REMATCH[1]}"
            cp "$header_file" "$INCLUDE_DIR/$header_name.h"
            echo "Copied $filename to $INCLUDE_DIR/$header_name.h"

        else
            echo "Skipped $filename (doesn't match any pattern)"
        fi
    fi
done
