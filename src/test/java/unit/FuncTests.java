package unit;

import generaloss.freetype.FreeType;
import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.types.*;
import jpize.util.res.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        final FTFace face = lib.newMemoryFace(Resource.internal("/bchb8i.pfb").readByteBuffer(), 0);

        face.attachFile("src/test/resources/bchb8i.afm");

        face.done();
        lib.done();
    }

    @Test
    public void ftAttachStream() {
        // (postscript type 1 font)
        final ByteArrayInputStream bais = new ByteArrayInputStream(Resource.internal("/bchb8i.afm").readBytes());
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
        final FTFace face = lib.newMemoryFace(Resource.internal("/bchb8i.pfb").readByteBuffer(), 0);
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
    public void ftGetTrackKerning() { // old enough postscript type1 font was not found => 0F track kerning
        final FTLibrary library = new FTLibrary();

        final FTFace face = library.newMemoryFace(Resource.internal("/bchb8i.pfb").readByteBuffer(), 0);

        final FTStream stream = new FTStream();
        stream.set(Resource.internal("/bchb8i.afm").readBytes());

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.STREAM);
        args.setStream(stream);

        face.attachStream(args);

        stream.free();
        args.free();

        face.setPixelSizes(0, 16);

        Assert.assertEquals(0F, face.getTrackKerning(32, 0), 0F);

        face.done();
        library.done();
    }

    @Test
    public void ftSelectCharmap() {
        final FTLibrary library = new FTLibrary();
        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.selectCharmap(FTEncoding.APPLE_ROMAN);
        Assert.assertEquals(FTEncoding.APPLE_ROMAN, face.getCharmap().getEncoding());

        face.selectCharmap(FTEncoding.UNICODE);
        Assert.assertEquals(FTEncoding.UNICODE, face.getCharmap().getEncoding());

        face.done();
        library.done();
    }

    @Test
    public void ftSetCharmap() {
        final FTLibrary library = new FTLibrary();
        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final List<FTCharMap> list = new ArrayList<>(Arrays.asList(face.getCharmaps()));
        list.remove(face.getCharmap());

        final FTCharMap target = list.get(0);
        face.setCharmap(target);
        Assert.assertEquals(target, face.getCharmap());

        face.done();
        library.done();
    }

    @Test
    public void ftGetCharmapIndex() {
        final FTLibrary library = new FTLibrary();
        final FTFace face = library.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTCharMap[] charmaps = face.getCharmaps();

        final int index = (charmaps.length - 1); // last
        final FTCharMap charmap = face.getCharmaps()[index];
        Assert.assertEquals(index, charmap.getIndex());

        face.done();
        library.done();
    }

    @Test
    public void ftGetCharIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long indexA = face.getCharIndex('A');
        final long indexZ = face.getCharIndex('Z');
        final long invalidChar = face.getCharIndex(0xFFFF);

        Assert.assertTrue(indexA > 0);
        Assert.assertTrue(indexZ > 0);
        Assert.assertEquals(0, invalidChar);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetFirstChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long[] dstGlyphIndex = new long[1];
        final long charcode = face.getFirstChar(dstGlyphIndex);

        Assert.assertTrue(charcode > 0);
        Assert.assertEquals(face.getCharIndex(charcode), dstGlyphIndex[0]);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetNextChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long first = face.getFirstChar(null);
        final long next = face.getNextChar(first, null);

        Assert.assertTrue(next > first);
        Assert.assertTrue(next > 0);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetNameIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long index = face.getNameIndex("copyright");
        Assert.assertTrue(index >= 0);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetPostscriptName() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final String postscriptName = face.getPostscriptName();
        Assert.assertEquals("DroidSans", postscriptName);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetSubGlyphInfo() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.loadChar('Ǽ', FTLoad.NO_RECURSE);
        final FTGlyphSlot glyph = face.getGlyph();

        final FTSubGlyph[] subglyphs = glyph.getSubglyphs();
        final int index = (subglyphs.length - 1); // last
        final FTSubGlyph target = subglyphs[index];

        final int[] dstPIndex = new int[1];
        final long[] dstPFlags = new long[1];
        final int[] dstPArg1 = new int[1];
        final int[] dstPArg2 = new int[1];
        final FTMatrix dstPTransform = new FTMatrix();

        glyph.getSubGlyphInfo(index, dstPIndex, dstPFlags, dstPArg1, dstPArg2, dstPTransform);

        Assert.assertEquals(target.getIndex(), dstPIndex[0]);
        Assert.assertEquals(target.getFlagsRaw(), dstPFlags[0]);
        Assert.assertEquals(target.getArg1(), dstPArg1[0]);
        Assert.assertEquals(target.getArg2(), dstPArg2[0]);
        Assert.assertEquals(target.getTransform(), dstPTransform);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetFSTypeFlags() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FSTypeFlags flags = face.getFSTypeFlags();
        Assert.assertFalse(flags.isDefault());

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharVariantIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        // tag? final long variantIndex = face.getCharVariantIndex('A', "latn");
        // Assert.assertTrue(variantIndex >= 0);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharVariantIsDefault() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        // tag? final boolean isDefault = face.getCharVariantIsDefault('A', "latn");
        // Assert.assertTrue(isDefault);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetVariantSelectors() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/main.ttf").readByteBuffer(), 0);

        final long[] selectors = face.getVariantSelectors();
        for(long selector: selectors)
            System.out.print((char) selector + ", ");
        System.out.println();

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetVariantsOfChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/apple-color-emoji.ttf").readByteBuffer(), 0);

        final long[] variants = face.getVariantsOfChar('❤');
        for(long variant: variants)
            System.out.println("'" + (char) variant + "'");

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharsOfVariant() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/main.ttf").readByteBuffer(), 0);

        final long[] chars = face.getCharsOfVariant(face.getVariantSelectors()[0]);
        for(long c: chars)
            System.out.print((char) c + ", ");
        System.out.println();

        face.done();
        lib.done();
    }

    @Test
    public void ftMulDiv() {
        Assert.assertEquals(2, FreeType.ftMulDiv(4, 5, 10));
    }

    @Test
    public void ftMulFix() {
        Assert.assertEquals(8, FreeType.ftMulFix(1024, 512));
    }

    @Test
    public void ftDivFix() {
        Assert.assertEquals(1024, FreeType.ftDivFix(8, 512));
    }

    @Test
    public void ftRoundFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assert.assertEquals(2F, FreeType.ftRoundFix(num), 0F);
        num.free();
    }

    @Test
    public void ftCeilFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assert.assertEquals(2F, FreeType.ftCeilFix(num), 0F);
        num.free();
    }

    @Test
    public void ftFloorFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assert.assertEquals(1F, FreeType.ftFloorFix(num), 0F);
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
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
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
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
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
