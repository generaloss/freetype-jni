import generaloss.freetype.freetype.*;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.glyph.FTBitmapGlyph;
import jpize.util.res.Resource;

public class Test {

    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++)
            test1();
    }

    private static final String CHARS = "\0ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*�?�?�?�?�? ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";

    private static void test2() {
        final FTLibrary library = new FTLibrary();
        // ...
        library.done();
    }

    private static void test1() {
        final FTLibrary library = new FTLibrary();

        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        System.out.println("Charmaps num: " + face.getNumCharmaps());

        for(FTCharMap cmap: face.getCharmaps()) {
            System.out.printf(
                "encoding=%s platformId=%d encodingId=%d%n",
                cmap.getEncoding(), cmap.getPlatformID(), cmap.getEncodingID()
            );
        }

        face.setPixelSizes(0, 15);
        final FTSizeMetrics metrics = face.getSize().getMetrics();
        System.out.println(metrics.getAscender() + ", " + metrics.getDescender() + ", " + metrics.getHeight());

        for(int i = 0; i < CHARS.length(); i++) {
            face.loadGlyph(face.getCharIndex(CHARS.charAt(i)));

            final FTGlyphSlot slot = face.getGlyph();
            slot.renderGlyph(FTRenderMode.NORMAL);
            final FTBitmapGlyph bitmapGlyph = slot.getGlyph().toBitmap(FTRenderMode.NORMAL, null, false);
            final FTBitmap bitmap = bitmapGlyph.getBitmap();

            // final FTBitmap bitmap = face.getGlyph().getBitmap();
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
    }

}
