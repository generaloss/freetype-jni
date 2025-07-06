cmake_minimum_required(VERSION 3.10)
project(freetype_jni)

# jdk headers
set(JAVA_HOME "/usr/lib/jvm/java-8-openjdk") # for windows: set(JAVA_HOME "C:/Program Files/Java/jdk-8")
include_directories("${JAVA_HOME}/include/linux" "${JAVA_HOME}/include") # for windows: include_directories("${JAVA_HOME}/include/win32" "${JAVA_HOME}/include")

# jni
file(GLOB_RECURSE SOURCE_FILES src/*.*)
include_directories("include")
add_library(freetype_jni SHARED ${SOURCE_FILES})
target_include_directories(freetype_jni PRIVATE ${CMAKE_SOURCE_DIR}/freetype/include)

# freetype
set(FREETYPE_LIB "${CMAKE_SOURCE_DIR}/freetype/lib/${TARGET_PLATFORM}/${TARGET_ARCH}/libfreetype.a")
if(NOT EXISTS "${FREETYPE_LIB}")
    message(FATAL_ERROR "libfreetype.a not found for current platform/arch: ${FREETYPE_LIB}")
endif()

add_library(freetype STATIC IMPORTED GLOBAL)
set_target_properties(freetype PROPERTIES IMPORTED_LOCATION "${FREETYPE_LIB}")

# library
if(TARGET_PLATFORM STREQUAL "android")
    message(STATUS "Configuring for Android")

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/libpng/lib/android/${ANDROID_ABI}/libpng.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/harfbuzz/lib/android/${ANDROID_ABI}/libharfbuzz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/harfbuzz/include/harfbuzz"
    )

    target_link_libraries(freetype_jni PRIVATE freetype z libpng harfbuzz android log)
    set_target_properties(freetype_jni PROPERTIES SUFFIX ".so")

elseif(TARGET_PLATFORM STREQUAL "windows")
    message(STATUS "Configuring for Windows")

    # brotlidec
    add_library(brotlidec STATIC IMPORTED GLOBAL)
    set_target_properties(brotlidec PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/brotli/lib/windows/${TARGET_ARCH}/libbrotlidec.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/brotli/include/brotli"
    )

    # bz2
    add_library(bz2 STATIC IMPORTED GLOBAL)
    set_target_properties(bz2 PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/bz2/lib/windows/${TARGET_ARCH}/libbz2.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/bz2/include"
    )

    # z
    add_library(z STATIC IMPORTED GLOBAL)
    set_target_properties(z PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/zlib/lib/windows/${TARGET_ARCH}/libz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/zlib/include"
    )

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/libpng/lib/windows/${TARGET_ARCH}/libpng.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/harfbuzz/lib/windows/${TARGET_ARCH}/libharfbuzz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/harfbuzz/include/harfbuzz"
    )

    target_link_libraries(freetype_jni PRIVATE freetype harfbuzz brotlidec bz2 libpng z)

    set(CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS ON)
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".dll"
        PREFIX ""
        IMPORT_PREFIX ""
    )
    target_link_options(freetype_jni PRIVATE -static -static-libgcc -static-libstdc++)

else()
    message(STATUS "Configuring for Linux")

    target_link_libraries(freetype_jni PRIVATE freetype)
    target_compile_options(freetype_jni PRIVATE -fPIC)
    set_target_properties(freetype_jni PROPERTIES SUFFIX ".so")
endif()