package dev;

import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTGlyphLoader;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.glyph.FTBitmapGlyph;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.stroke.FTStrokerLineCap;
import generaloss.freetype.stroke.FTStrokerLineJoin;
import generaloss.freetype.system.FTMemory;
import generaloss.freetype.types.FTVector;
import jpize.util.res.Resource;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class TreeTest {

    public static void main(String[] args) {
        final ByteArrayInputStream bais = new ByteArrayInputStream(Resource.internal("/main.ttf").readBytes());

        FTStream stream = new FTStream();
        stream.setSize(bais.available());
        stream.setRead((long offset, byte[] buffer, long count) -> {
            bais.reset();
            bais.skip(offset);
            return bais.read(buffer, 0, (int) count);
        });
        stream.setClose(bais::close);

        final FTOpenArgs openargs = new FTOpenArgs();
        openargs.setFlags(FTOpen.STREAM);
        openargs.setStream(stream);

        final FTLibrary library = new FTLibrary();
        final FTFace face = library.openFace(openargs, 0);
        face.setPixelSizes(32, 32);

        final FTStroker stroker = library.newStroker();
        stroker.set(4, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0);

        final char c = 'Ǽ';
        face.loadChar(c);

        final long charIndex = face.getCharIndex(c);
        face.loadGlyph(charIndex);
        final FTGlyphSlot slot = face.getGlyph();

        final FTGlyph glyph = slot.getGlyph();
        final FTGlyph strokedGlyph = glyph.strokeBorder(stroker, false, false);

        slot.renderGlyph(FTRenderMode.NORMAL);

        final FTMemory memory = library.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        System.out.println("FT_Library {");
        System.out.println("  FT_Memory memory {");
        System.out.println("    NewGlyphLoader() => {");
        System.out.println("      FT_Memory       memory = " + loader.getMemory());
        System.out.println("      FT_UInt         max_points = " + loader.getMaxPoints());
        System.out.println("      FT_UInt         max_contours = " + loader.getMaxContours());
        System.out.println("      FT_UInt         max_subglyphs = " + loader.getMaxSubglyphs());
        System.out.println("      FT_Bool         use_extra = " + loader.getUseExtra());
        System.out.println("      FT_GlyphLoadRec base = {");
        System.out.println("        FT_Outline outline = " + loader.getBase().getOutline());
        System.out.println("        FT_Vector* extra_points = {");
        for(FTVector point: loader.getBase().getExtraPoints()) {
            System.out.println("          FT_Vector {");
            System.out.println("            FT_Pos x = " + point.getX());
            System.out.println("            FT_Pos y = " + point.getY());
            System.out.println("          }");
        }
        System.out.println("        }");
        System.out.println("        FT_Vector* extra_points2 = {");
        for(FTVector point: loader.getBase().getExtraPoints2()) {
            System.out.println("          FT_Vector {");
            System.out.println("            FT_Pos x = " + point.getX());
            System.out.println("            FT_Pos y = " + point.getY());
            System.out.println("          }");
        }
        System.out.println("        }");
        System.out.println("        FT_UInt num_subglyphs = " + loader.getBase().getNumSubglyphs());
        System.out.println("        FT_SubGlyph subglyphs = [");
        for(FTSubGlyph subglyph: loader.getBase().getSubglyphs()) {
            System.out.println("        FT_SubGlyph {");
            System.out.println("          FT_Int    index = " + subglyph.getIndex());
            System.out.println("          FT_UShort flags = " + subglyph.getFlags());
            System.out.println("          FT_Int    arg1 = " + subglyph.getArg1());
            System.out.println("          FT_Int    arg2 = " + subglyph.getArg2());
            System.out.println("          FT_Matrix transform = {");
            System.out.println("            FT_Fixed xx = " + subglyph.getTransform().getXX());
            System.out.println("            FT_Fixed xy = " + subglyph.getTransform().getXY());
            System.out.println("            FT_Fixed yx = " + subglyph.getTransform().getYX());
            System.out.println("            FT_Fixed yy = " + subglyph.getTransform().getYY());
            System.out.println("          }");
            System.out.println("        }");
        }
        System.out.println("        ]");
        System.out.println("      }");
        System.out.println("      FT_GlyphLoadRec current = " + loader.getCurrent());
        System.out.println("    }");
        System.out.println("  }");


        System.out.println("FT_Face {");
        System.out.println("  FT_Long         num_faces = " + face.getNumFaces());
        System.out.println("  FT_Long         face_index = " + face.getFaceIndex());
        System.out.println("  FT_Long         face_flags = " + face.getFaceFlags());
        System.out.println("  FT_String*      family_name = " + face.getFamilyName());
        System.out.println("  FT_String*      style_name = " + face.getStyleName());
        System.out.println("  FT_Int          num_fixed_sizes = " + face.getNumFixedSizes());
        System.out.println("  RequestSize() => {");
        final FTSizeRequest request = new FTSizeRequest();
        request.setType(FTSizeRequestType.REAL_DIM);
        request.setWidth(0);
        request.setHeight(12 * 64); // 12pt in 1/64th points (internal FreeType format)
        request.setHoriResolution(72); // DPI
        request.setVertResolution(72); // DPI
        face.requestSize(request);
        System.out.println("    FT_Size_Request_Type type = " + request.getType());
        System.out.println("    FT_Long              width = " + request.getWidth());
        System.out.println("    FT_Long              height = " + request.getHeight());
        System.out.println("    FT_UInt              horiResolution = " + request.getHoriResolution());
        System.out.println("    FT_UInt              vertResolution = " + request.getVertResolution());
        System.out.println("  }");
        System.out.println("  FT_Bitmap_Size* available_sizes = [");
        for(FTBitmapSize size: face.getAvailableSizes()) {
            System.out.println("    FT_Bitmap_Size {");
            System.out.println("      FT_Short height = " + size.getHeight());
            System.out.println("      FT_Short width = " + size.getWidth());
            System.out.println("      FT_Pos   size = " + size.getSize());
            System.out.println("      FT_Pos   x_ppem = " + size.getXPpem());
            System.out.println("      FT_Pos   y_ppem = " + size.getYPpem());
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
        System.out.println("    FT_GetGlyph() => FTGlyph {");
        System.out.println("      FT_Library      library = " + glyph.getLibrary());
        System.out.println("      FT_Glyph_Format format = " + glyph.getFormat());
        System.out.println("      FT_Vector       advance = {");
        System.out.println("        FT_Pos x = " + glyph.getAdvance().getX());
        System.out.println("        FT_Pos y = " + glyph.getAdvance().getY());
        System.out.println("      }");
        final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, null, false);
        System.out.println("      FT_Glyph_To_Bitmap => FT_BitmapGlyph {");
        System.out.println("        FT_GlyphRec root = " + bitmapGlyph.getRoot());
        System.out.println("        FT_Int      left = " + bitmapGlyph.getLeft());
        System.out.println("        FT_Int      top = " + bitmapGlyph.getTop());
        System.out.println("        FT_Bitmap   bitmap = " + bitmapGlyph.getBitmap());
        System.out.println("      }");
        System.out.println("    }");
        System.out.println("  }");
        System.out.println("  FT_Size         size = {");
        System.out.println("    FT_Face         face = " + face.getSize().getFace());
        System.out.println("    FT_Size_Metrics metrics = {");
        System.out.println("      FT_UShort x_ppem = " + face.getSize().getMetrics().getXPpem());
        System.out.println("      FT_UShort y_ppem = " + face.getSize().getMetrics().getYPpem());
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

        stroker.done();
        face.done();
        library.done();
    }

}
