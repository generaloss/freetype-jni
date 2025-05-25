package generaloss.freetype.freetype;

public enum FTFaceFlag {

    /** The face contains outline glyphs. Note that a face can contain bitmap strikes also, i.e., a face can have both this flag and FTFaceFlag.FIXED_SIZES set. */
    SCALABLE,         // 1

    /** The face contains bitmap strikes. See also the num_fixed_sizes and available_sizes fields of FT_FaceRec. */
    FIXED_SIZES,      // 2

    /** The face contains fixed-width characters (like Courier, Lucida, MonoType, etc.).c */
    FIXED_WIDTH,      // 4

    /** The face uses the SFNT storage scheme. For now, this means TrueType and OpenType. */
    SFNT,             // 8

    /** The face contains horizontal glyph metrics. This should be set for all common formats. */
    HORIZONTAL,       // 16

    /** The face contains vertical glyph metrics. This is only available in some formats, not all of them. */
    VERTICAL,         // 32

    /** The face contains kerning information. If set, the kerning distance can be retrieved using the function FT_Get_Kerning. Otherwise the function always returns the vector (0,0).
      * Note that for TrueType fonts only, freetype.h.txt supports both the ‘kern’ table and the basic, pair-wise kerning feature from the ‘GPOS’ table (with TT_CONFIG_OPTION_GPOS_KERNING enabled), though freetype.h.txt does not support the more advanced GPOS layout features; use a library like HarfBuzz for those instead. */
    KERNING,          // 64

    /** THIS FLAG IS DEPRECATED. DO NOT USE OR TEST IT. */
    FAST_GLYPHS,      // 128

    /** The face contains multiple masters and is capable of interpolating between them. Supported formats are Adobe MM, TrueType GX, and OpenType variation fonts.
      * See section ‘Multiple Masters’ for API details. */
    MULTIPLE_MASTERS, // 256

    /** The face contains glyph names, which can be retrieved using FT_Get_Glyph_Name. Note that some TrueType fonts contain broken glyph name tables. Use the function FT_Has_PS_Glyph_Names when needed. */
    GLYPH_NAMES,      // 512

    /** Used internally by freetype.h.txt to indicate that a face's stream was provided by the client application and should not be destroyed when FT_Done_Face is called. Don't read or test this flag. */
    EXTERNAL_STREAM,  // 1024

    /** The font driver has a hinting machine of its own. For example, with TrueType fonts, it makes sense to use data from the SFNT ‘gasp’ table only if the native TrueType hinting engine (with the bytecode interpreter) is available and active. */
    HINTER,           // 2048

    /** The face is CID-keyed. In that case, the face is not accessed by glyph indices but by CID values. For subsetted CID-keyed fonts this has the consequence that not all index values are a valid argument to FT_Load_Glyph. Only the CID values for which corresponding glyphs in the subsetted font exist make FT_Load_Glyph return successfully; in all other cases you get an FT_Err_Invalid_Argument error.
      * Note that CID-keyed fonts that are in an SFNT wrapper (that is, all OpenType/CFF fonts) don't have this flag set since the glyphs are accessed in the normal way (using contiguous indices); the ‘CID-ness’ isn't visible to the application. */
    CID_KEYED,        // 4096

    /** The face is ‘tricky’, that is, it always needs the font format's native hinting engine to get a reasonable result. A typical example is the old Chinese font mingli.ttf (but not mingliu.ttc) that uses TrueType bytecode instructions to move and scale all of its subglyphs.
      * It is not possible to auto-hint such fonts using FTLoad.FORCE_AUTOHINT; it will also ignore FTLoad.NO_HINTING. You have to set both FTLoad.NO_HINTING and FTLoad.NO_AUTOHINT to really disable hinting; however, you probably never want this except for demonstration purposes.
      * Currently, there are about a dozen TrueType fonts in the list of tricky fonts; they are hard-coded in file ttobjs.c. */
    TRICKY,           // 8192

    /** The face has color glyph tables. See FTLoad.COLOR for more information. */
    COLOR,            // 16384

    /** Set if the current face (or named instance) has been altered with FT_Set_MM_Design_Coordinates, FT_Set_Var_Design_Coordinates, FT_Set_Var_Blend_Coordinates, or FT_Set_MM_WeightVector to select a non-default instance. */
    VARIATION,        // 32768

    /** The face has an ‘SVG ’ OpenType table. */
    SVG,              // 65536

    /** The face has an ‘sbix’ OpenType table and outlines. For such fonts, FTFaceFlag.SCALABLE is not set by default to retain backward compatibility. */
    SBIX,             // 131072

    /** The face has an ‘sbix’ OpenType table where outlines should be drawn on top of bitmap strikes. */
    SBIX_OVERLAY;     // 262144


    public final int value;

    FTFaceFlag() {
        this.value = (1 << this.ordinal());
    }

    public static FTFaceFlag byValue(int value) {
        final int index = Integer.numberOfTrailingZeros(value);
        return FTFaceFlag.values()[index];
    }

}
