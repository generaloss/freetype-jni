cmake_minimum_required(VERSION 3.10)
project(freetype_jni)

# java headers
set(JAVA_HOME "/usr/lib/jvm/java-8-openjdk") # windows => set(JAVA_HOME "C:/Program Files/Java/jdk-8")
include_directories("${JAVA_HOME}/include/linux" "${JAVA_HOME}/include") # windows => include_directories("${JAVA_HOME}/include/win32" "${JAVA_HOME}/include")

# jni

# file(GLOB_RECURSE SOURCE_FILES src/*.*) - Sometimes don't work
set(SOURCE_FILES
  src/FreeType.cpp
  src/freetype/FTBitmapSize.cpp
  src/freetype/FTCharMap.cpp
  src/freetype/FTFace.cpp
  src/freetype/FTGlyphMetrics.cpp
  src/freetype/FTGlyphSlot.cpp
  src/freetype/FTLibrary.cpp
  src/freetype/FTOpenArgs.cpp
  src/freetype/FTParameter.cpp
  src/freetype/FTSize.cpp
  src/freetype/FTSizeMetrics.cpp
  src/freetype/FTSizeRequest.cpp
  src/freetype/FTStream.cpp
  src/gload/FTGlyphLoad.cpp
  src/gload/FTGlyphLoader.cpp
  src/gload/FTSubGlyph.cpp
  src/glyph/FTBitmapGlyph.cpp
  src/glyph/FTGlyph.cpp
  src/image/FTBitmap.cpp
  src/image/FTOutline.cpp
  src/image/FTOutlineFuncs.cpp
  src/image/FTRasterParams.cpp
  src/image/FTSpan.cpp
  src/types/FTBBox.cpp
  src/types/FTF26Dot6.cpp
  src/types/FTFixed.cpp
  src/types/FTMatrix.cpp
  src/types/FTPos.cpp
  src/types/FTVector.cpp
)

add_library(freetype_jni SHARED ${SOURCE_FILES})
include_directories("include")

# freetype
add_library(freetype STATIC IMPORTED GLOBAL)
set_target_properties(freetype PROPERTIES
    IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/../freetype/lib/${TARGET_PLATFORM}/${TARGET_ARCH}/libfreetype.a"
    INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/../freetype/include"
)

set(LIBS_DIR "${CMAKE_SOURCE_DIR}/../libs")

if(TARGET_PLATFORM STREQUAL "android")
    message(STATUS "Configuring for Android")

    # z
    add_library(z STATIC IMPORTED GLOBAL)
    set_target_properties(z PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/zlib/lib/android/${ANDROID_ABI}/libz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/zlib/include"
    )

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/libpng/lib/android/${ANDROID_ABI}/libpng.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/harfbuzz/lib/android/${ANDROID_ABI}/libharfbuzz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/harfbuzz/include/harfbuzz"
    )

    # link
    target_link_libraries(freetype_jni PRIVATE
        freetype
        harfbuzz
        libpng
        z
    )
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".so"
    )

elseif(TARGET_PLATFORM STREQUAL "windows")
    message(STATUS "Configuring for Windows")

    # z
    add_library(z STATIC IMPORTED GLOBAL)
    set_target_properties(z PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/zlib/lib/windows/${TARGET_ARCH}/libz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/zlib/include"
    )

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/libpng/lib/windows/${TARGET_ARCH}/libpng.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/harfbuzz/lib/windows/${TARGET_ARCH}/libharfbuzz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/harfbuzz/include/harfbuzz"
    )

    # link
    target_link_libraries(freetype_jni PRIVATE
        freetype
        harfbuzz
        libpng
        z
    )
    set(CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS ON)
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".dll"
        PREFIX ""
        IMPORT_PREFIX ""
    )
    target_link_options(freetype_jni PRIVATE -static -static-libgcc -static-libstdc++)

elseif(TARGET_PLATFORM STREQUAL "linux")
    message(STATUS "Configuring for Linux")

    # z
    add_library(z STATIC IMPORTED GLOBAL)
    set_target_properties(z PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/zlib/lib/linux/${TARGET_ARCH}/libz.so"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/zlib/include"
    )

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/libpng/lib/linux/${TARGET_ARCH}/libpng.so"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${LIBS_DIR}/harfbuzz/lib/linux/${TARGET_ARCH}/libharfbuzz.so"
        INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/harfbuzz/include/harfbuzz"
    )

    # link
    target_link_libraries(freetype_jni PRIVATE
        freetype
        harfbuzz
        libpng
        z
    )
    target_compile_options(freetype_jni PRIVATE -fPIC)
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".so"
    )

elseif(TARGET_PLATFORM STREQUAL "macos")
    message(STATUS "Configuring for MacOS")

    # NOT COMPILED YET
    ## z
    #add_library(z STATIC IMPORTED GLOBAL)
    #set_target_properties(z PROPERTIES
    #    IMPORTED_LOCATION "${LIBS_DIR}/zlib/lib/macos/${TARGET_ARCH}/libz.so"
    #    INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/zlib/include"
    #)
    #
    ## png
    #add_library(libpng STATIC IMPORTED GLOBAL)
    #set_target_properties(libpng PROPERTIES
    #    IMPORTED_LOCATION "${LIBS_DIR}/libpng/lib/macos/${TARGET_ARCH}/libpng.so"
    #    INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/libpng/include"
    #)
    #
    ## harfbuzz
    #add_library(harfbuzz STATIC IMPORTED GLOBAL)
    #set_target_properties(harfbuzz PROPERTIES
    #    IMPORTED_LOCATION "${LIBS_DIR}/harfbuzz/lib/macos/${TARGET_ARCH}/libharfbuzz.so"
    #    INTERFACE_INCLUDE_DIRECTORIES "${LIBS_DIR}/harfbuzz/include/harfbuzz"
    #)

    # link
    target_link_libraries(freetype_jni PRIVATE
        freetype
#        harfbuzz
#        libpng
#        z
    )
    target_compile_options(freetype_jni PRIVATE -fPIC)
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".dylib"
    )

endif()