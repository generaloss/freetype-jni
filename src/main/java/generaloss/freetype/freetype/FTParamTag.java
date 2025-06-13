package generaloss.freetype.freetype;

import generaloss.freetype.types.FTTag;

public enum FTParamTag {

    /** A tag for FT_Parameter to make FT_Open_Face ignore typographic family names in the ‘name’ table (introduced in OpenType version 1.4). Use this for backward compatibility with legacy systems that have a four-faces-per-family restriction. */
    IGNORE_TYPOGRAPHIC_FAMILY    ('i', 'g', 'p', 'f'),
    /** A tag for FT_Parameter to make FT_Open_Face ignore typographic subfamily names in the ‘name’ table (introduced in OpenType version 1.4). Use this for backward compatibility with legacy systems that have a four-faces-per-family restriction. */
    IGNORE_TYPOGRAPHIC_SUBFAMILY ('i', 'g', 'p', 's'),
    /** An FT_Parameter tag to be used with FT_Open_Face to indicate incremental glyph loading. */
    INCREMENTAL                  ('i', 'n', 'c', 'r'),
    /** A tag for FT_Parameter to make FT_Open_Face ignore an ‘sbix’ table while loading a font. Use this if FT_FACE_FLAG_SBIX is set and you want to access the outline glyphs in the font. */
    IGNORE_SBIX                  ('i', 's', 'b', 'x'),
    /** An FT_Parameter tag to be used with FT_Face_Properties. The corresponding argument specifies the five LCD filter weights for a given face (if using FT_LOAD_TARGET_LCD, for example), overriding the global default values or the values set up with FT_Library_SetLcdFilterWeights. */
    LCD_FILTER_WEIGHTS           ('l', 'c', 'd', 'f'),
    /** An FT_Parameter tag to be used with FT_Face_Properties. The corresponding 32bit signed integer argument overrides the font driver's random seed value with a face-specific one; see random-seed. */
    RANDOM_SEED                  ('s', 'e', 'e', 'd'),
    /** An FT_Parameter tag to be used with FT_Face_Properties. The corresponding Boolean argument specifies whether to apply stem darkening, overriding the global default values or the values set up with FT_Property_Set (see no-stem-darkening).
     * This is a passive setting that only takes effect if the font driver or autohinter honors it, which the CFF, Type 1, and CID drivers always do, but the autohinter only in ‘light’ hinting mode (as of version 2.9). */
    STEM_DARKENING               ('d', 'a', 'r', 'k');

    public final int value;

    FTParamTag(char x1, char x2, char x3, char x4) {
        this.value = FTTag.ftMakeTag(x1, x2, x3, x4);
    }

}
