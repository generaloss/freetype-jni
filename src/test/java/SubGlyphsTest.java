import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTSubGlyph;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;

public class SubGlyphsTest {

    public static void main(String[] args) {
        final FTLibrary library = new FTLibrary();
        final ByteBuffer data = Resource.internal("/droidsans.ttf").readByteBuffer();
        final FTFace face = library.newMemoryFace(data, 0);

        final LoadFlags flags = new LoadFlags().set(FTLoad.NO_RECURSE);

        face.loadChar('Ǽ', flags);

        final FTGlyphSlot slot = face.getGlyph();

        System.out.println("FT_UInt          num_subglyphs = " + slot.getNumSubglyphs());
        System.out.println("FT_SubGlyph      subglyphs = [");
        for(FTSubGlyph subglyph: slot.getSubglyphs()) {
            System.out.println("  FT_SubGlyph {");
            System.out.println("    FT_Int    index = " + subglyph.getIndex());
            System.out.println("    FT_UShort flags = " + subglyph.getFlags());
            System.out.println("    FT_Int    arg1 = " + subglyph.getArg1());
            System.out.println("    FT_Int    arg2 = " + subglyph.getArg2());
            System.out.println("    FT_Matrix transform = {");
            System.out.println("      FT_Fixed xx = " + subglyph.getTransform().getXX());
            System.out.println("      FT_Fixed xy = " + subglyph.getTransform().getXY());
            System.out.println("      FT_Fixed yx = " + subglyph.getTransform().getYX());
            System.out.println("      FT_Fixed yy = " + subglyph.getTransform().getYY());
            System.out.println("    }");
            System.out.println("  }");
        }
        System.out.println("]");

        face.done();
        library.done();
    }

}
