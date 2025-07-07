cmake_minimum_required(VERSION 3.10)
project(freetype_jni)

# java headers
set(JAVA_HOME "/usr/lib/jvm/java-8-openjdk") # windows => set(JAVA_HOME "C:/Program Files/Java/jdk-8")
include_directories("${JAVA_HOME}/include/linux" "${JAVA_HOME}/include") # windows => include_directories("${JAVA_HOME}/include/win32" "${JAVA_HOME}/include")

# jni
file(GLOB_RECURSE SOURCE_FILES src/*.*)
add_library(freetype_jni SHARED ${SOURCE_FILES})
include_directories("include")

# freetype
add_library(freetype STATIC IMPORTED GLOBAL)
set_target_properties(freetype PROPERTIES
    IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/freetype/lib/${TARGET_PLATFORM}/${TARGET_ARCH}/libfreetype.a"
    INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/freetype/include"
)

if(TARGET_PLATFORM STREQUAL "android")
    message(STATUS "Configuring for Android")

    # z
    add_library(z STATIC IMPORTED GLOBAL)
    set_target_properties(z PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/zlib/lib/android/${ANDROID_ABI}/libz.so"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/zlib/include"
    )

    # png
    add_library(libpng STATIC IMPORTED GLOBAL)
    set_target_properties(libpng PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/libpng/lib/android/${ANDROID_ABI}/libpng.so"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/libpng/include"
    )

    # harfbuzz
    add_library(harfbuzz STATIC IMPORTED GLOBAL)
    set_target_properties(harfbuzz PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/libs/harfbuzz/lib/android/${ANDROID_ABI}/libharfbuzz.a"
        INTERFACE_INCLUDE_DIRECTORIES "${CMAKE_SOURCE_DIR}/libs/harfbuzz/include/harfbuzz"
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

else()
    message(STATUS "Configuring for Linux")

    # link
    target_link_libraries(freetype_jni PRIVATE
        freetype
        # harfbuzz
        # libpng
        # z
    )
    target_compile_options(freetype_jni PRIVATE -fPIC)
    set_target_properties(freetype_jni PROPERTIES
        SUFFIX ".so"
    )
endif()