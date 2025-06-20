package generaloss.freetype;

import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTGlyphLoader;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.image.FTOutline;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.stroke.FTStrokerLinecap;
import generaloss.freetype.stroke.FTStrokerLinejoin;
import generaloss.freetype.system.FTMemory;
import generaloss.freetype.types.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FreeType {

    private static final String LIBRARY_NAME = "freetype_jni";

    static {
        loadNative();
    }

    public static void init() { }

    @SuppressWarnings("UnsafeDynamicallyLoadedCode")
    private static void loadNative() {
        final String os = detectOS();

        if(os.equals("android")) {
            System.loadLibrary(LIBRARY_NAME);
            return;
        }

        final String arch = detectArch();
        final String libName = (os.equals("windows") ? LIBRARY_NAME + ".dll" : "lib" + LIBRARY_NAME + ".so");
        final String pathInJar = String.format("/jni/%s/%s/%s", os, arch, libName);

        try(InputStream in = FTLibrary.class.getResourceAsStream(pathInJar)) {
            if(in == null)
                throw new UnsatisfiedLinkError("Native library not found: " + pathInJar);

            final Path temp = Files.createTempFile(LIBRARY_NAME, libName);
            temp.toFile().deleteOnExit();

            try(OutputStream out = Files.newOutputStream(temp)) {
                final byte[] buffer = new byte[4096];
                int read;
                while((read = in.read(buffer)) != -1)
                    out.write(buffer, 0, read);
            }

            System.load(temp.toAbsolutePath().toString());
        }catch(IOException e) {
            throw new RuntimeException("Failed to load native library", e);
        }
    }

    private static String detectOS() {
        final String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            return "windows";

        if(os.contains("linux")) {
            String vm = System.getProperty("java.vm.name").toLowerCase();
            if(vm.contains("dalvik") || vm.contains("art"))
                return "android";
            return "linux";
        }
        throw new UnsupportedOperationException("Unsupported OS: " + os);
    }

    private static String detectArch() {
        final String arch = System.getProperty("os.arch").toLowerCase();
        if(arch.contains("amd64") || arch.contains("x86_64")) return "x86_64";
        if(arch.contains("86")) return "i686";
        if(arch.contains("aarch64") || arch.contains("arm64")) return "aarch64";
        if(arch.contains("riscv")) return "riscv64";
        if(arch.contains("x86")) return "x86";
        throw new UnsupportedOperationException("Unsupported architecture: " + arch);
    }


    // ------------------------------
    // ---      freetype.h        ---
    // ------------------------------


    // FT_Error FT_Init_FreeType(FT_Library *alibrary)
    private static native int FT_Init_FreeType(long[] alibrary);

    public static FTError ftInitFreeType(long[] dstLibraryPointer) {
        final int code = FT_Init_FreeType(dstLibraryPointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_Done_FreeType(FT_Library library)
    private static native int FT_Done_FreeType(long library);

    public static FTError ftDoneFreeType(FTLibrary library) {
        final int code = FT_Done_FreeType(FTStruct.getPointer(library));
        if(library != null) {
            library.destroyPointer();
            FTStructCache.clear();
        }
        return FTError.byCode(code);
    }

    // FT_Error FT_New_Face(FT_Library library, const char* filepathname, FT_Long face_index, FT_Face *aface)
    private static native int FT_New_Face(long library, String filepathname, long face_index, long[] aface);

    public static FTError ftNewFace(FTLibrary library, String filepath, long faceIndex, long[] dstFacePointer) {
        final int code = FT_New_Face(FTStruct.getPointer(library), filepath, faceIndex, dstFacePointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_New_Memory_Face(FT_Library library, const FT_Byte* file_base, FT_Long file_size, FT_Long face_index, FT_Face *aface)
    private static native int FT_New_Memory_Face(long library, ByteBuffer file_base, long file_size, long face_index, long[] aface);

    public static FTError ftNewMemoryFace(FTLibrary library, ByteBuffer dataBuffer, long dataSize, long faceIndex, long[] dstFacePointer) {
        final int code = FT_New_Memory_Face(FTStruct.getPointer(library), dataBuffer, dataSize, faceIndex, dstFacePointer);
        return FTError.byCode(code);
    }

    public static FTError ftNewMemoryFace(FTLibrary library, ByteBuffer dataBuffer, long faceIndex, long[] dstFacePointer) {
        return ftNewMemoryFace(library, dataBuffer, dataBuffer.remaining(), faceIndex, dstFacePointer);
    }

    public static FTError ftNewMemoryFace(FTLibrary library, byte[] data, long dataSize, long faceIndex, long[] dstFacePointer) {
        if(data == null)
            throw new NullPointerException("Data is null");
        final ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.put(data);
        buffer.flip();
        return ftNewMemoryFace(library, buffer, dataSize, faceIndex, dstFacePointer);
    }

    public static FTError ftNewMemoryFace(FTLibrary library, byte[] data, long faceIndex, long[] dstFacePointer) {
        return ftNewMemoryFace(library, data, data.length, faceIndex, dstFacePointer);
    }

    // FT_Error FT_Open_Face(FT_Library library, const FT_Open_Args* args, FT_Long face_index, FT_Face *aface)
    private static native int FT_Open_Face(long library, long args, long face_index, long[] aface);

    public static FTError ftOpenFace(FTLibrary library, FTOpenArgs args, long faceIndex, long[] dstFacePointer) {
        final int code = FT_Open_Face(FTStruct.getPointer(library), FTStruct.getPointer(args), faceIndex, dstFacePointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_Attach_File(FT_Face face, const char* filepathname)
    private static native int FT_Attach_File(long face, String filepathname);

    public static FTError ftAttachFile(FTFace face, String filepath) {
        final int code = FT_Attach_File(FTStruct.getPointer(face), filepath);
        return FTError.byCode(code);
    }

    // FT_Error FT_Attach_Stream(FT_Face face, const FT_Open_Args* parameters)
    private static native int FT_Attach_Stream(long face, long parameters);

    public static FTError ftAttachStream(FTFace face, FTOpenArgs parameters) {
        final int code = FT_Attach_Stream(FTStruct.getPointer(face), FTStruct.getPointer(parameters));
        return FTError.byCode(code);
    }

    // FT_Error FT_Reference_Face(FT_Face face)
    private static native int FT_Reference_Face(long face);

    public static FTError ftReferenceFace(FTFace face) {
        final int code = FT_Reference_Face(FTStruct.getPointer(face));
        return FTError.byCode(code);
    }

    // FT_Error FT_Done_Face(FT_Face face)
    private static native int FT_Done_Face(long face, boolean[] dstDestroyFlag);

    public static FTError ftDoneFace(FTFace face) {
        final boolean[] dstDestroyFlag = new boolean[1];
        final int code = FT_Done_Face(FTStruct.getPointer(face), dstDestroyFlag);
        if(dstDestroyFlag[0])
            face.destroyPointer();

        return FTError.byCode(code);
    }

    // FT_Error FT_Select_Size(FT_Face face, FT_Int strike_index)
    private static native int FT_Select_Size(long face, int strike_index);

    public static FTError ftSelectSize(FTFace face, int strikeIndex) {
        final int code = FT_Select_Size(FTStruct.getPointer(face), strikeIndex);
        return FTError.byCode(code);
    }

    // FT_Error FT_Request_Size(FT_Face face, FT_Size_Request req)
    private static native int FT_Request_Size(long face, long req);

    public static FTError ftRequestSize(FTFace face, FTSizeRequest request) {
        final int code = FT_Request_Size(FTStruct.getPointer(face), FTStruct.getPointer(request));
        return FTError.byCode(code);
    }

    // FT_Error FT_Set_Char_Size(FT_Face face, FT_F26Dot6 char_width, FT_F26Dot6 char_height, FT_UInt horz_resolution, FT_UInt vert_resolution)
    private static native int FT_Set_Char_Size(long face, int char_width, int char_height, long horz_resolution, long vert_resolution);

    public static FTError ftSetCharSize(FTFace face, float charWidth, float charHeight, long horzResolution, long vertResolution) {
        final int code = FT_Set_Char_Size(FTStruct.getPointer(face), FTF26Dot6.of(charWidth), FTF26Dot6.of(charHeight), horzResolution, vertResolution);
        return FTError.byCode(code);
    }

    // FT_Error FT_Set_Pixel_Sizes(FT_Face face, FT_UInt pixel_width, FT_UInt pixel_height)
    private static native int FT_Set_Pixel_Sizes(long face, long pixel_width, long pixel_height);

    public static FTError ftSetPixelSizes(FTFace face, long pixelWidth, long pixelHeight) {
        final int code = FT_Set_Pixel_Sizes(FTStruct.getPointer(face), pixelWidth, pixelHeight);
        return FTError.byCode(code);
    }

    // FT_Error FT_Load_Glyph(FT_Face face, FT_UInt glyph_index, FT_Int32 load_flags)
    private static native int FT_Load_Glyph(long face, long glyph_index, int load_flags);

    public static FTError ftLoadGlyph(FTFace face, long glyphIndex, int loadFlags) {
        final int code = FT_Load_Glyph(FTStruct.getPointer(face), glyphIndex, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadGlyph(FTFace face, long glyphIndex, LoadFlags loadFlags) {
        return ftLoadGlyph(face, glyphIndex, BitMask.getBits(loadFlags));
    }

    public static FTError ftLoadGlyph(FTFace face, long glyphIndex, FTLoad loadFlag) {
        return ftLoadGlyph(face, glyphIndex, loadFlag.getBit());
    }

    public static FTError ftLoadGlyph(FTFace face, long glyphIndex, FTLoadTarget loadFlag) {
        return ftLoadGlyph(face, glyphIndex, loadFlag.value);
    }

    public static FTError ftLoadGlyph(FTFace face, long glyphIndex) {
        return ftLoadGlyph(face, glyphIndex, FTLoad.DEFAULT);
    }

    // FT_Error FT_Load_Char(FT_Face face, FT_ULong char_code, FT_Int32 load_flags)
    private static native int FT_Load_Char(long face, long char_code, int load_flags);

    public static FTError ftLoadChar(FTFace face, long charcode, int loadFlags) {
        final int code = FT_Load_Char(FTStruct.getPointer(face), charcode, loadFlags);
        return FTError.byCode(code);
    }

    public static FTError ftLoadChar(FTFace face, long charcode, LoadFlags loadFlags) {
        return ftLoadChar(face, charcode, BitMask.getBits(loadFlags));
    }

    public static FTError ftLoadChar(FTFace face, long charcode, FTLoad loadFlag) {
        return ftLoadChar(face, charcode, loadFlag.getBit());
    }

    public static FTError ftLoadChar(FTFace face, long charcode, FTLoadTarget loadFlag) {
        return ftLoadChar(face, charcode, loadFlag.value);
    }

    public static FTError ftLoadChar(FTFace face, long charcode) {
        return ftLoadChar(face, charcode, FTLoad.DEFAULT);
    }

    // void FT_Set_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void FT_Set_Transform(long face, long matrix, long delta);

    public static void ftSetTransform(FTFace face, FTMatrix matrix, FTVector delta) {
        FT_Set_Transform(FTStruct.getPointer(face), FTStruct.getPointer(matrix), FTStruct.getPointer(delta));
    }

    // void FT_Get_Transform(FT_Face face, FT_Matrix* matrix, FT_Vector* delta)
    private static native void FT_Get_Transform(long face, long matrix, long delta);

    public static void ftGetTransform(FTFace face, FTMatrix dstMatrix, FTVector dstDelta) {
        FT_Get_Transform(FTStruct.getPointer(face), FTStruct.getPointer(dstMatrix), FTStruct.getPointer(dstDelta));
    }

    // FT_Error FT_Render_Glyph(FT_GlyphSlot slot, FT_Render_Mode render_mode)
    private static native int FT_Render_Glyph(long slot, int render_mode);

    public static FTError ftRenderGlyph(FTGlyphSlot slot, FTRenderMode renderMode) {
        if(renderMode == null)
            renderMode = FTRenderMode.NORMAL;
        final int code = FT_Render_Glyph(FTStruct.getPointer(slot), renderMode.value);
        return FTError.byCode(code);
    }

    // FT_Error FT_Get_Kerning(FT_Face face, FT_UInt left_glyph, FT_UInt right_glyph, FT_UInt kern_mode, FT_Vector *akerning)
    private static native int FT_Get_Kerning(long face, long left_glyph, long right_glyph, int kern_mode, long akerning);

    public static FTError ftGetKerning(FTFace face, long leftGlyph, long rightGlyph, FTKerningMode kerningMode, FTVector dstKerning) {
        if(kerningMode == null)
            kerningMode = FTKerningMode.DEFAULT;
        final int code = FT_Get_Kerning(FTStruct.getPointer(face), leftGlyph, rightGlyph, kerningMode.value, FTStruct.getPointer(dstKerning));
        return FTError.byCode(code);
    }

    public static FTError ftGetKerning(FTFace face, long leftGlyph, long rightGlyph, FTVector dstKerning) {
        return ftGetKerning(face, leftGlyph, rightGlyph, FTKerningMode.DEFAULT, dstKerning);
    }

    // FT_Error FT_Get_Track_Kerning(FT_Face face, FT_Fixed point_size, FT_Int degree, FT_Fixed* akerning)
    private static native int FT_Get_Track_Kerning(long face, int point_size, int degree, long akerning);

    public static FTError ftGetTrackKerning(FTFace face, float pointSize, int degree, FTFixed dstKerning) {
        final int code = FT_Get_Track_Kerning(FTStruct.getPointer(face), FTFixed.of(pointSize), degree, FTStruct.getPointer(dstKerning));
        return FTError.byCode(code);
    }

    // FT_Error FT_Select_Charmap(FT_Face face, FT_Encoding encoding)
    private static native int FT_Select_Charmap(long face, int encoding);

    public static FTError ftSelectCharmap(FTFace face, FTEncoding encoding) {
        if(encoding == null)
            encoding = FTEncoding.NONE;
        final int code = FT_Select_Charmap(FTStruct.getPointer(face), encoding.value);
        return FTError.byCode(code);
    }

    // FT_Error FT_Set_Charmap(FT_Face face, FT_CharMap charmap)
    private static native int FT_Set_Charmap(long face, long charmap);

    public static FTError ftSetCharmap(FTFace face, FTCharMap charmap) {
        final int code = FT_Set_Charmap(FTStruct.getPointer(face), FTStruct.getPointer(charmap));
        return FTError.byCode(code);
    }

    // FT_Int FT_Get_Charmap_Index(FT_CharMap charmap)
    private static native int FT_Get_Charmap_Index(long charmap);

    public static int ftGetCharmapIndex(FTCharMap charmap) {
        return FT_Get_Charmap_Index(FTStruct.getPointer(charmap));
    }

    // FT_UInt FT_Get_Char_Index(FT_Face face, FT_ULong charcode)
    private static native long FT_Get_Char_Index(long face, long charcode);

    public static long ftGetCharIndex(FTFace face, long charcode) {
        return FT_Get_Char_Index(FTStruct.getPointer(face), charcode);
    }

    // FT_ULong FT_Get_First_Char(FT_Face face, FT_UInt *agindex)
    private static native long FT_Get_First_Char(long face, long[] agindex);

    public static long ftGetFirstChar(FTFace face, long[] dstGlyphIndex) {
        return FT_Get_First_Char(FTStruct.getPointer(face), dstGlyphIndex);
    }

    // FT_ULong FT_Get_Next_Char(FT_Face face, FT_ULong char_code, FT_UInt *agindex)
    private static native long FT_Get_Next_Char(long face, long char_code, long[] agindex);

    public static long ftGetNextChar(FTFace face, long charcode, long[] dstGlyphIndex) {
        return FT_Get_Next_Char(FTStruct.getPointer(face), charcode, dstGlyphIndex);
    }

    // FT_Error FT_Face_Properties(FT_Face face, FT_UInt num_properties, FT_Parameter* properties)
    private static native int FT_Face_Properties(long face, long num_properties, long[] properties);

    public static FTError ftFaceProperties(FTFace face, int numProperties, FTParameter... properties) {
        final long[] pointers = FTStruct.makePointerArray(properties);
        final int code = FT_Face_Properties(FTStruct.getPointer(face), numProperties, pointers);
        return FTError.byCode(code);
    }

    public static FTError ftFaceProperties(FTFace face, FTParameter... properties) {
        final int numProperties = (properties == null ? 0 : properties.length);
        return ftFaceProperties(face, numProperties, properties);
    }

    // FT_UInt FT_Get_Name_Index(FT_Face face, const FT_String* glyph_name)
    private static native long FT_Get_Name_Index(long face, String glyph_name);

    public static long ftGetNameIndex(FTFace face, String glyphName) {
        return FT_Get_Name_Index(FTStruct.getPointer(face), glyphName);
    }

    // FT_Error FT_Get_Glyph_Name(FT_Face face, FT_UInt glyph_index, FT_Pointer buffer, FT_UInt buffer_max)
    private static native int FT_Get_Glyph_Name(long face, long glyph_index, ByteBuffer buffer, long buffer_max);

    public static FTError ftGetGlyphName(FTFace face, long glyphIndex, ByteBuffer buffer, long bufferMax) {
        final int code = FT_Get_Glyph_Name(FTStruct.getPointer(face), glyphIndex, buffer, bufferMax);
        return FTError.byCode(code);
    }

    public static FTError ftGetGlyphName(FTFace face, long glyphIndex, ByteBuffer buffer) {
        final long bufferMax = (buffer == null ? 0 : buffer.limit());
        return ftGetGlyphName(face, glyphIndex, buffer, bufferMax);
    }

    // const char* FT_Get_Postscript_Name(FT_Face face)
    private static native String FT_Get_Postscript_Name(long face);

    public static String ftGetPostscriptName(FTFace face) {
        return FT_Get_Postscript_Name(FTStruct.getPointer(face));
    }

    // FT_Error FT_Get_SubGlyph_Info(FT_GlyphSlot glyph, FT_UInt sub_index, FT_Int *p_index, FT_UInt *p_flags, FT_Int *p_arg1, FT_Int *p_arg2, FT_Matrix *p_transform)
    private static native int FT_Get_SubGlyph_Info(long glyph, long sub_index, int[] p_index, long[] p_flags, int[] p_arg1, int[] p_arg2, long p_transform);

    public static FTError ftGetSubGlyphInfo(FTGlyphSlot glyph, long subIndex, int[] dstPIndex, long[] dstPFlags, int[] dstPArg1, int[] dstPArg2, FTMatrix dstPTransform) {
        final int code = FT_Get_SubGlyph_Info(FTStruct.getPointer(glyph), subIndex, dstPIndex, dstPFlags, dstPArg1, dstPArg2, FTStruct.getPointer(dstPTransform));
        return FTError.byCode(code);
    }

    // FT_UShort FT_Get_FSType_Flags(FT_Face face)
    private static native int FT_Get_FSType_Flags(long face);

    public static int ftGetFSTypeFlagsRaw(FTFace face) {
        return FT_Get_FSType_Flags(FTStruct.getPointer(face));
    }

    public static FSTypeFlags ftGetFSTypeFlags(FTFace face) {
        return new FSTypeFlags(ftGetFSTypeFlagsRaw(face));
    }

    // FT_UInt FT_Face_GetCharVariantIndex(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    private static native long FT_Face_GetCharVariantIndex(long face, long charcode, long variantSelector);

    public static long ftFaceGetCharVariantIndex(FTFace face, long charcode, long variantSelector) {
        return FT_Face_GetCharVariantIndex(FTStruct.getPointer(face), charcode, variantSelector);
    }

    // FT_Int FT_Face_GetCharVariantIsDefault(FT_Face face, FT_ULong charcode, FT_ULong variantSelector)
    private static native int FT_Face_GetCharVariantIsDefault(long face, long charcode, long variantSelector);

    public static int ftFaceGetCharVariantIsDefault(FTFace face, long charcode, long variantSelector) {
        return FT_Face_GetCharVariantIsDefault(FTStruct.getPointer(face), charcode, variantSelector);
    }

    // FT_UInt32* FT_Face_GetVariantSelectors(FT_Face face)
    private static native long[] FT_Face_GetVariantSelectors(long face);

    public static long[] ftFaceGetVariantSelectors(FTFace face) {
        final long[] array = FT_Face_GetVariantSelectors(FTStruct.getPointer(face));
        if(array == null)
            return new long[0];
        return array;
    }

    // FT_UInt32* FT_Face_GetVariantsOfChar(FT_Face face, FT_ULong charcode)
    private static native long[] FT_Face_GetVariantsOfChar(long face, long charcode);

    public static long[] ftFaceGetVariantsOfChar(FTFace face, long charcode) {
        final long[] array = FT_Face_GetVariantsOfChar(FTStruct.getPointer(face), charcode);
        if(array == null)
            return new long[0];
        return array;
    }

    // FT_UInt32* FT_Face_GetCharsOfVariant(FT_Face face, FT_ULong variantSelector)
    private static native long[] FT_Face_GetCharsOfVariant(long face, long variantSelector);

    public static long[] ftFaceGetCharsOfVariant(FTFace face, long variantSelector) {
        final long[] array = FT_Face_GetCharsOfVariant(FTStruct.getPointer(face), variantSelector);
        if(array == null)
            return new long[0];
        return array;
    }

    // FT_Long FT_MulDiv(FT_Long a, FT_Long b, FT_Long c)
    public static long ftMulDiv(long a, long b, long c) {
        return (a * b) / c;
    }

    // FT_Long FT_MulFix(FT_Long a, FT_Long b)
    public static long ftMulFix(long a, long b) {
        return (a * b) / 0x10000;
    }

    // FT_Long FT_DivFix(FT_Long a, FT_Long b)
    public static long ftDivFix(long a, long b) {
        return (a * 0x10000) / b;
    }

    // FT_Fixed FT_RoundFix(FT_Fixed a)
    private static native int FT_RoundFix(long a);

    public static float ftRoundFix(FTFixed a) {
        final int raw = FT_RoundFix(FTStruct.getPointer(a));
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed FT_CeilFix(FT_Fixed a)
    private static native int FT_CeilFix(long a);

    public static float ftCeilFix(FTFixed a) {
        final int raw = FT_CeilFix(FTStruct.getPointer(a));
        return FTFixed.toFloat(raw);
    }

    // FT_Fixed FT_FloorFix(FT_Fixed a)
    private static native int FT_FloorFix(long a);

    public static float ftFloorFix(FTFixed a) {
        final int raw = FT_FloorFix(FTStruct.getPointer(a));
        return FTFixed.toFloat(raw);
    }

    // void FT_Vector_Transform(FT_Vector* vector, const FT_Matrix* matrix)
    private static native void FT_Vector_Transform(long vector, long matrix);

    public static void ftVectorTransform(FTVector vector, FTMatrix matrix) {
        FT_Vector_Transform(FTStruct.getPointer(vector), FTStruct.getPointer(matrix));
    }

    // void FT_Library_Version(FT_Library library, FT_Int *amajor, FT_Int *aminor, FT_Int *apatch)
    private static native void FT_Library_Version(long library, int[] amajor, int[] aminor, int[] apatch);

    public static void ftLibraryVersion(FTLibrary library, int[] dstMajor, int[] dstMinor, int[] dstPatch) {
        FT_Library_Version(FTStruct.getPointer(library), dstMajor, dstMinor, dstPatch);
    }

    // FT_Bool FT_Face_CheckTrueTypePatents(FT_Face face)
    private static native boolean FT_Face_CheckTrueTypePatents(long face);

    public static boolean ftFaceCheckTrueTypePatents(FTFace face) {
        return FT_Face_CheckTrueTypePatents(FTStruct.getPointer(face));
    }

    // FT_Bool FT_Face_SetUnpatentedHinting(FT_Face face, FT_Bool value)
    private static native boolean FT_Face_SetUnpatentedHinting(long face, boolean value);

    public static boolean ftFaceSetUnpatentedHinting(FTFace face, boolean value) {
        return FT_Face_SetUnpatentedHinting(FTStruct.getPointer(face), value);
    }


    // ------------------------------
    // ---      ftgloadr.h        ---
    // ------------------------------


    // FT_Error FT_GlyphLoader_New(FT_Memory memory, FT_GlyphLoader *aloader)
    private static native int FT_GlyphLoader_New(long memory, long[] aloader);

    public static FTError ftGlyphLoaderNew(FTMemory memory, long[] dstLoaderPointer) {
        final int code = FT_GlyphLoader_New(FTStruct.getPointer(memory), dstLoaderPointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_GlyphLoader_CreateExtra(FT_GlyphLoader loader)
    private static native int FT_GlyphLoader_CreateExtra(long loader);

    public static FTError ftGlyphLoaderCreateExtra(FTGlyphLoader loader) {
        final int code = FT_GlyphLoader_CreateExtra(FTStruct.getPointer(loader));
        return FTError.byCode(code);
    }

    // void FT_GlyphLoader_Done(FT_GlyphLoader loader)
    private static native void FT_GlyphLoader_Done(long loader);

    public static void ftGlyphLoaderDone(FTGlyphLoader loader) {
        if(loader == null)
            return;

        FT_GlyphLoader_Done(FTStruct.getPointer(loader));
        loader.destroyPointer();
    }

    // void FT_GlyphLoader_Reset(FT_GlyphLoader loader)
    private static native void FT_GlyphLoader_Reset(long loader);

    public static void ftGlyphLoaderReset(FTGlyphLoader loader) {
        FT_GlyphLoader_Reset(FTStruct.getPointer(loader));
    }

    // void FT_GlyphLoader_Rewind(FT_GlyphLoader loader)
    private static native void FT_GlyphLoader_Rewind(long loader);

    public static void ftGlyphLoaderRewind(FTGlyphLoader loader) {
        FT_GlyphLoader_Rewind(FTStruct.getPointer(loader));
    }

    // FT_Error FT_GlyphLoader_CheckPoints(FT_GlyphLoader loader, FT_UInt n_points, FT_UInt n_contours)
    private static native int FT_GlyphLoader_CheckPoints(long loader, long n_points, long n_contours);

    public static FTError ftGlyphLoaderCheckPoints(FTGlyphLoader loader, long nPoints, long nContours) {
        final int code = FT_GlyphLoader_CheckPoints(FTStruct.getPointer(loader), nPoints, nContours);
        return FTError.byCode(code);
    }

    // FT_Error FT_GlyphLoader_CheckSubGlyphs(FT_GlyphLoader loader, FT_UInt n_subs)
    private static native int FT_GlyphLoader_CheckSubGlyphs(long loader, long n_subs);

    public static FTError ftGlyphLoaderCheckSubGlyphs(FTGlyphLoader loader, long nSubs) {
        final int code = FT_GlyphLoader_CheckSubGlyphs(FTStruct.getPointer(loader), nSubs);
        return FTError.byCode(code);
    }

    // void FT_GlyphLoader_Prepare(FT_GlyphLoader loader)
    private static native void FT_GlyphLoader_Prepare(long loader);

    public static void ftGlyphLoaderPrepare(FTGlyphLoader loader) {
        FT_GlyphLoader_Prepare(FTStruct.getPointer(loader));
    }

    // void FT_GlyphLoader_Add(FT_GlyphLoader loader)
    private static native void FT_GlyphLoader_Add(long loader);

    public static void ftGlyphLoaderAdd(FTGlyphLoader loader) {
        FT_GlyphLoader_Add(FTStruct.getPointer(loader));
    }


    // ------------------------------
    // ---       ftglyph.h        ---
    // ------------------------------


    // FT_Error FT_New_Glyph(FT_Library library, FT_Glyph_Format format, FT_Glyph *aglyph)
    private static native int FT_New_Glyph(long library, int format, long[] aglyph);

    public static FTError ftNewGlyph(FTLibrary library, FTGlyphFormat format, long[] dstGlyphPointer) {
        if(format == null)
            format = FTGlyphFormat.NONE;
        final int code = FT_New_Glyph(FTStruct.getPointer(library), format.value, dstGlyphPointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_Get_Glyph(FT_GlyphSlot slot, FT_Glyph *aglyph)
    private static native int FT_Get_Glyph(long slot, long[] aglyph);

    public static FTError ftGetGlyph(FTGlyphSlot slot, long[] dstGlyphPointer) {
        final int code = FT_Get_Glyph(FTStruct.getPointer(slot), dstGlyphPointer);
        return FTError.byCode(code);
    }

    // FT_Error FT_Glyph_Copy(FT_Glyph source, FT_Glyph *target)
    private static native int FT_Glyph_Copy(long source, long target);

    public static FTError ftGlyphCopy(FTGlyph source, FTGlyph target) {
        final int code = FT_Glyph_Copy(FTStruct.getPointer(source), FTStruct.getPointer(target));
        return FTError.byCode(code);
    }

    // FT_Error FT_Glyph_Transform(FT_Glyph glyph, const FT_Matrix* matrix, const FT_Vector* delta)
    private static native int FT_Glyph_Transform(long glyph, long matrix, long delta);

    public static FTError ftGlyphTransform(FTGlyph glyph, FTMatrix matrix, FTVector delta) {
        final int code = FT_Glyph_Transform(FTStruct.getPointer(glyph), FTStruct.getPointer(matrix), FTStruct.getPointer(delta));
        return FTError.byCode(code);
    }

    // void FT_Glyph_Get_CBox(FT_Glyph glyph, FT_UInt bbox_mode, FT_BBox *acbox)
    private static native void FT_Glyph_Get_CBox(long glyph, long bboxMode, long acbox);

    public static void ftGlyphGetCBox(FTGlyph glyph, long bboxMode, FTBBox dstCbox) {
        FT_Glyph_Get_CBox(FTStruct.getPointer(glyph), bboxMode, FTStruct.getPointer(dstCbox));
    }

    // FT_Error FT_Glyph_To_Bitmap(FT_Glyph* the_glyph, FT_Render_Mode render_mode, const FT_Vector* origin, FT_Bool destroy)
    private static native int FT_Glyph_To_Bitmap(long the_glyph, int render_mode, long origin, boolean destroy, long[] dstBitmapGlyphPointer);

    public static FTError ftGlyphToBitmap(FTGlyph glyph, FTRenderMode renderMode, FTVector origin, boolean destroy, long[] dstBitmapGlyphPointer) {
        if(renderMode == null)
            renderMode = FTRenderMode.NORMAL;

        final int code = FT_Glyph_To_Bitmap(FTStruct.getPointer(glyph), renderMode.value, FTStruct.getPointer(origin), destroy, dstBitmapGlyphPointer);
        if(destroy && glyph != null)
            glyph.destroyPointer();

        return FTError.byCode(code);
    }

    // void FT_Done_Glyph(FT_Glyph glyph)
    private static native void FT_Done_Glyph(long glyph);

    public static void ftDoneGlyph(FTGlyph glyph) {
        FT_Done_Glyph(FTStruct.getPointer(glyph));
        glyph.destroyPointer();
    }

    // void FT_Matrix_Multiply(const FT_Matrix* a, FT_Matrix* b)
    private static native void FT_Matrix_Multiply(long a, long b);

    public static void ftMatrixMultiply(FTMatrix a, FTMatrix b) {
        FT_Matrix_Multiply(FTStruct.getPointer(a), FTStruct.getPointer(b));
    }

    // FT_Error FT_Matrix_Invert(FT_Matrix* matrix)
    private static native int FT_Matrix_Invert(long matrix);

    public static FTError ftMatrixInvert(FTMatrix matrix) {
        final int code = FT_Matrix_Invert(FTStruct.getPointer(matrix));
        return FTError.byCode(code);
    }


    // ------------------------------
    // ---      ftstroke.h        ---
    // ------------------------------


    // FT_StrokerBorder FT_Outline_GetInsideBorder(FT_Outline* outline)
    private static native int FT_Outline_GetInsideBorder(long outline);

    public static FTStrokerBorder ftOutlineGetInsideBorder(FTOutline outline) {
        final int raw = FT_Outline_GetInsideBorder(FTStruct.getPointer(outline));
        return FTStrokerBorder.byValue(raw);
    }

    // FT_StrokerBorder FT_Outline_GetOutsideBorder(FT_Outline* outline)
    private static native int FT_Outline_GetOutsideBorder(long outline);

    public static FTStrokerBorder ftOutlineGetOutsideBorder(FTOutline outline) {
        final int raw = FT_Outline_GetOutsideBorder(FTStruct.getPointer(outline));
        return FTStrokerBorder.byValue(raw);
    }

    // FT_Error FT_Stroker_New(FT_Library library, FT_Stroker *astroker)
    private static native int FT_Stroker_New(long library, long[] astroker);

    public static FTError ftStrokerNew(FTLibrary library, long[] dstStrokerPointer) {
        final int code = FT_Stroker_New(FTStruct.getPointer(library), dstStrokerPointer);
        return FTError.byCode(code);
    }

    // void FT_Stroker_Set(FT_Stroker stroker, FT_Fixed radius, FT_Stroker_LineCap line_cap, FT_Stroker_LineJoin line_join, FT_Fixed miter_limit)
    private static native void FT_Stroker_Set(long stroker, long radius, int line_cap, int line_join, long miter_limit);

    public static void ftStrokerSet(FTStroker stroker, float radius, FTStrokerLinecap lineCap, FTStrokerLinejoin lineJoin, float miterLimit) {
        if(lineCap == null)
            throw new NullPointerException("LineCap is null");
        if(lineJoin == null)
            throw new NullPointerException("LineJoin is null");
        FT_Stroker_Set(FTStruct.getPointer(stroker), FTFixed.of(radius), lineCap.value, lineJoin.value, FTFixed.of(miterLimit));
    }

    // void FT_Stroker_Rewind(FT_Stroker stroker)
    private static native void FT_Stroker_Rewind(long stroker);

    public static void ftStrokerRewind(FTStroker stroker) {
        FT_Stroker_Rewind(FTStruct.getPointer(stroker));
    }

    // FT_Error FT_Stroker_ParseOutline(FT_Stroker stroker, FT_Outline* outline, FT_Bool opened)
    private static native int FT_Stroker_ParseOutline(long stroker, long outline, boolean opened);

    public static FTError ftStrokerParseOutline(FTStroker stroker, FTOutline outline, boolean opened) {
        final int code = FT_Stroker_ParseOutline(FTStruct.getPointer(stroker), FTStruct.getPointer(outline), opened);
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_BeginSubPath(FT_Stroker stroker, FT_Vector* to, FT_Bool open)
    private static native int FT_Stroker_BeginSubPath(long stroker, long to, boolean open);

    public static FTError ftStrokerBeginSubPath(FTStroker stroker, FTVector to, boolean open) {
        final int code = FT_Stroker_BeginSubPath(FTStruct.getPointer(stroker), FTStruct.getPointer(to), open);
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_EndSubPath(FT_Stroker stroker)
    private static native int FT_Stroker_EndSubPath(long stroker);

    public static FTError ftStrokerEndSubPath(FTStroker stroker) {
        final int code = FT_Stroker_EndSubPath(FTStruct.getPointer(stroker));
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_LineTo(FT_Stroker stroker, FT_Vector* to)
    private static native int FT_Stroker_LineTo(long stroker, long to);

    public static FTError ftStrokerLineTo(FTStroker stroker, FTVector to) {
        final int code = FT_Stroker_LineTo(FTStruct.getPointer(stroker), FTStruct.getPointer(to));
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_ConicTo(FT_Stroker stroker, FT_Vector* control, FT_Vector* to)
    private static native int FT_Stroker_ConicTo(long stroker, long control, long to);

    public static FTError ftStrokerConicTo(FTStroker stroker, FTVector control, FTVector to) {
        final int code = FT_Stroker_ConicTo(FTStruct.getPointer(stroker), FTStruct.getPointer(control), FTStruct.getPointer(to));
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_CubicTo(FT_Stroker stroker, FT_Vector* control1, FT_Vector* control2, FT_Vector* to)
    private static native int FT_Stroker_CubicTo(long stroker, long control1, long control2, long to);

    public static FTError ftStrokerCubicTo(FTStroker stroker, FTVector control1, FTVector control2, FTVector to) {
        final int code = FT_Stroker_CubicTo(FTStruct.getPointer(stroker), FTStruct.getPointer(control1), FTStruct.getPointer(control2), FTStruct.getPointer(to));
        return FTError.byCode(code);
    }

    // FT_Error FT_Stroker_GetBorderCounts(FT_Stroker stroker, FT_StrokerBorder border, FT_UInt *anum_points, FT_UInt *anum_contours)
    private static native int FT_Stroker_GetBorderCounts(long stroker, int border, long[] anum_points, long[] anum_contours);

    public static FTError ftStrokerGetBorderCounts(FTStroker stroker, FTStrokerBorder border, long[] dstNumPoint, long[] dstNumContours) {
        if(border == null)
            throw new NullPointerException("Border is null");
        final int code = FT_Stroker_GetBorderCounts(FTStruct.getPointer(stroker), border.value, dstNumPoint, dstNumContours);
        return FTError.byCode(code);
    }

    // void FT_Stroker_ExportBorder(FT_Stroker stroker, FT_StrokerBorder border, FT_Outline* outline)
    private static native void FT_Stroker_ExportBorder(long stroker, long border, long outline);

    public static void ftStrokerExportBorder(FTStroker stroker, FTStrokerBorder border, FTOutline outline) {
        if(border == null)
            throw new NullPointerException("Border is null");
        FT_Stroker_ExportBorder(FTStruct.getPointer(stroker), border.value, FTStruct.getPointer(outline));
    }

    // FT_Error FT_Stroker_GetCounts(FT_Stroker stroker, FT_UInt *anum_points, FT_UInt *anum_contours)
    private static native int FT_Stroker_GetCounts(long stroker, long[] anum_points, long[] anum_contours);

    public static FTError ftStrokerGetCounts(FTStroker stroker, long[] dstNumPoints, long[] dstNumContours) {
        final int code = FT_Stroker_GetCounts(FTStruct.getPointer(stroker), dstNumPoints, dstNumContours);
        return FTError.byCode(code);
    }

    // void FT_Stroker_Export(FT_Stroker stroker, FT_Outline* outline)
    private static native void FT_Stroker_Export(long stroker, long outline);

    public static void ftStrokerExport(FTStroker stroker, FTOutline outline) {
        FT_Stroker_Export(FTStruct.getPointer(stroker), FTStruct.getPointer(outline));
    }

    // void FT_Stroker_Done(FT_Stroker stroker)
    private static native void FT_Stroker_Done(long stroker);

    public static void ftStrokerDone(FTStroker stroker) {
        if(stroker == null)
            return;
        FT_Stroker_Done(FTStruct.getPointer(stroker));
        stroker.destroyPointer();
    }

    // FT_Error FT_Glyph_Stroke(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool destroy)
    private static native int FT_Glyph_Stroke(long pglyph, long stroker, boolean destroy, long[] dstStrokeGlyphPointer);

    public static FTError ftGlyphStroke(FTGlyph pGlyph, FTStroker stroker, boolean destroy, long[] dstStrokeGlyphPointer) {
        final int code = FT_Glyph_Stroke(FTStruct.getPointer(pGlyph), FTStruct.getPointer(stroker), destroy, dstStrokeGlyphPointer);
        if(destroy && pGlyph != null) // check error code?
            pGlyph.destroyPointer();

        return FTError.byCode(code);
    }

    // FT_Error FT_Glyph_StrokeBorder(FT_Glyph *pglyph, FT_Stroker stroker, FT_Bool inside, FT_Bool destroy)
    private static native int FT_Glyph_StrokeBorder(long pglyph, long stroker, boolean inside, boolean destroy, long[] dstStrokeGlyphPointer);

    public static FTError ftGlyphStrokeBorder(FTGlyph pGlyph, FTStroker stroker, boolean inside, boolean destroy, long[] dstStrokeGlyphPointer) {
        final int code = FT_Glyph_StrokeBorder(FTStruct.getPointer(pGlyph), FTStruct.getPointer(stroker), inside, destroy, dstStrokeGlyphPointer);
        if(destroy && pGlyph != null)
            pGlyph.destroyPointer();

        return FTError.byCode(code);
    }

}