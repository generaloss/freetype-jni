package generaloss.freetype.freetype;

public enum FTLoad {

    /** In this case, the following happens:
      *     1. freetype.h.txt looks for a bitmap for the glyph corresponding to the face's current size. If one is found, the function returns. The bitmap data can be accessed from the glyph slot.
      *     2. If no embedded bitmap is searched for or found, freetype.h.txt looks for a scalable outline. If one is found, it is loaded from the font file, scaled to device pixels, then ‘hinted’ to the pixel grid in order to optimize it. The outline data can be accessed from the glyph slot.
      * Note that by default the glyph loader doesn't render outlines into bitmaps. The following flags are used to modify this default behaviour to more specific and useful cases. */
    DEFAULT                     (0),

    /** Don't scale the loaded outline glyph but keep it in font units. This flag is also assumed if FT_Size owned by the face was not properly initialized.
      * This flag implies FTLoad.NO_HINTING and FTLoad.NO_BITMAP, and unsets FTLoad.RENDER.
      * If the font is ‘tricky’ (see FTFaceFlag.TRICKY for more), using FTLoad.NO_SCALE usually yields meaningless outlines because the subglyphs must be scaled and positioned with hinting instructions. This can be solved by loading the font without FTLoad.NO_SCALE and setting the character size to font->units_per_EM. */
    NO_SCALE                    (1),

    /** Disable hinting. This generally generates ‘blurrier’ bitmap glyphs when the glyphs are rendered in any of the anti-aliased modes.
      * This flag is implied by FTLoad.NO_SCALE. */
    NO_HINTING                  (1 << 1),  // 2

    /** Call FT_Render_Glyph after the glyph is loaded. By default, the glyph is rendered in FTRenderMode.NORMAL mode. This can be overridden by FTLoadTarget.XXX or FTLoad.MONOCHROME.
      * This flag is unset by FTLoad.NO_SCALE. */
    RENDER                      (1 << 2),  // 4

    /** Ignore bitmap strikes when loading. Bitmap-only fonts ignore this flag.
      * FTLoad.NO_SCALE always sets this flag. */
    NO_BITMAP                   (1 << 3),  // 8

    /** Load the glyph for vertical text layout. In particular, the advance value in the FT_GlyphSlotRec structure is set to the vertAdvance value of the metrics field.
      * In case FT_HAS_VERTICAL doesn't return true, you shouldn't use this flag currently. Reason is that in this case vertical metrics get synthesized, and those values are not always consistent across various font formats. */
    VERTICAL_LAYOUT             (1 << 4),  // 16

    /** Prefer the auto-hinter over the font's native hinter. See also the note below. */
    FORCE_AUTOHINT              (1 << 5),  // 32

    /** Ignored. Deprecated. */
    CROP_BITMAP                 (1 << 6),  // 64

    /** Make the font driver perform pedantic verifications during glyph loading and hinting. This is mostly used to detect broken glyphs in fonts. By default, freetype.h.txt tries to handle broken fonts also.
      * In particular, errors from the TrueType bytecode engine are not passed to the application if this flag is not set; this might result in partially hinted or distorted glyphs in case a glyph's bytecode is buggy. */
    PEDANTIC                    (1 << 7),  // 128

    /** Ignored. Deprecated. */
    IGNORE_GLOBAL_ADVANCE_WIDTH (1 << 9),  // 512

    /** Don't load composite glyphs recursively. Instead, the font driver fills the num_subglyph and subglyphs values of the glyph slot; it also sets glyph->format to FT_GLYPH_FORMAT_COMPOSITE. The description of subglyphs can then be accessed with FT_Get_SubGlyph_Info.
      * Don't use this flag for retrieving metrics information since some font drivers only return rudimentary data.
      * This flag implies FTLoad.NO_SCALE and FTLoad.IGNORE_TRANSFORM. */
    NO_RECURSE                  (1 << 10), // 1024

    /** Ignore the transform matrix set by FT_Set_Transform. */
    IGNORE_TRANSFORM            (1 << 11), // 2048

    /** This flag is used with FTLoad.RENDER to indicate that you want to render an outline glyph to a 1-bit monochrome bitmap glyph, with 8 pixels packed into each byte of the bitmap data.
      * Note that this has no effect on the hinting algorithm used. You should rather use FTLoad.TARGET_MONO so that the monochrome-optimized hinting algorithm is used. */
    MONOCHROME                  (1 << 12), // 4096

    /** Keep linearHoriAdvance and linearVertAdvance fields of FT_GlyphSlotRec in font units. See FT_GlyphSlotRec for details. */
    LINEAR_DESIGN               (1 << 13), // 8192

    /** [Since 2.12] This is the opposite of FTLoad.NO_BITMAP, more or less: FTLoad.Glyph returns FT_Err_Invalid_Argument if the face contains a bitmap strike for the given size (or the strike selected by FT_Select_Size) but there is no glyph in the strike.
     * Note that this load flag was part of freetype.h.txt since version 2.0.6 but previously tagged as internal. */
    SBITS_ONLY                  (1 << 14), // 16384

    /** Disable the auto-hinter. See also the note below. */
    NO_AUTOHINT                 (1 << 15), // 32768

    /** Load colored glyphs. freetype.h.txt searches in the following order; there are slight differences depending on the font format.
      * Load embedded color bitmap images (provided FTLoad.NO_BITMAP is not set). The resulting color bitmaps, if available, have the FTPixelMode.BGRA format, with pre-multiplied color channels. If the flag is not set and color bitmaps are found, they are converted to 256-level gray bitmaps, using the FT_PIXEL_MODE_GRAY format.
      * If the glyph index maps to an entry in the face's ‘SVG ’ table, load the associated SVG document from this table and set the format field of FT_GlyphSlotRec to FT_GLYPH_FORMAT_SVG ([since 2.13.1] provided FTLoad.NO_SVG is not set). Note that freetype.h.txt itself can't render SVG documents; however, the library provides hooks to seamlessly integrate an external renderer. See sections ‘The SVG driver’ and ‘OpenType SVG Fonts’ for more.
      * If the glyph index maps to an entry in the face's ‘COLR’ table with a ‘CPAL’ palette table (as defined in the OpenType specification), make FT_Render_Glyph provide a default blending of the color glyph layers associated with the glyph index, using the same bitmap format as embedded color bitmap images. This is mainly for convenience and works only for glyphs in ‘COLR’ v0 tables (or glyphs in ‘COLR’ v1 tables that exclusively use v0 features). For full control of color layers use FT_Get_Color_Glyph_Layer and freetype.h.txt's color functions like FT_Palette_Select instead of setting FTLoad.COLOR for rendering so that the client application can handle blending by itself. */
    COLOR                       (1 << 20), // 1048576

    /** Compute glyph metrics from the glyph data, without the use of bundled metrics tables (for example, the ‘hdmx’ table in TrueType fonts). This flag is mainly used by font validating or font editing applications, which need to ignore, verify, or edit those tables.
     * Currently, this flag is only implemented for TrueType fonts. */
    COMPUTE_METRICS             (1 << 21), // 2097152

    /** Request loading of the metrics and bitmap image information of a (possibly embedded) bitmap glyph without allocating or copying the bitmap image data itself. No effect if the target glyph is not a bitmap image.
     * This flag unsets FTLoad.RENDER. */
    BITMAP_METRICS_ONLY         (1 << 22), // 4194304

    /** Ignore SVG glyph data when loading. */
    NO_SVG                      (1 << 24); // 16777216

    public final int value;

    FTLoad(int value) {
        this.value = value;
    }

}
