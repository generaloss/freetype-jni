package generaloss.freetype.types;

public enum FTError {

    OK                            (0x00, "no error"),

    CANNOT_OPEN_RESOURCE          (0x01, "cannot open resource"),
    UNKNOWN_FILE_FORMAT           (0x02, "unknown file format"),
    INVALID_FILE_FORMAT           (0x03, "broken file"),
    INVALID_VERSION               (0x04, "invalid freetype.h.txt version"),
    LOWER_MODULE_VERSION          (0x05, "module version is too low"),
    INVALID_ARGUMENT              (0x06, "invalid argument"),
    UNIMPLEMENTED_FEATURE         (0x07, "unimplemented feature"),
    INVALID_TABLE                 (0x08, "broken table"),
    INVALID_OFFSET                (0x09, "broken offset within table"),
    ARRAY_TOO_LARGE               (0x0A, "array allocation size too large"),
    MISSING_MODULE                (0x0B, "missing module"),
    MISSING_PROPERTY              (0x0C, "missing property"),

    // glyph/character errors

    INVALID_GLYPH_INDEX           (0x10, "invalid glyph index"),
    INVALID_CHARACTER_CODE        (0x11, "invalid character code"),
    INVALID_GLYPH_FORMAT          (0x12, "unsupported glyph image format"),
    CANNOT_RENDER_GLYPH           (0x13, "cannot render this glyph format"),
    INVALID_OUTLINE               (0x14, "invalid outline"),
    INVALID_COMPOSITE             (0x15, "invalid composite glyph"),
    TOO_MANY_HINTS                (0x16, "too many hints"),
    INVALID_PIXEL_SIZE            (0x17, "invalid pixel size"),
    INVALID_SVG_DOCUMENT          (0x18, "invalid SVG document"),

    // handle errors

    INVALID_HANDLE                (0x20, "invalid object handle"),
    INVALID_LIBRARY_HANDLE        (0x21, "invalid library handle"),
    INVALID_DRIVER_HANDLE         (0x22, "invalid module handle"),
    INVALID_FACE_HANDLE           (0x23, "invalid face handle"),
    INVALID_SIZE_HANDLE           (0x24, "invalid size handle"),
    INVALID_SLOT_HANDLE           (0x25, "invalid glyph slot handle"),
    INVALID_CHARMAP_HANDLE        (0x26, "invalid charmap handle"),
    INVALID_CACHE_HANDLE          (0x27, "invalid cache manager handle"),
    INVALID_STREAM_HANDLE         (0x28, "invalid stream handle"),

    // driver errors

    TOO_MANY_DRIVERS              (0x30, "too many modules"),
    TOO_MANY_EXTENSIONS           (0x31, "too many extensions"),

    // memory errors

    OUT_OF_MEMORY                 (0x40, "out of memory"),
    UNLISTED_OBJECT               (0x41, "unlisted object"),

    // stream errors

    CANNOT_OPEN_STREAM            (0x51, "cannot open stream"),
    INVALID_STREAM_SEEK           (0x52, "invalid stream seek"),
    INVALID_STREAM_SKIP           (0x53, "invalid stream skip"),
    INVALID_STREAM_READ           (0x54, "invalid stream read"),
    INVALID_STREAM_OPERATION      (0x55, "invalid stream operation"),
    INVALID_FRAME_OPERATION       (0x56, "invalid frame operation"),
    NESTED_FRAME_ACCESS           (0x57, "nested frame access"),
    INVALID_FRAME_READ            (0x58, "invalid frame read"),

    // raster errors

    RASTER_UNINITIALIZED          (0x60, "raster uninitialized"),
    RASTER_CORRUPTED              (0x61, "raster corrupted"),
    RASTER_OVERFLOW               (0x62, "raster overflow"),
    RASTER_NEGATIVE_HEIGHT        (0x63, "negative height while rastering"),

    // cache errors

    TOO_MANY_CACHES               (0x70, "too many registered caches"),

    // TrueType and SFNT errors

