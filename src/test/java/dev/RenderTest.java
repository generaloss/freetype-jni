package dev;

import generaloss.freetype.freetype.*;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.glyph.FTBitmapGlyph;
import generaloss.freetype.types.FTVector;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;

public class RenderTest {

    public static void main(String[] args) {
        for(int i = 0; i < 100000; i++) {
            System.out.println("--------- ITERATE " + i + " ---------");
            test();
        }
    }

    private static final String CHARS = "\0ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*�?�?�?�?�? ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";

    private static void test() {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/main.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);

        face.setPixelSizes(0, 15);

        for(int i = 0; i < CHARS.length(); i++)
            loadChar(face, CHARS.charAt(i));

        face.done();
        library.done();
    }

    private static void loadChar(FTFace face, char c) {
        final long charIndex = face.getCharIndex(c);
        face.loadGlyph(charIndex);

        final FTGlyphSlot slot = face.getGlyph();

        slot.renderGlyph(FTRenderMode.NORMAL);

        final FTGlyph glyph = slot.getGlyph();

        final FTVector origin = new FTVector();
        final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, origin, true);
        final FTBitmap bitmap = bitmapGlyph.getBitmap();

        final FTGlyphMetrics glyphMetrics = slot.getMetrics();

        final ByteBuffer buffer = bitmap.getBuffer();
        final long rows = bitmap.getRows();
        final long width = bitmap.getWidth();
        final int pitch = bitmap.getPitch();
        System.out.println("buffer: " + buffer.capacity() + ", rows: " + rows + ", width: " + width + ", pitch: " + pitch);

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < width; x++) {
                final int index = (x + pitch * y);
                final String string = (buffer.get(index) != 0 ? "X" : " ");
                System.out.print(string);
            }
            System.out.println();
        }
    }

}
