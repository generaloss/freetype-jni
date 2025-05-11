# [FreeType JNI](https://github.com/generaloss/freetype-jni)
![logo](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F1%2F17%2FFreetype_logo_new.svg%2F360px-Freetype_logo_new.svg.png&f=1&nofb=1&ipt=39d69a8a2f61f9e89fd0168cd4fcb5ca2defa1c898b900c76e65ac1c944aae13&ipo=images)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.generaloss/freetype-jni.svg)](https://mvnrepository.com/artifact/io.github.generaloss/freetype-jni)

---

### [FreeType](https://github.com/freetype/freetype.git) version: 2.13.3

---

## How to use:

1. Add the [freetype-jni](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni) dependency.
2. Add the natives you need:
    * [freetype-jni-natives-windows](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-windows) (available arches: x86_64, i686)
    * [freetype-jni-natives-linux](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-linux) (available arches: x86_64, aarch64)
    * [freetype-jni-natives-android](https://central.sonatype.com/artifact/io.github.generaloss/freetype-jni-natives-android) (all ABIs available)

Android SDK: 21 +

---

## Code sample:

Java version: 8 +

``` java
final FTLibrary library = FTLibrary.init();

final FTFace face = library.newMemoryFace(/*font file raw data*/, 0);
face.setPixelSizes(0, 15);
final FTSizeMetrics faceMetrics = face.getSize().getMetrics();
System.out.println(faceMetrics.getAscender() + ", " + faceMetrics.getDescender() + ", " + faceMetrics.getHeight());

private static final String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
// print all chars bitmaps:
for(int i = 0; i < CHARS.length(); i++) {
    if(!face.loadGlyph(face.getCharIndex(CHARS.charAt(i))))
        continue;
    if(!face.getGlyph().renderGlyph(FTRenderMode.NORMAL))
        continue;

    final FTBitmap bitmap = face.getGlyph().getBitmap();
    final FTGlyphMetrics glyphMetrics = face.getGlyph().getMetrics();

    System.out.println(glyphMetrics.getHoriBearingX() + ", " + glyphMetrics.getHoriBearingY());
    System.out.println(glyphMetrics.getWidth() + ", " + glyphMetrics.getHeight() + ", " + glyphMetrics.getHoriAdvance());
    System.out.println(bitmap.getWidth() + ", " + bitmap.getRows() + ", " + bitmap.getPitch() + ", " + bitmap.getNumGray());

    for(int y = 0; y < bitmap.getRows(); y++) {
        for(int x = 0; x < bitmap.getWidth(); x++)
            System.out.print(bitmap.getBuffer().get(x + bitmap.getPitch() * y) != 0? "X": " ");
        System.out.println();
    }
}

face.done();
        
library.done();
```

---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/freetype-jni/issues).

---

### Credits: [FreeType](https://freetype.org/)