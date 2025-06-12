
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

    }

    private static void test_ftLoadGlyph() {

    }

    private static void test_ftLoadChar() {

    }

    private static void test_ftFaceProperties() {

    }

    private static void test_ftGetGlyphName() {

    }

    private static void test_ftGetFSTypeFlagsRaw() {

    }

    private static void test_ftInitFreeType() {

    }

    private static void test_ftDoneFreeType() {

    }

    private static void test_ftNewFace() {

    }

    private static void test_ftOpenFace() {

    }

    private static void test_ftAttachFile() {

    }

    private static void test_ftAttachStream() {

    }

    private static void test_ftReferenceFace() {

    }

    private static void test_ftDoneFace() {

    }

    private static void test_ftSelectSize() {

    }

    private static void test_ftRequestSize() {

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

    }

    private static void test_ftMulFix() {

    }

    private static void test_ftDivFix() {

    }

    private static void test_ftRoundFix() {

    }

    private static void test_ftCeilFix() {

    }

    private static void test_ftFloorFix() {

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

    }

    private static void test_ftGlyphStroke() {

    }

    private static void test_ftGlyphStrokeBorder() {

    }

}
