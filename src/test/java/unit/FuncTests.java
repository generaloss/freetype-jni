package unit;

import generaloss.freetype.FreeType;
import generaloss.freetype.freetype.*;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.types.*;
import jpize.util.res.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class FuncTests {

    @Test
    public void ftNewMemoryFace() {
        final FTLibrary lib = new FTLibrary();

        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.done();

        lib.done();
    }

    @Test
    public void ftLoadGlyph() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long glyphIndex = face.getCharIndex('A');
        face.loadGlyph(glyphIndex);

        face.done();
        lib.done();
    }

    @Test
    public void ftLoadChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.loadChar('A');

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceProperties() {
        final FTLibrary lib = new FTLibrary();

        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTParameter property1 = new FTParameter()
            .setTag(FTParamTag.STEM_DARKENING)
            .setData(true);

        final FTParameter property2 = new FTParameter()
            .setTag(FTParamTag.LCD_FILTER_WEIGHTS)
            .setData(new byte[] { 0x11, 0x44, 0x56, 0x44, 0x11 });

        final FTParameter property3 = new FTParameter()
            .setTag(FTParamTag.RANDOM_SEED)
            .setData(314159265);

        face.properties(property1, property2, property3);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetGlyphName() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long glyphIndex = face.getCharIndex('A');
        final ByteBuffer buffer = ByteBuffer.allocateDirect(2);
        face.getGlyphName(glyphIndex, buffer);
        Assert.assertEquals('䄀', buffer.getChar());

        face.done();
        lib.done();
    }

    @Test
    public void ftInitFreeType() {
        final FTLibrary lib = new FTLibrary();
        lib.done();
    }

    @Test
    public void ftDoneFreeType() {
        final FTLibrary lib = new FTLibrary();
        lib.done();
    }

    @Test
    public void ftNewFace() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newFace("src/test/resources/droidsans.ttf", 0);
        face.done();
        lib.done();
    }

    @Test
    public void ftOpenFace_FILE() {
        final FTLibrary lib = new FTLibrary();

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.PATHNAME);
        args.setPathname("src/test/resources/droidsans.ttf");

        final FTFace face = lib.openFace(args, 0);
        face.done();
        lib.done();
    }

    @Test
    public void ftOpenFace_MEMORY() {
        final FTLibrary lib = new FTLibrary();

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.MEMORY);
        args.setMemoryBase(Resource.internal("/droidsans.ttf").readByteBuffer());

        final FTFace face = lib.openFace(args, 0);
        face.done();
        lib.done();
    }

    @Test
    public void ftOpenFace_STREAM() {
        final FTLibrary lib = new FTLibrary();

        final ByteArrayInputStream bais = new ByteArrayInputStream(Resource.internal("/main.ttf").readBytes());
        final FTStream stream = new FTStream();
        stream.setSize(bais.available());
        stream.setRead((long offset, byte[] buffer, long count) -> {
            bais.reset();
            bais.skip(offset);
            return bais.read(buffer, 0, (int) count);
        });
        stream.setClose(bais::close);

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.STREAM);
        args.setStream(stream);

        final FTFace face = lib.openFace(args, 0);
        face.done();
        lib.done();
    }

    @Test
    public void ftAttachFile() {
        // (postscript type 1 font)
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/cennp.pfb").readByteBuffer(), 0);

        face.attachFile("src/test/resources/cennp.pfm");

        face.done();
        lib.done();
    }

    @Test
    public void ftAttachStream() {
        // (postscript type 1 font)
        final ByteArrayInputStream bais = new ByteArrayInputStream(Resource.internal("/cennp.pfm").readBytes());
        final FTStream stream = new FTStream();
        stream.setSize(bais.available());
        stream.setRead((long offset, byte[] buffer, long count) -> {
            bais.reset();
            bais.skip(offset);
            return bais.read(buffer, 0, (int) count);
        });
        stream.setClose(bais::close);

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.STREAM);
        args.setStream(stream);

        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/cennp.pfb").readByteBuffer(), 0);
        face.attachStream(args);

        stream.free();
        args.free();
        face.done();
        lib.done();
    }

    @Test
    public void ftReferenceFace() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0); // new => refcount=1

        face.referenceFace(); // refcount++ = 2
        face.referenceFace(); // refcount++ = 3
        face.done();          // refcount-- = 2
        face.referenceFace(); // refcount++ = 3
        face.done();          // refcount-- = 2
        face.done();          // refcount-- = 1

        face.done(); // refcount=0 => done
        lib.done();
    }

    @Test
    public void ftDoneFace() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.done();
        lib.done();
    }

    @Test
    public void ftSelectSize() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/ter-u32n.bdf").readByteBuffer(), 0);

        final int strikeCount = face.getNumFixedSizes();
        if(strikeCount == 0)
            throw new RuntimeException("Face has no available bitmap sizes");

        FreeType.ftSelectSize(face, 0);

        FreeType.ftLoadGlyph(face, face.getCharIndex('A'));

        // Load bitmap glyph using FT_Select_Size strike[0]
        final FTGlyphSlot glyph = face.getGlyph();
        if(glyph.getFormat() != FTGlyphFormat.BITMAP)
            throw new RuntimeException("Expected bitmap glyph, but got: " + glyph.getFormat());

        face.done();
        lib.done();
    }

    @Test
    public void ftRequestSize() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTSizeRequest request = new FTSizeRequest();
        request.setType(FTSizeRequestType.REAL_DIM);
        request.setWidth(0);
        request.setHeight(12 * 64); // 12pt in 1/64th points (internal FreeType format)
        request.setHoriResolution(72); // 72 DPI
        request.setVertResolution(72); // 72 DPI

        face.requestSize(request);

        Assert.assertEquals(2384, face.getHeight());

        face.done();
        lib.done();
    }

    @Test
    public void ftSetCharSize() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setCharSize(0F, 16F, 300, 300); // 16pt, 300dpi

        final FTSizeMetrics metrics = face.getSize().getMetrics();
        Assert.assertEquals(78F, metrics.getHeight(), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftSetPixelSizes() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0, 24);

        final FTSizeMetrics metrics = face.getSize().getMetrics();
        Assert.assertEquals(28F, metrics.getHeight(), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftSetTransform() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTMatrix matrix = new FTMatrix().setIdentity();
        final FTVector delta = new FTVector().set(10 * 64, 20 * 64);
        face.setTransform(matrix, delta);

        matrix.setXX(0F).setYY(0F);
        delta.set(0F);

        face.getTransform(matrix, delta);
        Assert.assertEquals(1F, matrix.getXX(), 0F);
        Assert.assertEquals(1F, matrix.getYY(), 0F);
        Assert.assertEquals(10 * 64, delta.getX(), 0F);
        Assert.assertEquals(20 * 64, delta.getY(), 0F);

        matrix.free();
        delta.free();

        face.done();
        lib.done();
    }

    @Test
    public void ftGetTransform() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTMatrix matrix = new FTMatrix().setIdentity();
        final FTVector delta = new FTVector().set(10 * 64, 20 * 64);
        face.setTransform(matrix, delta);

        matrix.setXX(0F).setYY(0F);
        delta.set(0F);

        face.getTransform(matrix, delta);
        Assert.assertEquals(1F, matrix.getXX(), 0F);
        Assert.assertEquals(1F, matrix.getYY(), 0F);
        Assert.assertEquals(10 * 64, delta.getX(), 0F);
        Assert.assertEquals(20 * 64, delta.getY(), 0F);

        matrix.free();
        delta.free();

        face.done();
        lib.done();
    }

    @Test
    public void ftRenderGlyph() {
        final FTLibrary library = new FTLibrary();

        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.setPixelSizes(32, 32);
        face.loadChar('A');

        final FTGlyphSlot slot = face.getGlyph();
        slot.renderGlyph(FTRenderMode.NORMAL);

        face.done();
        library.done();
    }

    @Test
    public void ftGetKerning() {
        final FTLibrary library = new FTLibrary();

        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.setPixelSizes(0, 64);

        final long left = face.getCharIndex('A');
        final long right = face.getCharIndex('V');

        final FTVector dstKerning = new FTVector();
        face.getKerning(left, right, FTKerningMode.DEFAULT, dstKerning);

        Assert.assertEquals(-2F, dstKerning.getX(), 0F);

        face.done();
        library.done();
    }

    @Test
    public void ftGetTrackKerning() {

    }

    @Test
    public void ftSelectCharmap() {

    }

    @Test
    public void ftSetCharmap() {

    }

    @Test
    public void ftGetCharmapIndex() {

    }

    @Test
    public void ftGetCharIndex() {

    }

    @Test
    public void ftGetFirstChar() {

    }

    @Test
    public void ftGetNextChar() {

    }

    @Test
    public void ftGetNameIndex() {

    }

    @Test
    public void ftGetPostscriptName() {

    }

    @Test
    public void ftGetSubGlyphInfo() {

    }

    @Test
    public void ftGetFSTypeFlags() {

    }

    @Test
    public void ftFaceGetCharVariantIndex() {

    }

    @Test
    public void ftFaceGetCharVariantIsDefault() {

    }

    @Test
    public void ftFaceGetVariantSelectors() {

    }

    @Test
    public void ftFaceGetVariantsOfChar() {

    }

    @Test
    public void ftFaceGetCharsOfVariant() {

    }

    @Test
    public void ftMulDiv() {
        assert (FreeType.ftMulDiv(4, 5, 10) == 2);
    }

    @Test
    public void ftMulFix() {
        assert (FreeType.ftMulFix(1024, 512) == 8);
    }

    @Test
    public void ftDivFix() {
        assert (FreeType.ftDivFix(8, 512) == 1024);
    }

    @Test
    public void ftRoundFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftRoundFix(num) == 2F);
        num.free();
    }

    @Test
    public void ftCeilFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftCeilFix(num) == 2F);
        num.free();
    }

    @Test
    public void ftFloorFix() {
        FTFixed num = new FTFixed().set(1.5F);
        assert (FreeType.ftFloorFix(num) == 1F);
        num.free();
    }

    @Test
    public void ftVectorTransform() {

    }

    @Test
    public void ftLibraryVersion() {

    }

    @Test
    public void ftFaceCheckTrueTypePatents() {

    }

    @Test
    public void ftFaceSetUnpatentedHinting() {

    }

    @Test
    public void ftGlyphLoaderNew() {

    }

    @Test
    public void ftGlyphLoaderCreateExtra() {

    }

    @Test
    public void ftGlyphLoaderDone() {

    }

    @Test
    public void ftGlyphLoaderReset() {

    }

    @Test
    public void ftGlyphLoaderRewind() {

    }

    @Test
    public void ftGlyphLoaderCheckPoints() {

    }

    @Test
    public void ftGlyphLoaderCheckSubGlyphs() {

    }

    @Test
    public void ftGlyphLoaderPrepare() {

    }

    @Test
    public void ftGlyphLoaderAdd() {

    }

    @Test
    public void ftNewGlyph() {

    }

    @Test
    public void ftGetGlyph() {

    }

    @Test
    public void ftGlyphCopy() {

    }

    @Test
    public void ftGlyphTransform() {

    }

    @Test
    public void ftGlyphGetCBox() {

    }

    @Test
    public void ftGlyphToBitmap() {

    }

    @Test
    public void ftDoneGlyph() {

    }

    @Test
    public void ftMatrixMultiply() {

    }

    @Test
    public void ftMatrixInvert() {

    }

    @Test
    public void ftOutlineGetInsideBorder() {

    }

    @Test
    public void ftOutlineGetOutsideBorder() {

    }

    @Test
    public void ftStrokerNew() {
        FTLibrary lib = new FTLibrary();
        FTStroker stroker = lib.newStroker();
        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerSet() {

    }

    @Test
    public void ftStrokerRewind() {

    }

    @Test
    public void ftStrokerParseOutline() {

    }

    @Test
    public void ftStrokerBeginSubPath() {

    }

    @Test
    public void ftStrokerEndSubPath() {

    }

    @Test
    public void ftStrokerLineTo() {

    }

    @Test
    public void ftStrokerConicTo() {

    }

    @Test
    public void ftStrokerCubicTo() {

    }

    @Test
    public void ftStrokerGetBorderCounts() {

    }

    @Test
    public void ftStrokerExportBorder() {

    }

    @Test
    public void ftStrokerGetCounts() {

    }

    @Test
    public void ftStrokerExport() {

    }

    @Test
    public void ftStrokerDone() {
        FTLibrary lib = new FTLibrary();
        FTStroker stroker = lib.newStroker();
        stroker.done();
        lib.done();
    }

    @Test
    public void ftGlyphStroke() {

    }

    @Test
    public void ftGlyphStrokeBorder() {

    }

}