    INVALID_OPCODE                (0x80, "invalid opcode"),
    TOO_FEW_ARGUMENTS             (0x81, "too few arguments"),
    STACK_OVERFLOW                (0x82, "stack overflow"),
    CODE_OVERFLOW                 (0x83, "code overflow"),
    BAD_ARGUMENT                  (0x84, "bad argument"),
    DIVIDE_BY_ZERO                (0x85, "division by zero"),
    INVALID_REFERENCE             (0x86, "invalid reference"),
    DEBUG_OPCODE                  (0x87, "found debug opcode"),
    ENDF_IN_EXEC_STREAM           (0x88, "found ENDF opcode in execution stream"),
    NESTED_DEFS                   (0x89, "nested DEFS"),
    INVALID_CODERANGE             (0x8A, "invalid code range"),
    EXECUTION_TOO_LONG            (0x8B, "execution context too long"),
    TOO_MANY_FUNCTION_DEFS        (0x8C, "too many function definitions"),
    TOO_MANY_INSTRUCTION_DEFS     (0x8D, "too many instruction definitions"),
    TABLE_MISSING                 (0x8E, "SFNT font table missing"),
    HORIZ_HEADER_MISSING          (0x8F, "horizontal header (hhea) table missing"),
    LOCATIONS_MISSING             (0x90, "locations (loca) table missing"),
    NAME_TABLE_MISSING            (0x91, "name table missing"),
    CMAP_TABLE_MISSING            (0x92, "character map (cmap) table missing"),
    HMTX_TABLE_MISSING            (0x93, "horizontal metrics (hmtx) table missing"),
    POST_TABLE_MISSING            (0x94, "PostScript (post) table missing"),
    INVALID_HORIZ_METRICS         (0x95, "invalid horizontal metrics"),
    INVALID_CHARMAP_FORMAT        (0x96, "invalid character map (cmap) format"),
    INVALID_PPEM                  (0x97, "invalid ppem value"),
    INVALID_VERT_METRICS          (0x98, "invalid vertical metrics"),
    COULD_NOT_FIND_CONTEXT        (0x99, "could not find context"),
    INVALID_POST_TABLE_FORMAT     (0x9A, "invalid PostScript (post) table format"),
    INVALID_POST_TABLE            (0x9B, "invalid PostScript (post) table"),
    DEF_IN_GLYF_BYTECODE          (0x9C, "found FDEF or IDEF opcode in glyf bytecode"),
    MISSING_BITMAP                (0x9D, "missing bitmap in strike"),
    MISSING_SVG_HOOKS             (0x9E, "SVG hooks have not been set"),

    // CFF, CID, and Type 1 errors

    SYNTAX_ERROR                  (0xA0, "opcode syntax error"),
    STACK_UNDERFLOW               (0xA1, "argument stack underflow"),
    IGNORE                        (0xA2, "ignore"),
    NO_UNICODE_GLYPH_NAME         (0xA3, "no Unicode glyph name found"),
    GLYPH_TOO_BIG                 (0xA4, "glyph too big for hinting"),

    // BDF errors

    MISSING_STARTFONT_FIELD       (0xB0, "`STARTFONT' field missing"),
    MISSING_FONT_FIELD            (0xB1, "`FONT' field missing"),
    MISSING_SIZE_FIELD            (0xB2, "`SIZE' field missing"),
    MISSING_FONTBOUNDINGBOX_FIELD (0xB3, "`FONTBOUNDINGBOX' field missing"),
    MISSING_CHARS_FIELD           (0xB4, "`CHARS' field missing"),
    MISSING_STARTCHAR_FIELD       (0xB5, "`STARTCHAR' field missing"),
    MISSING_ENCODING_FIELD        (0xB6, "`ENCODING' field missing"),
    MISSING_BBX_FIELD             (0xB7, "`BBX' field missing"),
    BBX_TOO_BIG                   (0xB8, "`BBX' too big"),
    CORRUPTED_FONT_HEADER         (0xB9, "Font header corrupted or missing fields"),
    CORRUPTED_FONT_GLYPHS         (0xBA, "Font glyphs corrupted or missing fields");

    public final int code;
    private final String description;

    FTError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean hasError() {
        return (code != 0);
    }

    public boolean isOK() {
        return (code == 0);
    }

    public void checkError() {
        if(this.hasError())
            throw new Error("FTError: " + this.toString());
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + " (" + code + ")";
    }


    public static FTError byCode(int code) {
        for(FTError error: values())
            if(error.code == code)
                return error;
        return OK;
    }

}
