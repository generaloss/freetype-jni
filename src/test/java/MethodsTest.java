
import generaloss.freetype.FreeType;
import generaloss.freetype.freetype.*;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.types.FTFixed;
import jpize.util.res.Resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

public class MethodsTest {

    public static void main(String[] args) throws Exception {
        for(Method method: MethodsTest.class.getDeclaredMethods()) {
            if(Modifier.isPrivate(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
                System.out.println("Invoke " + method.getName());
                method.setAccessible(true);
                method.invoke(null);
            }
        }
    }


    private static void test_ftNewMemoryFace() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.done();
        lib.done();
    }

    private static void test_ftLoadGlyph() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        long glyphIndex = face.getCharIndex('A');
        face.loadGlyph(glyphIndex);

        face.done();
        lib.done();
    }

    private static void test_ftLoadChar() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.loadChar('A');

        face.done();
        lib.done();
    }

    private static void test_ftFaceProperties() {
        FTLibrary lib = new FTLibrary();

        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        FTParameter property1 = new FTParameter()
                .setTag(FTParamTag.STEM_DARKENING)
                .setData(true);

        FTParameter property2 = new FTParameter()
                .setTag(FTParamTag.LCD_FILTER_WEIGHTS)
                .setData(new byte[] { 0x11, 0x44, 0x56, 0x44, 0x11 });

        FTParameter property3 = new FTParameter()
                .setTag(FTParamTag.RANDOM_SEED)
                .setData(314159265);

        face.properties(property1, property2, property3);

        face.done();
        lib.done();
    }

    private static void test_ftGetGlyphName() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        long glyphIndex = face.getCharIndex('A');
        ByteBuffer buffer = ByteBuffer.allocateDirect(2);
        face.getGlyphName(glyphIndex, buffer);
        assert (buffer.getChar() == '䄀');

        face.done();
        lib.done();
    }

    private static void test_ftInitFreeType() {
        FTLibrary lib = new FTLibrary();
        lib.done();
    }

    private static void test_ftDoneFreeType() {
        FTLibrary lib = new FTLibrary();
        lib.done();
    }

    private static void test_ftNewFace() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newFace("src/test/resources/droidsans.ttf", 0);
        face.done();
        lib.done();
    }

    private static void test_ftOpenFace() {
        FTLibrary lib = new FTLibrary();

        FTOpenArgs args = new FTOpenArgs();
        args.setFlags(new OpenFlags().set(FTOpen.PATHNAME));
        args.setPathname("src/test/resources/droidsans.ttf");

        FTFace face = lib.openFace(args, 0);
        face.done();
        lib.done();
    }

    private static void test_ftAttachFile() {

    }

    private static void test_ftAttachStream() {

    }

    private static void test_ftReferenceFace() {

    }

    private static void test_ftDoneFace() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.done();
        lib.done();
    }

    private static void test_ftSelectSize() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        // face.selectSize();

        face.done();
        lib.done();
    }

    private static void test_ftRequestSize() {
        FTLibrary lib = new FTLibrary();
        FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        FTSizeRequest request = new FTSizeRequest();
        request.setType(FTSizeRequestType.REAL_DIM);
        request.setWidth(0);
        request.setHeight(12 * 64); // 12pt in 1/64th points (internal FreeType format)
        request.setHoriResolution(72); // 72 DPI
        request.setVertResolution(72); // 72 DPI

        face.requestSize(request);

        assert (face.getHeight() == 2384);

        face.done();
        lib.done();
    }

    private static void test_ftSetCharSize() {

    }

    private static void test_ftSetPixelSizes() {

    }

    private static void test_ftSetTransform() {

    }

    private static void test_ftGetTransform() {

    }

    private static void test_ftRenderGlyph() {

    }

    private static void test_ftGetKerning() {

    }

    private static void test_ftGetTrackKerning() {

    }

    private static void test_ftSelectCharmap() {

    }

    private static void test_ftSetCharmap() {

    }

    private static void test_ftGetCharmapIndex() {

    }

    private static void test_ftGetCharIndex() {

    }

    private static void test_ftGetFirstChar() {

    }

    private static void test_ftGetNextChar() {

    }

    private static void test_ftGetNameIndex() {

    }

    private static void test_ftGetPostscriptName() {

    }

    private static void test_ftGetSubGlyphInfo() {

    }

    private static void test_ftGetFSTypeFlags() {

    }

    private static void test_ftFaceGetCharVariantIndex() {

    }

    private static void test_ftFaceGetCharVariantIsDefault() {

    }

    private static void test_ftFaceGetVariantSelectors() {

    }

    private static void test_ftFaceGetVariantsOfChar() {

    }

    private static void test_ftFaceGetCharsOfVariant() {

    }

    private static void test_ftMulDiv() {
        assert (FreeType.ftMulDiv(4, 5, 10) == 2);
    }

    private static void test_ftMulFix() {
        assert (FreeType.ftMulFix(1024, 512) == 8);
    }

    private static void test_ftDivFix() {
        assert (FreeType.ftDivFix(8, 512) == 1024);
    }

    private static void test_ftRoundFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftRoundFix(num) == 2F);
        num.free();
    }

    private static void test_ftCeilFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftCeilFix(num) == 2F);
        num.free();
    }

    private static void test_ftFloorFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftFloorFix(num) == 1F);
        num.free();
    }

    private static void test_ftVectorTransform() {

    }

    private static void test_ftLibraryVersion() {

    }

    private static void test_ftFaceCheckTrueTypePatents() {

    }

    private static void test_ftFaceSetUnpatentedHinting() {

    }

    private static void test_ftGlyphLoaderNew() {

    }

    private static void test_ftGlyphLoaderCreateExtra() {

    }

    private static void test_ftGlyphLoaderDone() {

    }

    private static void test_ftGlyphLoaderReset() {

    }

    private static void test_ftGlyphLoaderRewind() {

    }

    private static void test_ftGlyphLoaderCheckPoints() {

    }

    private static void test_ftGlyphLoaderCheckSubGlyphs() {

    }

    private static void test_ftGlyphLoaderPrepare() {

    }

    private static void test_ftGlyphLoaderAdd() {

    }

    private static void test_ftNewGlyph() {

    }

    private static void test_ftGetGlyph() {

    }

    private static void test_ftGlyphCopy() {

    }

    private static void test_ftGlyphTransform() {

    }

    private static void test_ftGlyphGetCBox() {

    }

    private static void test_ftGlyphToBitmap() {

    }

    private static void test_ftDoneGlyph() {

    }

    private static void test_ftMatrixMultiply() {

    }

    private static void test_ftMatrixInvert() {

    }

    private static void test_ftOutlineGetInsideBorder() {

    }

    private static void test_ftOutlineGetOutsideBorder() {

    }

    private static void test_ftStrokerNew() {
        FTLibrary lib = new FTLibrary();
        FTStroker stroker = lib.newStroker();
        stroker.done();
        lib.done();
    }

    private static void test_ftStrokerSet() {

    }

    private static void test_ftStrokerRewind() {

    }

    private static void test_ftStrokerParseOutline() {

    }

    private static void test_ftStrokerBeginSubPath() {

    }

    private static void test_ftStrokerEndSubPath() {

    }

    private static void test_ftStrokerLineTo() {

    }

    private static void test_ftStrokerConicTo() {

    }

    private static void test_ftStrokerCubicTo() {

    }

    private static void test_ftStrokerGetBorderCounts() {

    }

    private static void test_ftStrokerExportBorder() {

    }

    private static void test_ftStrokerGetCounts() {

    }

    private static void test_ftStrokerExport() {

    }

    private static void test_ftStrokerDone() {
        FTLibrary lib = new FTLibrary();
        FTStroker stroker = lib.newStroker();
        stroker.done();
        lib.done();
    }

    private static void test_ftGlyphStroke() {

    }

    private static void test_ftGlyphStrokeBorder() {

    }

}
