# [FreeType JNI](https://github.com/generaloss/freetype-jni)
![logo](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F1%2F17%2FFreetype_logo_new.svg%2F360px-Freetype_logo_new.svg.png&f=1&nofb=1&ipt=39d69a8a2f61f9e89fd0168cd4fcb5ca2defa1c898b900c76e65ac1c944aae13&ipo=images)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.generaloss/freetype-jni.svg)](https://mvnrepository.com/artifact/io.github.generaloss/freetype-jni)

---

### [FreeType](https://github.com/freetype/freetype.git) version: 2.13.3

---

## How to use

1. Add the [freetype-jni](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni) dependency.
2. Add the natives you need:
    * [freetype-jni-natives-windows](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-windows) (Archetictures available: x86_64, x86)
    * [freetype-jni-natives-linux](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-linux) (Archetictures available: x86_64, aarch64)
    * [freetype-jni-natives-android](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-android) (all ABIs available)

### Requirements
* Java: 1.8 +
* Android SDK: 21 +

### Natives
* Compiled with libraries: zlib, harfbuzz, libpng


---

## Code sample

``` java
final FTLibrary lib = new FTLibrary();
final ByteBuffer data = ...;
final FTFace face = lib.newMemoryFace(data, 0);
// ...
face.done();
lib.done();
```

See other samples (*99*) in [**Tests.java**](src/test/java/unit/Tests.java)

## Implemented

* Functions (*93*) - [**FreeType.cpp**](jni/freetype-jni/src/FreeType.cpp)
* Structs in headers (**fully** / partially): [**freetype.h**](.index/freetype.h.txt), [**ftgloadr.h**](.index/ftgloadr.h.txt), [ftglyph.h](.index/ftglyph.h.txt), [ftimage.h](.index/ftimage.h.txt), [**ftoutln.h**](.index/ftoutln.h.txt), [**ftstroke.h**](.index/ftstroke.h.txt), [ftsystem.h](.index/ftsystem.h.txt)

---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/freetype-jni/issues).

---

### Credits: [FreeType](https://freetype.org/)