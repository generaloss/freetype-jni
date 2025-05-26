package generaloss.freetype.freetype;

import generaloss.freetype.types.*;
import generaloss.freetype.FTStruct;

public class FreeType {

    // FT_ENC_TAG(value, a, b, c, d)
    public static int ftEncodeTag(char a, char b, char c, char d) {
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    
    // FT_Error FT_Init_FreeType(FT_Library *alibrary)
    private static native int ftInitFreeType(long alibrary);

    public static FTError ftInitFreeType(FTLibrary dstLibrary) {
        final int code = ftInitFreeType(dstLibrary.getPointer());
        return FTError.byCode(code);
    }
    
    
    // FT_Error FT_Done_FreeType(FT_Library library)
    private static native int ftDoneFreeType(long library);

    public static FTError ftDoneFreeType(FTLibrary library) {
        final int code = ftDoneFreeType(library.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_New_Face(FT_Library library, const char* filepathname, FT_Long face_index, FT_Face *aface)
    private static native int ftNewFace(long library, String filepathname, long face_index, long aface);

    public static FTError ftNewFace(FTLibrary library, String filepath, long faceIndex, FTFace dstFace) {
        final int code = ftNewFace(library.getPointer(), filepath, faceIndex, dstFace.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_New_Memory_Face(FT_Library library, const FT_Byte* file_base, FT_Long file_size, FT_Long face_index, FT_Face *aface)
    private static native int ftNewMemoryFace(long library, byte file_base, long file_size, long face_index, long aface);

    public static FTError ftNewMemoryFace(FTLibrary library, byte fileBase, long fileSize, long faceIndex, FTFace face) {
        final int code = ftNewMemoryFace(library.getPointer(), fileBase, fileSize, faceIndex, face.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Open_Face(FT_Library library, const FT_Open_Args* args, FT_Long face_index, FT_Face *aface)
    private static native int ftOpenFace(long library, long args, long face_index, long aface);

    public static FTError ftOpenFace(FTLibrary library, FTOpenArgs args, long faceIndex, FTFace dstFace) {
        final int code = ftOpenFace(library.getPointer(), args.getPointer(), faceIndex, dstFace.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Attach_File(FT_Face face, const char* filepathname)
    private static native int ftAttachFile(long face, String filepathname);

    public static FTError ftAttachFile(FTFace face, String filepath) {
        final int code = ftAttachFile(face.getPointer(), filepath);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Attach_Stream(FT_Face face, const FT_Open_Args* parameters)
    private static native int ftAttachStream(long face, long parameters);

    public static FTError ftAttachStream(FTFace face, FTOpenArgs parameters) {
        final int code = ftAttachStream(face.getPointer(), parameters.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Reference_Face(FT_Face face)
    private static native int ftReferenceFace(long face);

    public static FTError ftReferenceFace(FTFace face) {
        final int code = ftReferenceFace(face.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Done_Face(FT_Face face)
    private static native int ftDoneFace(long face);

    public static FTError ftDoneFace(FTFace face) {
        final int code = ftDoneFace(face.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Select_Size(FT_Face face, FT_Int strike_index)
    private static native int ftSelectSize(long face, int strike_index);

    public static FTError ftSelectSize(FTFace face, int strikeIndex) {
        final int code = ftSelectSize(face.getPointer(), strikeIndex);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Request_Size(FT_Face face, FT_Size_Request req)
    private static native int ftRequestSize(long face, long req);

    public static FTError ftRequestSize(FTFace face, FTSizeRequest reqest) {
        final int code = ftRequestSize(face.getPointer(), reqest.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Set_Char_Size(FT_Face face, FT_F26Dot6 char_width, FT_F26Dot6 char_height, FT_UInt horz_resolution, FT_UInt vert_resolution)
    private static native int ftSetCharSize(long face, int char_width, int char_height, long horz_resolution, long vert_resolution);

    public static FTError ftSetCharSize(FTFace face, float charWidth, float charHeight, long horzResolution, long vertResolution) {
        final int code = ftSetCharSize(face.getPointer(), FTF26Dot6.of(charWidth), FTF26Dot6.of(charHeight), horzResolution, vertResolution);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Set_Pixel_Sizes(FT_Face face, FT_UInt pixel_width, FT_UInt pixel_height)
    private static native int ftSetPixelSizes(long face, long pixel_width, long pixel_height);

    public static FTError ftSetPixelSizes(FTFace face, long pixelWidth, long pixelHeight) {
        final int code = ftSetPixelSizes(face.getPointer(), pixelWidth, pixelHeight);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Load_Glyph(FT_Face face, FT_UInt glyph_index, FT_Int32 load_flags)
    private static native int ftLoadGlyph(long face, long glyph_index, int load_flags);

    public static FTError ftLoadGlyph(FTFace face, int glyphIndex, int loadFlags) {
        final int code = ftLoadGlyph(face.getPointer(), glyphIndex, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadGlyph(FTFace face, int glyphIndex, LoadFlags loadFlags) {
        return ftLoadGlyph(face, glyphIndex, loadFlags.getBits());
    }
    

    // FT_Error FT_Load_Char(FT_Face face, FT_ULong char_code, FT_Int32 load_flags)
    private static native int ftLoadChar(long face, long char_code, int load_flags);

    public static FTError ftLoadChar(FTFace face, long charcode, int loadFlags) {
        final int code = ftLoadChar(face.getPointer(), charcode, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadChar(FTFace face, int charcode, LoadFlags loadFlags) {
        return ftLoadGlyph(face, charcode, loadFlags.getBits());
    }

    
    // void FT_Set_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void ftSetTransform(long face, long matrix, long delta);

    public static void ftSetTransform(FTFace face, FTMatrix matrix, FTVector delta) {
        ftSetTransform(face.getPointer(), matrix.getPointer(), delta.getPointer());
    }

    
    // void FT_Get_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void ftGetTransform(long face, long matrix, long delta);

    public static void ftGetTransform(FTFace face, FTMatrix dstMatrix, FTVector dstDelta) {
        ftGetTransform(face.getPointer(), dstMatrix.getPointer(), dstDelta.getPointer());
    }

    
    // FT_Error FT_Render_Glyph(FT_GlyphSlot slot, FT_Render_Mode render_mode)
    private static native int ftRenderGlyph(long slot, int render_mode);

    public static FTError ftRenderGlyph(FTGlyphSlot slot, FTRenderMode renderMode) {
        final int code = ftRenderGlyph(slot.getPointer(), renderMode.value);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Get_Kerning(FT_Face face, FT_UInt left_glyph, FT_UInt right_glyph, FT_UInt kern_mode, FT_Vector *akerning)
    private static native int ftGetKerning(long face, long left_glyph, long right_glyph, int kern_mode, long akerning);

    public static FTError ftGetKerning(FTFace face, int leftGlyph, int rightGlyph, FTKerningMode kerningMode, FTVector dstKerning) {
        final int code = ftGetKerning(face.getPointer(), leftGlyph, rightGlyph, kerningMode.value, dstKerning.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Get_Track_Kerning(FT_Face face, FT_Fixed point_size, FT_Int degree, FT_Fixed* akerning)
    private static native int ftGetTrackKerning(long face, int point_size, int degree, long akerning);

    public static FTError ftGetTrackKerning(FTFace face, float pointSize, int degree, FTFixed dstKerning) {
        final int code = ftGetTrackKerning(face.getPointer(), FTFixed.of(pointSize), degree, dstKerning.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Select_Charmap(FT_Face face, FT_Encoding encoding)
    private static native int ftSelectCharmap(long face, int encoding);

    public static FTError ftSelectCharmap(FTFace face, FTEncoding encoding) {
        final int code = ftSelectCharmap(face.getPointer(), encoding.value);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Set_Charmap(FT_Face face, FT_CharMap charmap)
    private static native int ftSetCharmap(long face, long charmap);

    public static FTError ftSetCharmap(FTFace face, FTCharMap charmap) {
        final int code = ftSetCharmap(face.getPointer(), charmap.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Int FT_Get_Charmap_Index(FT_CharMap charmap)
    private static native int ftGetCharmapIndex(long charmap);

    public static int ftGetCharmapIndex(FTCharMap charmap) {
        return ftGetCharmapIndex(charmap.getPointer());
    }

    
    // FT_UInt FT_Get_Char_Index(FT_Face face, FT_ULong charcode)
    private static native int ftGetCharIndex(long face, long charcode);

    public static int ftGetCharIndex(FTFace face, long charcode) {
        return ftGetCharIndex(face.getPointer(), charcode);
    }

    
    // FT_ULong FT_Get_First_Char(FT_Face face, FT_UInt *agindex)
    private static native long ftGetFirstChar(long face, long )

    public static long ftGetFirstChar(FTFace face, int* gindex) {

    }
    

    // FT_ULong FT_Get_Next_Char(FT_Face face, FT_ULong char_code, FT_UInt *agindex)
    public static long ftGetNextChar(FTFace face, long charcode, int* gindex) {

    }

    
    // FT_Error FT_Face_Properties(FT_Face face, FT_UInt num_properties, FT_Parameter* properties)
    private static native int ftFaceProperties(long face, long numProperties, FTParameter properties);

    public static FTError ftFaceProperties(FTFace face, int numProperties, FTParameter* properties) {
        final int code = ftFaceProperties();
        return FTError.byCode(code);
    }

    
    // FT_UInt FT_Get_Name_Index(FT_Face face, const FT_String* glyph_name)
    public static int ftGetNameIndex(FTFace face, String* glyphName) {

    }

    
    // FT_Error FT_Get_Glyph_Name(FT_Face face, FT_UInt glyph_index, FT_Pointer buffer, FT_UInt buffer_max)
    private static native int ftGetGlyphName();

    public static FTError ftGetGlyphName(FTFace face, int glyphIndex, FTPointer buffer, int bufferMax) {
        final int code = ftGetGlyphName();
        return FTError.byCode(code);
    }
    
    
    // const char* FT_Get_Postscript_Name(FT_Face face)
    public static String ftGetPostscriptName(FTFace face) {

    }

    
    // FT_Error FT_Get_SubGlyph_Info(FT_GlyphSlot glyph, FT_UInt sub_index, FT_Int *p_index, FT_UInt *p_flags, FT_Int *p_arg1, FT_Int *p_arg2, FT_Matrix *p_transform)
    private static native int ftGetSubGlyphInfo();

    public static FTError ftGetSubGlyphInfo(FTGlyphSlot glyph, int subIndex, int* pIndex, int* pFlags, int* pArg1, int* pArg2, FTMatrix* pTransform) {
        final int code = ftGetSubGlyphInfo();
        return FTError.byCode(code);
    }

    
    // FT_UShort FT_Get_FSType_Flags(FT_Face face)
    public static int ftGetFSTypeFlags(FTFace face) {

    }

    
    // FT_UInt FT_Face_GetCharVariantIndex(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    public static int ftFaceGetCharVariantIndex(FTFace face, long charcode, long variantSelector) {

    }
    
    
    // FT_Int FT_Face_GetCharVariantIsDefault(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    public static int ftFaceGetCharVariantIsDefault(FTFace face, long charcode, long variantSelector) {

    }
    

    // FT_UInt32* FT_Face_GetVariantSelectors(FT_Face face)
    public static int ftFaceGetVariantSelectors(FTFace face) {

    }

    
    // FT_UInt32* FT_Face_GetVariantsOfChar(FT_Face face, FT_ULong charcode)
    public static int ftFaceGetVariantsOfChar(FTFace face, long charcode) {

    }
    

    // FT_UInt32* FT_Face_GetCharsOfVariant(FT_Face face, FT_ULong variantSelector)
    public static int ftFaceGetCharsOfVariant(FTFace face, long variantSelector) {

    }

    
    // FT_Long FT_MulDiv(FT_Long a, FT_Long b, FT_Long c)
    public static long ftMulDiv(long a, long b, long c) {

    }
    
    
    // FT_Long FT_MulFix(FT_Long a, FT_Long b)
    public static long ftMulFix(long a, long b) {

    }

    
    // FT_Long FT_DivFix(FT_Long a, FT_Long b)
    public static long ftDivFix(long a, long b) {

    }
    
    
    // FT_Fixed FT_RoundFix(FT_Fixed a)
    public static FTFixed ftRoundFix(FTFixed a) {

    }

    
    // FT_Fixed FT_CeilFix(FT_Fixed a)
    public static FTFixed ftCeilFix(FTFixed a) {

    }

    
    // FT_Fixed FT_FloorFix(FT_Fixed a)
    public static FTFixed ftFloorFix(FTFixed a) {

    }

    
    // void FT_Vector_Transform(FT_Vector* vector, const FT_Matrix* matrix)
    public static void ftVectorTransform(FTVector vector, FTMatrix* matrix) {

    }

    
    // void FT_Library_Version(FT_Library library, FT_Int *amajor, FT_Int *aminor, FT_Int *apatch)
    public static void ftLibraryVersion(FTLibrary library, int* major, int* minor, int* patch) {

    }

    
    // FT_Bool FT_Face_CheckTrueTypePatents(FT_Face face)
    public static boolean ftFaceCheckTrueTypePatents(FTFace face) {

    }

    
    // FT_Bool FT_Face_SetUnpatentedHinting(FT_Face face, FT_Bool value)
    public static boolean ftFaceSetUnpatentedHinting(FTFace face, boolean value) {
        
    }
    
}
