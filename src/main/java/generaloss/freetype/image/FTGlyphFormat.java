package generaloss.freetype.image;

import generaloss.freetype.types.FTTag;

public enum FTGlyphFormat {

    NONE      (0),

    /** The glyph image is a composite of several other images. This format is only used with FTLoad.NO_RECURSE, and is used to report compound glyphs (like accented characters). */
    COMPOSITE ('c', 'o', 'm', 'p'),

    /** The glyph image is a bitmap, and can be described as an FTBitmap. You generally need to access the bitmap field of the FTGlyphSlotRec structure to read it. */
    BITMAP    ('b', 'i', 't', 's'),

    /** The glyph image is a vectorial outline made of line segments and Bezier arcs; it can be described as an FT_Outline; you generally want to access the outline field of the FTGlyphSlotRec structure to read it. */
    OUTLINE   ('o', 'u', 't', 'l'),

    /** The glyph image is a vectorial path with no inside and outside contours. Some Type 1 fonts, like those in the Hershey family, contain glyphs in this format. These are described as FT_Outline, but freetype.h.txt isn't currently capable of rendering them correctly. */
    PLOTTER   ('p', 'l', 'o', 't'),

    /** The glyph is represented by an SVG document in the ‘SVG ’ table. */
    SVG       ('S', 'V', 'G', ' ');

    public final int value;

    FTGlyphFormat(int value) {
        this.value = value;
    }

    FTGlyphFormat(char a, char b, char c, char d) {
        this.value = FTTag.ftMakeTag(a, b, c, d);
    }

    public static FTGlyphFormat byValue(int value) {
        for(FTGlyphFormat format: values())
            if(format.value == value)
                return format;
        return null;
    }

}
