import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.glyph.FTBitmapGlyph;
import generaloss.freetype.types.FTBBox;
import generaloss.freetype.types.FTVector;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        for(int i = 0; i < 1; i++)
            test3();
    }

    private static final String CHARS = "\0ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*�?�?�?�?�? ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";

    private static void test3() {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/benchmark.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);

        System.out.println("FTFace {");
        System.out.println("  FT_Long         num_faces = " + face.getNumFaces());
        System.out.println("  FT_Long         face_index = " + face.getFaceIndex());
        System.out.println("  FT_Long         face_flags = " + face.getFaceFlags());
        System.out.println("  FT_String*      family_name = " + face.getFamilyName());
        System.out.println("  FT_String*      style_name = " + face.getStyleName());
        System.out.println("  FT_Int          num_fixed_sizes = " + face.getNumFixedSizes());
        System.out.println("  FT_Bitmap_Size* available_sizes = [");
        for(FTBitmapSize size: face.getAvailableSizes()) {
            System.out.println("    FT_Bitmap_Size {");
            System.out.println("      FT_Short height = " + size.getHeight());
            System.out.println("      FT_Short width = " + size.getWidth());
            System.out.println("      FT_Pos   size = " + size.getSize());
            System.out.println("      FT_Pos   x_ppem = " + size.getXppem());
            System.out.println("      FT_Pos   y_ppem = " + size.getYppem());
            System.out.println("    }");
        }
        System.out.println("  ]");
        System.out.println("  FT_Int          num_charmaps = " + face.getNumCharmaps());
        System.out.println("  FT_CharMap*     charmaps = [");
        for(FTCharMap charmap: face.getCharmaps()) {
            System.out.println("    FTCharMap {");
            System.out.println("      FT_Face     face = " + charmap.getFace());
            System.out.println("      FT_Encoding encoding = " + charmap.getEncoding());
            System.out.println("      FT_UShort   platform_id = " + charmap.getPlatformID());
            System.out.println("      FT_UShort   encoding_id = " + charmap.getEncodingID());
            System.out.println("    }");
        }
        System.out.println("  ]");
        System.out.println("  FT_BBox         bbox = {");
        System.out.println("    FT_Pos xMin = " + face.getBBox().getXMin());
        System.out.println("    FT_Pos yMin = " + face.getBBox().getYMin());
        System.out.println("    FT_Pos xMax = " + face.getBBox().getXMax());
        System.out.println("    FT_Pos yMax = " + face.getBBox().getYMax());
        System.out.println("  }");
        System.out.println("  FT_UShort       units_per_EM = " + face.getUnitsPerEM());
        System.out.println("  FT_Short        ascender = " + face.getAscender());
        System.out.println("  FT_Short        descender = " + face.getDescender());
        System.out.println("  FT_Short        height = " + face.getHeight());
        System.out.println("  FT_Short        max_advance_width = " + face.getMaxAdvanceWidth());
        System.out.println("  FT_Short        max_advance_height = " + face.getMaxAdvanceHeight());
        System.out.println("  FT_Short        underline_position = " + face.getUnderlinePosition());
        System.out.println("  FT_Short        underline_thickness = " + face.getUnderlineThickness());
        System.out.println("  FT_GlyphSlot    glyph = {");
        System.out.println("    FT_Library       library = " + face.getGlyph().getLibrary());
        System.out.println("    FT_Face          face = " + face.getGlyph().getFace());
        System.out.println("    FT_GlyphSlot     next = " + face.getGlyph().getNext());
        System.out.println("    FT_UInt          glyph_index = " + face.getGlyph().getGlyphIndex());
        System.out.println("    FT_Glyph_Metrics metrics = " + face.getGlyph().getMetrics());
        System.out.println("    FT_Fixed         linearHoriAdvance = " + face.getGlyph().getLinearHoriAdvance());
        System.out.println("    FT_Fixed         linearVertAdvance = " + face.getGlyph().getLinearVertAdvance());
        System.out.println("    FT_Vector        advance = " + face.getGlyph().getAdvance());
        System.out.println("    FT_Glyph_Format  format = " + face.getGlyph().getFormat());
        System.out.println("    FT_Bitmap        bitmap = " + face.getGlyph().getBitmap());
        System.out.println("    FT_Int           bitmap_left = " + face.getGlyph().getBitmapLeft());
        System.out.println("    FT_Int           bitmap_top = " + face.getGlyph().getBitmapTop());
        System.out.println("    FT_Outline       outline = " + face.getGlyph().getOutline());
        System.out.println("    FT_UInt          num_subglyphs = " + face.getGlyph().getNumSubglyphs());
        System.out.println("    FT_SubGlyph      subglyphs = [");
        for(FTSubGlyph subglyph: face.getGlyph().getSubglyphs()) {
            System.out.println("      FT_SubGlyph {");
            System.out.println("        FT_Int index = " + subglyph.getIndex());
            System.out.println("        FT_UShort flags = " + subglyph.getFlags());
            System.out.println("        FT_Int arg1 = " + subglyph.getArg1());
            System.out.println("        FT_Int arg2 = " + subglyph.getArg2());
            System.out.println("        FT_Matrix transform = " + subglyph.getTransform());
            System.out.println("      }");
        }
        System.out.println("    ]");
        System.out.println("    FT_Pos           lsb_delta = " + face.getGlyph().getLsbDelta());
        System.out.println("    FT_Pos           rsb_delta = " + face.getGlyph().getRsbDelta());
        System.out.println("  }");
        System.out.println("  FT_Size         size = " + face.getSize());
        System.out.println("  FT_CharMap      charmap = " + face.getCharmap());
        System.out.println("}");

        face.done();
        library.done();
    }

    private static void test2() {
        final FTLibrary library = new FTLibrary();
        // ...
        library.done();
    }

    private static void test1() {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/main.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);

        for(FTCharMap cmap: face.getCharmaps()) {
            System.out.printf(
                "encoding=%s platformId=%d encodingId=%d%n",
                cmap.getEncoding(), cmap.getPlatformID(), cmap.getEncodingID()
            );
        }

        face.setPixelSizes(0, 15);
        final FTSize size = face.getSize();
        final FTSizeMetrics metrics = size.getMetrics();
        System.out.println(metrics.getAscender() + ", " + metrics.getDescender() + ", " + metrics.getHeight());

        for(int i = 0; i < CHARS.length(); i++)
            loadChar(face, CHARS.charAt(i));

        face.done();
        library.done();
    }

    private static void loadChar(FTFace face, char c) {
        final long charIndex = face.getCharIndex(c);
        face.loadGlyph(charIndex);

        System.out.println("char '" + c + "': " + charIndex);

        final FTGlyphSlot slot = face.getGlyph();

        System.out.println("render");
        slot.renderGlyph(FTRenderMode.NORMAL);

        final FTGlyph glyph = slot.getGlyph();

        System.out.println("advance: " + glyph.getAdvance());

        final FTVector origin = FTVector.newInstance();
        final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, origin, true);
        System.out.println("origin: " + origin);
        final FTBitmap bitmap = bitmapGlyph.getBitmap();

        // final FTBitmap bitmap = slot.getBitmap();
        final FTGlyphMetrics glyphMetrics = slot.getMetrics();

        System.out.println(glyphMetrics.getHoriBearingX() + ", " + glyphMetrics.getHoriBearingY());
        System.out.println(glyphMetrics.getWidth() + ", " + glyphMetrics.getHeight() + ", " + glyphMetrics.getHoriAdvance());
        System.out.println(bitmap.getWidth() + ", " + bitmap.getRows() + ", " + bitmap.getPitch() + ", " + bitmap.getNumGray());

        for(int y = 0; y < bitmap.getRows(); y++) {
            for(int x = 0; x < bitmap.getWidth(); x++)
                System.out.print(bitmap.getBuffer().get(x + bitmap.getPitch() * y) != 0? "X": " ");
            System.out.println();
        }
    }

}
