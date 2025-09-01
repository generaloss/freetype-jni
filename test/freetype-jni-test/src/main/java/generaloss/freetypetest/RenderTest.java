package generaloss.freetypetest;

import generaloss.freetype.freetype.*;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.glyph.FTBitmapGlyph;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;

public class RenderTest {

    public static void main(String[] args) {
        for(int i = 0; i <= 100000; i++) {
            System.out.println("--------- ITERATE " + i + " ---------");
            test();
        }
    }

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";

    private static void test() {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/main.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);

        face.setPixelSizes(0L, 4L);

        if(face.getNumCharmaps() == 0)
            System.out.println("No charmaps!");

        final FTCharMap charmap = face.getCharmap();
        System.out.println(charmap + "{index=" + charmap.getIndex() + ", encoding=" + charmap.getEncoding() + "(id=" + charmap.getEncodingID() + "), platformID=" + charmap.getPlatformID() + "}");

        for(int i = 0; i < CHARS.length(); i++)
            loadChar(face, CHARS.charAt(i));

        face.done();
        library.done();
    }

    private static void loadChar(FTFace face, char c) {
        final long charIndex = face.getCharIndex(c);
        System.out.println("Char '" + c + "' index=" + charIndex);

        face.loadGlyph(charIndex);

        final FTGlyphSlot slot = face.getGlyph();
        final FTGlyph glyph = slot.getGlyph();
        final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, null, true);
        final FTBitmap bitmap = bitmapGlyph.getBitmap();

        final ByteBuffer buffer = bitmap.getBuffer();
        final long rows = bitmap.getRows();
        final long width = bitmap.getWidth();
        final int pitch = bitmap.getPitch();
        System.out.println(bitmap + "{capacity=" + buffer.capacity() + ", rows=" + rows + ", width=" + width + ", pitch=" + pitch + "}");
    }

}
