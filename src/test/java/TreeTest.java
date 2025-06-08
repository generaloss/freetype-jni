import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.types.FTVector;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class TreeTest {

    public static void main(String[] args) {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/DejaVuSans.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);
        face.setPixelSizes(32, 32);

        final char c = 'Ǽ';
        face.loadChar(c);

        final long charIndex = face.getCharIndex(c);
        face.loadGlyph(charIndex);
        final FTGlyphSlot slot = face.getGlyph();
        slot.renderGlyph(FTRenderMode.NORMAL);

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
        System.out.println("    FT_Bitmap        bitmap = {");
        System.out.println("      unsigned int   rows = " + face.getGlyph().getBitmap().getRows());
        System.out.println("      unsigned int   width = " + face.getGlyph().getBitmap().getWidth());
        System.out.println("      int            pitch = " + face.getGlyph().getBitmap().getPitch());
        System.out.println("      unsigned char* buffer = " + face.getGlyph().getBitmap().getBuffer());
        System.out.println("      unsigned short num_grays = " + face.getGlyph().getBitmap().getNumGrays());
        System.out.println("      unsigned char  pixel_mode = " + face.getGlyph().getBitmap().getPixelMode());
        System.out.println("      unsigned char  palette_mode = " + face.getGlyph().getBitmap().getPaletteMode());
        System.out.println("      void*          palette = " + face.getGlyph().getBitmap().getPalettePointer());
        System.out.println("    }");
        System.out.println("    FT_Int            bitmap_left = " + face.getGlyph().getBitmapLeft());
        System.out.println("    FT_Int            bitmap_top = " + face.getGlyph().getBitmapTop());
        System.out.println("    FT_Outline        outline = {");
        System.out.println("      unsigned short  n_contours = " + face.getGlyph().getOutline().getNContours());
        System.out.println("      unsigned short  n_points = " + face.getGlyph().getOutline().getNPoints());
        System.out.println("      FT_Vector* points = [");
        for(FTVector point: face.getGlyph().getOutline().getPoints()) {
            System.out.println("        FT_Vector {");
            System.out.println("          FT_Pos x = " + point.getX());
            System.out.println("          FT_Pos y = " + point.getY());
            System.out.println("        }");
        }
        System.out.println("      ]");
        System.out.println("      unsigned char*  tags = " + Arrays.toString(face.getGlyph().getOutline().getTagsString()));
        System.out.println("      unsigned short* contours = " + Arrays.toString(face.getGlyph().getOutline().getContours()));
        System.out.println("      int             flags = " + face.getGlyph().getOutline().getFlags());
        System.out.println("    }");
        System.out.println("    FT_UInt          num_subglyphs = " + face.getGlyph().getNumSubglyphs());
        System.out.println("    FT_SubGlyph      subglyphs = [");
        for(FTSubGlyph subglyph: face.getGlyph().getSubglyphs()) {
            System.out.println("      FT_SubGlyph {");
            System.out.println("        FT_Int    index = " + subglyph.getIndex());
            System.out.println("        FT_UShort flags = " + subglyph.getFlags());
            System.out.println("        FT_Int    arg1 = " + subglyph.getArg1());
            System.out.println("        FT_Int    arg2 = " + subglyph.getArg2());
            System.out.println("        FT_Matrix transform = {");
            System.out.println("          FT_Fixed xx = " + subglyph.getTransform().getXX());
            System.out.println("          FT_Fixed xy = " + subglyph.getTransform().getXY());
            System.out.println("          FT_Fixed yx = " + subglyph.getTransform().getYX());
            System.out.println("          FT_Fixed yy = " + subglyph.getTransform().getYY());
            System.out.println("        }");
            System.out.println("      }");
        }
        System.out.println("    ]");
        System.out.println("    FT_Pos           lsb_delta = " + face.getGlyph().getLsbDelta());
        System.out.println("    FT_Pos           rsb_delta = " + face.getGlyph().getRsbDelta());
        System.out.println("  }");
        System.out.println("  FT_Size         size = {");
        System.out.println("    FT_Face         face = " + face.getSize().getFace());
        System.out.println("    FT_Size_Metrics metrics = {");
        System.out.println("      FT_UShort x_ppem = " + face.getSize().getMetrics().getXppem());
        System.out.println("      FT_UShort y_ppem = " + face.getSize().getMetrics().getYppem());
        System.out.println("      FT_Fixed  x_scale = " + face.getSize().getMetrics().getXScale());
        System.out.println("      FT_Fixed  y_scale = " + face.getSize().getMetrics().getYScale());
        System.out.println("      FT_Pos    ascender = " + face.getSize().getMetrics().getAscender());
        System.out.println("      FT_Pos    descender = " + face.getSize().getMetrics().getDescender());
        System.out.println("      FT_Pos    height = " + face.getSize().getMetrics().getHeight());
        System.out.println("      FT_Pos    max_advance = " + face.getSize().getMetrics().getMaxAdvance());
        System.out.println("    }");
        System.out.println("  }");
        System.out.println("  FT_CharMap      charmap = {");
        System.out.println("    FT_Face     face = " + face.getCharmap().getFace());
        System.out.println("    FT_Encoding encoding = " + face.getCharmap().getEncoding());
        System.out.println("    FT_UShort   platform_id = " + face.getCharmap().getPlatformID());
        System.out.println("    FT_UShort   encoding_id = " + face.getCharmap().getEncodingID());
        System.out.println("  }");
        System.out.println("}");

        face.done();
        library.done();
    }

}
