set(CMAKE_SYSTEM_NAME Darwin)
set(CMAKE_SYSTEM_PROCESSOR x86_64)

set(OSXCROSS_TARGET o64)
set(CMAKE_C_COMPILER ${OSXCROSS_TARGET}-clang)
set(CMAKE_CXX_COMPILER ${OSXCROSS_TARGET}-clang++)

# Укажи SDK (osxcross ставит в /usr/osxcross/SDK/)
set(CMAKE_OSX_SYSROOT /usr/osxcross/SDK/MacOSX12.3.sdk)

# Чтобы найти system libs
set(CMAKE_FIND_ROOT_PATH ${CMAKE_OSX_SYSROOT})
