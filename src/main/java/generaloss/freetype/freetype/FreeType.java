package generaloss.freetype.freetype;

import generaloss.freetype.FTStructRegistry;
import generaloss.freetype.types.*;
import generaloss.freetype.FTStruct;

import java.nio.ByteBuffer;

// freetype.h
public class FreeType {

    // FT_Error FT_Init_FreeType(FT_Library *alibrary)
    private static native int FT_Init_FreeType(long alibrary);

    public static FTError ftInitFreeType(FTLibrary dstLibrary) {
        final int code = FT_Init_FreeType(dstLibrary.getPointer());
        return FTError.byCode(code);
    }
    
    
    // FT_Error FT_Done_FreeType(FT_Library library)
    private static native int FT_Done_FreeType(long library);

    public static FTError ftDoneFreeType(FTLibrary library) {
        final int code = FT_Done_FreeType(library.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_New_Face(FT_Library library, const char* filepathname, FT_Long face_index, FT_Face *aface)
    private static native int FT_New_Face(long library, String filepathname, long face_index, long aface);

    public static FTError ftNewFace(FTLibrary library, String filepath, long faceIndex, FTFace dstFace) {
        final int code = FT_New_Face(library.getPointer(), filepath, faceIndex, dstFace.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_New_Memory_Face(FT_Library library, const FT_Byte* file_base, FT_Long file_size, FT_Long face_index, FT_Face *aface)
    private static native int FT_New_Memory_Face(long library, ByteBuffer file_base, long file_size, long face_index, long aface);

    public static FTError ftNewMemoryFace(FTLibrary library, ByteBuffer fileBase, long fileSize, long faceIndex, FTFace dstFace) {
        final int code = FT_New_Memory_Face(library.getPointer(), fileBase, fileSize, faceIndex, dstFace.getPointer());
        return FTError.byCode(code);
    }

    public static FTError ftNewMemoryFace(FTLibrary library, byte[] fileBase, long fileSize, long faceIndex, FTFace dstFace) {
        final ByteBuffer fileBaseBuf = ByteBuffer.allocateDirect(fileBase.length);
        fileBaseBuf.put(fileBase);
        fileBaseBuf.position(0);
        return ftNewMemoryFace(library, fileBaseBuf, fileSize, faceIndex, dstFace);
    }


    // FT_Error FT_Open_Face(FT_Library library, const FT_Open_Args* args, FT_Long face_index, FT_Face *aface)
    private static native int FT_Open_Face(long library, long args, long face_index, long aface);

    public static FTError ftOpenFace(FTLibrary library, FTOpenArgs args, long faceIndex, FTFace dstFace) {
        final int code = FT_Open_Face(library.getPointer(), args.getPointer(), faceIndex, dstFace.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Attach_File(FT_Face face, const char* filepathname)
    private static native int FT_Attach_File(long face, String filepathname);

    public static FTError ftAttachFile(FTFace face, String filepath) {
        final int code = FT_Attach_File(face.getPointer(), filepath);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Attach_Stream(FT_Face face, const FT_Open_Args* parameters)
    private static native int FT_Attach_Stream(long face, long parameters);

    public static FTError ftAttachStream(FTFace face, FTOpenArgs parameters) {
        final int code = FT_Attach_Stream(face.getPointer(), parameters.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Reference_Face(FT_Face face)
    private static native int FT_Reference_Face(long face);

    public static FTError ftReferenceFace(FTFace face) {
        final int code = FT_Reference_Face(face.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Done_Face(FT_Face face)
    private static native int FT_Done_Face(long face);

    public static FTError ftDoneFace(FTFace face) {
        final int code = FT_Done_Face(face.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Select_Size(FT_Face face, FT_Int strike_index)
    private static native int FT_Select_Size(long face, int strike_index);

    public static FTError ftSelectSize(FTFace face, int strikeIndex) {
        final int code = FT_Select_Size(face.getPointer(), strikeIndex);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Request_Size(FT_Face face, FT_Size_Request req)
    private static native int FT_Request_Size(long face, long req);

    public static FTError ftRequestSize(FTFace face, FTSizeRequest reqest) {
        final int code = FT_Request_Size(face.getPointer(), reqest.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Set_Char_Size(FT_Face face, FT_F26Dot6 char_width, FT_F26Dot6 char_height, FT_UInt horz_resolution, FT_UInt vert_resolution)
    private static native int FT_Set_Char_Size(long face, int char_width, int char_height, long horz_resolution, long vert_resolution);

    public static FTError ftSetCharSize(FTFace face, float charWidth, float charHeight, long horzResolution, long vertResolution) {
        final int code = FT_Set_Char_Size(face.getPointer(), FTF26Dot6.of(charWidth), FTF26Dot6.of(charHeight), horzResolution, vertResolution);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Set_Pixel_Sizes(FT_Face face, FT_UInt pixel_width, FT_UInt pixel_height)
    private static native int FT_Set_Pixel_Sizes(long face, long pixel_width, long pixel_height);

    public static FTError ftSetPixelSizes(FTFace face, long pixelWidth, long pixelHeight) {
        final int code = FT_Set_Pixel_Sizes(face.getPointer(), pixelWidth, pixelHeight);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Load_Glyph(FT_Face face, FT_UInt glyph_index, FT_Int32 load_flags)
    private static native int FT_Load_Glyph(long face, long glyph_index, int load_flags);

    public static FTError ftLoadGlyph(FTFace face, int glyphIndex, int loadFlags) {
        final int code = FT_Load_Glyph(face.getPointer(), glyphIndex, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadGlyph(FTFace face, int glyphIndex, LoadFlags loadFlags) {
        return ftLoadGlyph(face, glyphIndex, loadFlags.getBits());
    }
    

    // FT_Error FT_Load_Char(FT_Face face, FT_ULong char_code, FT_Int32 load_flags)
    private static native int FT_Load_Char(long face, long char_code, int load_flags);

    public static FTError ftLoadChar(FTFace face, long charcode, int loadFlags) {
        final int code = FT_Load_Char(face.getPointer(), charcode, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadChar(FTFace face, int charcode, LoadFlags loadFlags) {
        return ftLoadGlyph(face, charcode, loadFlags.getBits());
    }

    
    // void FT_Set_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void FT_Set_Transform(long face, long matrix, long delta);

    public static void ftSetTransform(FTFace face, FTMatrix matrix, FTVector delta) {
        FT_Set_Transform(face.getPointer(), matrix.getPointer(), delta.getPointer());
    }

    
    // void FT_Get_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void FT_Get_Transform(long face, long matrix, long delta);

    public static void ftGetTransform(FTFace face, FTMatrix dstMatrix, FTVector dstDelta) {
        FT_Get_Transform(face.getPointer(), dstMatrix.getPointer(), dstDelta.getPointer());
    }

    
    // FT_Error FT_Render_Glyph(FT_GlyphSlot slot, FT_Render_Mode render_mode)
    private static native int FT_Render_Glyph(long slot, int render_mode);

    public static FTError ftRenderGlyph(FTGlyphSlot slot, FTRenderMode renderMode) {
        final int code = FT_Render_Glyph(slot.getPointer(), renderMode.value);
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Get_Kerning(FT_Face face, FT_UInt left_glyph, FT_UInt right_glyph, FT_UInt kern_mode, FT_Vector *akerning)
    private static native int FT_Get_Kerning(long face, long left_glyph, long right_glyph, int kern_mode, long akerning);

    public static FTError ftGetKerning(FTFace face, int leftGlyph, int rightGlyph, FTKerningMode kerningMode, FTVector dstKerning) {
        final int code = FT_Get_Kerning(face.getPointer(), leftGlyph, rightGlyph, kerningMode.value, dstKerning.getPointer());
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Get_Track_Kerning(FT_Face face, FT_Fixed point_size, FT_Int degree, FT_Fixed* akerning)
    private static native int FT_Get_Track_Kerning(long face, int point_size, int degree, long akerning);

    public static FTError ftGetTrackKerning(FTFace face, float pointSize, int degree, FTFixed dstKerning) {
        final int code = FT_Get_Track_Kerning(face.getPointer(), FTFixed.of(pointSize), degree, dstKerning.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Error FT_Select_Charmap(FT_Face face, FT_Encoding encoding)
    private static native int FT_Select_Charmap(long face, int encoding);

    public static FTError ftSelectCharmap(FTFace face, FTEncoding encoding) {
        final int code = FT_Select_Charmap(face.getPointer(), encoding.value);
        return FTError.byCode(code);
    }
    

    // FT_Error FT_Set_Charmap(FT_Face face, FT_CharMap charmap)
    private static native int FT_Set_Charmap(long face, long charmap);

    public static FTError ftSetCharmap(FTFace face, FTCharMap charmap) {
        final int code = FT_Set_Charmap(face.getPointer(), charmap.getPointer());
        return FTError.byCode(code);
    }

    
    // FT_Int FT_Get_Charmap_Index(FT_CharMap charmap)
    private static native int FT_Get_Charmap_Index(long charmap);

    public static int ftGetCharmapIndex(FTCharMap charmap) {
        return FT_Get_Charmap_Index(charmap.getPointer());
    }

    
    // FT_UInt FT_Get_Char_Index(FT_Face face, FT_ULong charcode)
    private static native int FT_Get_Char_Index(long face, long charcode);

    public static int ftGetCharIndex(FTFace face, long charcode) {
        return FT_Get_Char_Index(face.getPointer(), charcode);
    }

    
    // FT_ULong FT_Get_First_Char(FT_Face face, FT_UInt *agindex)
    private static native long FT_Get_First_Char(long face, long[] agindex);

    public static long ftGetFirstChar(FTFace face, long[] gindex) {
        return FT_Get_First_Char(face.getPointer(), gindex);
    }
    

    // FT_ULong FT_Get_Next_Char(FT_Face face, FT_ULong char_code, FT_UInt *agindex)
    public static native long FT_Get_Next_Char(long face, long char_code, long[] agindex);

    public static long ftGetNextChar(FTFace face, long charcode, long[] gindex) {
        return FT_Get_Next_Char(face.getPointer(), charcode, gindex);
    }

    
    // FT_Error FT_Face_Properties(FT_Face face, FT_UInt num_properties, FT_Parameter* properties)
    private static native int FT_Face_Properties(long face, long num_properties, long[] properties);

    public static FTError ftFaceProperties(FTFace face, int numProperties, FTParameter[] properties) {
        final long[] pointers = FTStruct.makePointerArray(properties);
        final int code = FT_Face_Properties(face.getPointer(), numProperties, pointers);
        return FTError.byCode(code);
    }

    
    // FT_UInt FT_Get_Name_Index(FT_Face face, const FT_String* glyph_name)
    private static native long FT_Get_Name_Index(long face, String glyph_name);

    public static long ftGetNameIndex(FTFace face, String glyphName) {
        return FT_Get_Name_Index(face.getPointer(), glyphName);
    }

    
    // FT_Error FT_Get_Glyph_Name(FT_Face face, FT_UInt glyph_index, FT_Pointer buffer, FT_UInt buffer_max)
    private static native int FT_Get_Glyph_Name(long face, long glyph_index, ByteBuffer buffer, long buffer_max);

    public static FTError ftGetGlyphName(FTFace face, int glyphIndex, ByteBuffer buffer, int bufferMax) {
        final int code = FT_Get_Glyph_Name(face.getPointer(), glyphIndex, buffer, bufferMax);
        return FTError.byCode(code);
    }
    
    
    // const char* FT_Get_Postscript_Name(FT_Face face)
    private static native String FT_Get_Postscript_Name(long face);

    public static String ftGetPostscriptName(FTFace face) {
        return FT_Get_Postscript_Name(face.getPointer());
    }

    
    // FT_Error FT_Get_SubGlyph_Info(FT_GlyphSlot glyph, FT_UInt sub_index, FT_Int *p_index, FT_UInt *p_flags, FT_Int *p_arg1, FT_Int *p_arg2, FT_Matrix *p_transform)
    private static native int FT_Get_SubGlyph_Info(long glyph, long sub_index, int[] p_index, long[] p_flags, int[] p_arg1, int[] p_arg2, FTMatrix p_transform);

    public static FTError ftGetSubGlyphInfo(FTGlyphSlot glyph, long subIndex, int[] pIndex, long[] pFlags, int[] pArg1, int[] pArg2, FTMatrix dstPTransform) {
        final int code = FT_Get_SubGlyph_Info(glyph.getPointer(), subIndex, pIndex, pFlags, pArg1, pArg2, dstPTransform);
        return FTError.byCode(code);
    }

    
    // FT_UShort FT_Get_FSType_Flags(FT_Face face)
    private static native int FT_Get_FSType_Flags(long face);

    public static int ftGetFSTypeFlags(FTFace face) {
        return FT_Get_FSType_Flags(face.getPointer());
    }

    
    // FT_UInt FT_Face_GetCharVariantIndex(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    private static native int FT_Face_GetCharVariantIndex(long face, long charcode, long variantSelector);

    public static int ftFaceGetCharVariantIndex(FTFace face, long charcode, long variantSelector) {
        return FT_Face_GetCharVariantIndex(face.getPointer(), charcode, variantSelector);
    }
    
    
    // FT_Int FT_Face_GetCharVariantIsDefault(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    private static native int FT_Face_GetCharVariantIsDefault(long face, long charcode, long variantSelector);

    public static int ftFaceGetCharVariantIsDefault(FTFace face, long charcode, long variantSelector) {
        return FT_Face_GetCharVariantIsDefault(face.getPointer(), charcode, variantSelector);
    }
    

    // FT_UInt32* FT_Face_GetVariantSelectors(FT_Face face)
    private static native int FT_Face_GetVariantSelectors(long face);

    public static int ftFaceGetVariantSelectors(FTFace face) {
        return FT_Face_GetVariantSelectors(face.getPointer());
    }

    
    // FT_UInt32* FT_Face_GetVariantsOfChar(FT_Face face, FT_ULong charcode)
    private static native int FT_Face_GetVariantsOfChar(long face, long charcode);

    public static int ftFaceGetVariantsOfChar(FTFace face, long charcode) {
        return FT_Face_GetVariantsOfChar(face.getPointer(), charcode);
    }
    

    // FT_UInt32* FT_Face_GetCharsOfVariant(FT_Face face, FT_ULong variantSelector)
    private static native int FT_Face_GetCharsOfVariant(long face, long variantSelector);

    public static int ftFaceGetCharsOfVariant(FTFace face, long variantSelector) {
        return FT_Face_GetCharsOfVariant(face.getPointer(), variantSelector);
    }

    
    // FT_Long FT_MulDiv(FT_Long a, FT_Long b, FT_Long c)
    private static native long FT_MulDiv(long a, long b, long c);

    public static long ftMulDiv(long a, long b, long c) {
        return FT_MulDiv(a, b, c);
    }
    
    
    // FT_Long FT_MulFix(FT_Long a, FT_Long b)
    private static native long FT_MulFix(long a, long b);

    public static long ftMulFix(long a, long b) {
        return FT_MulFix(a, b);
    }

    
    // FT_Long FT_DivFix(FT_Long a, FT_Long b)
    private static native long FT_DivFix(long a, long b);

    public static long ftDivFix(long a, long b) {
        return FT_DivFix(a, b);
    }
    
    
    // FT_Fixed FT_RoundFix(FT_Fixed a)
    private static native long FT_RoundFix(long a);

    public static FTFixed ftRoundFix(FTFixed a) {
        final long pointer = FT_RoundFix(a.getPointer());
        return FTStructRegistry.getOrCreate(pointer, FTFixed::new);
    }

    
    // FT_Fixed FT_CeilFix(FT_Fixed a)
    private static native long FT_CeilFix(long a);

    public static FTFixed ftCeilFix(FTFixed a) {
        final long pointer = FT_CeilFix(a.getPointer());
        return FTStructRegistry.getOrCreate(pointer, FTFixed::new);
    }

    
    // FT_Fixed FT_FloorFix(FT_Fixed a)
    private static native long FT_FloorFix(long a);

    public static FTFixed ftFloorFix(FTFixed a) {
        final long pointer = FT_FloorFix(a.getPointer());
        return FTStructRegistry.getOrCreate(pointer, FTFixed::new);
    }

    
    // void FT_Vector_Transform(FT_Vector* vector, const FT_Matrix* matrix)
    private static native void FT_Vector_Transform(long vector, long matrix);

    public static void ftVectorTransform(FTVector vector, FTMatrix matrix) {
        FT_Vector_Transform(vector.getPointer(), matrix.getPointer());
    }

    
    // void FT_Library_Version(FT_Library library, FT_Int *amajor, FT_Int *aminor, FT_Int *apatch)
    private static native void FT_Library_Version(long library, int[] amajor, int[] aminor, int[] apatch);

    public static void ftLibraryVersion(FTLibrary library, int[] dstMajor, int[] dstMinor, int[] dstPatch) {
        FT_Library_Version(library.getPointer(), dstMajor, dstMinor, dstPatch);
    }

    
    // FT_Bool FT_Face_CheckTrueTypePatents(FT_Face face)
    private static native boolean FT_Face_CheckTrueTypePatents(long face);

    public static boolean ftFaceCheckTrueTypePatents(FTFace face) {
        return FT_Face_CheckTrueTypePatents(face.getPointer());
    }

    
    // FT_Bool FT_Face_SetUnpatentedHinting(FT_Face face, FT_Bool value)
    private static native boolean FT_Face_SetUnpatentedHinting(long face, boolean value);

    public static boolean ftFaceSetUnpatentedHinting(FTFace face, boolean value) {
        return FT_Face_SetUnpatentedHinting(face.getPointer(), value);
    }
    
}