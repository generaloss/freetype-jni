package unit;

import generaloss.freetype.FreeType;
import generaloss.freetype.UnicodeVariationSelector;
import generaloss.freetype.freetype.*;
import generaloss.freetype.gload.FTGlyphLoader;
import generaloss.freetype.gload.FTSubGlyph;
import generaloss.freetype.glyph.FTBitmapGlyph;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.glyph.FTGlyphBBoxMode;
import generaloss.freetype.image.*;
import generaloss.freetype.outln.FTOrientation;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.stroke.FTStrokerBorder;
import generaloss.freetype.stroke.FTStrokerLineCap;
import generaloss.freetype.stroke.FTStrokerLineJoin;
import generaloss.freetype.system.FTMemory;
import generaloss.freetype.types.*;
import jpize.util.res.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0); // corrupted size vs. prev_size

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
        Assertions.assertEquals('䄀', buffer.getChar());

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
        stream.free();
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
        final FTStream stream = new FTStream();
        stream.set(Resource.internal("/bchb8i.afm").readBytes());

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
        request.setWidth(0L);
        request.setHeight(12L * 64L); // 12pt in 1/64th points (internal FreeType format)
        request.setHoriResolution(72L); // 72 DPI
        request.setVertResolution(72L); // 72 DPI

        face.requestSize(request);

        Assertions.assertEquals(2384, face.getHeight());

        face.done();
        lib.done();
    }

    @Test
    public void ftSetCharSize() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setCharSize(0F, 16F, 300, 300); // 16pt, 300dpi

        final FTSizeMetrics metrics = face.getSize().getMetrics();
        Assertions.assertEquals(78F, metrics.getHeight(), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftSetPixelSizes() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 24L);

        final FTSizeMetrics metrics = face.getSize().getMetrics();
        Assertions.assertEquals(28F, metrics.getHeight(), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftSetTransform() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTMatrix matrix = new FTMatrix().setIdentity();
        final FTVector delta = new FTVector().set(10F, 20F, PosType.F26DOT6);
        face.setTransform(matrix, delta);

        matrix.setXX(0F).setYY(0F);
        delta.set(0F, PosType.F26DOT6);

        face.getTransform(matrix, delta);
        Assertions.assertEquals(1F, matrix.getXX(), 0F);
        Assertions.assertEquals(1F, matrix.getYY(), 0F);
        Assertions.assertEquals(10F, delta.getX(PosType.F26DOT6), 0F);
        Assertions.assertEquals(20F, delta.getY(PosType.F26DOT6), 0F);

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
        final FTVector delta = new FTVector().set(10F, 20F, PosType.F26DOT6);
        face.setTransform(matrix, delta);

        matrix.setXX(0F).setYY(0F);
        delta.set(0F, PosType.F26DOT6);

        face.getTransform(matrix, delta);
        Assertions.assertEquals(1F, matrix.getXX(), 0F);
        Assertions.assertEquals(1F, matrix.getYY(), 0F);
        Assertions.assertEquals(10F, delta.getX(PosType.F26DOT6), 0F);
        Assertions.assertEquals(20F, delta.getY(PosType.F26DOT6), 0F);

        matrix.free();
        delta.free();

        face.done();
        lib.done();
    }

    @Test
    public void ftRenderGlyph() {
        final FTLibrary lib = new FTLibrary();

        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.setPixelSizes(32L, 32L);
        face.loadChar('A');

        final FTGlyphSlot slot = face.getGlyph();
        slot.renderGlyph(FTRenderMode.NORMAL);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetKerning() {
        final FTLibrary lib = new FTLibrary();

        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.setPixelSizes(0L, 64L);

        final long left = face.getCharIndex('A');
        final long right = face.getCharIndex('V');

        final FTVector dstKerning = new FTVector();
        face.getKerning(left, right, FTKerningMode.DEFAULT, dstKerning);

        Assertions.assertEquals(-2F, dstKerning.getX(PosType.F26DOT6), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetTrackKerning() { // old enough postscript type1 font was not found => 0F track kerning
        final FTLibrary lib = new FTLibrary();

        final FTFace face = lib.newMemoryFace(Resource.internal("/bchb8i.pfb").readByteBuffer(), 0);

        final FTStream stream = new FTStream();
        stream.set(Resource.internal("/bchb8i.afm").readBytes());

        final FTOpenArgs args = new FTOpenArgs();
        args.setFlags(FTOpen.STREAM);
        args.setStream(stream);

        face.attachStream(args);

        stream.free();
        args.free();

        face.setPixelSizes(0L, 16L);

        Assertions.assertEquals(0F, face.getTrackKerning(32L, 0), 0F);

        face.done();
        lib.done();
    }

    @Test
    public void ftSelectCharmap() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.selectCharmap(FTEncoding.APPLE_ROMAN);
        Assertions.assertEquals(FTEncoding.APPLE_ROMAN, face.getCharmap().getEncoding());

        face.selectCharmap(FTEncoding.UNICODE);
        Assertions.assertEquals(FTEncoding.UNICODE, face.getCharmap().getEncoding());

        face.done();
        lib.done();
    }

    @Test
    public void ftSetCharmap() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final List<FTCharMap> list = new ArrayList<>(Arrays.asList(face.getCharmaps()));
        list.remove(face.getCharmap());

        final FTCharMap target = list.get(0);
        face.setCharmap(target);
        Assertions.assertEquals(target, face.getCharmap());

        face.done();
        lib.done();
    }

    @Test
    public void ftGetCharmapIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTCharMap[] charmaps = face.getCharmaps();

        final int index = (charmaps.length - 1); // last
        final FTCharMap charmap = face.getCharmaps()[index];
        Assertions.assertEquals(index, charmap.getIndex());

        face.done();
        lib.done();
    }

    @Test
    public void ftGetCharIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long indexA = face.getCharIndex('A');
        final long indexZ = face.getCharIndex('Z');
        final long invalidChar = face.getCharIndex(0xFFFF);

        Assertions.assertTrue(indexA > 0L);
        Assertions.assertTrue(indexZ > 0L);
        Assertions.assertEquals(0L, invalidChar);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetFirstChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long[] dstGlyphIndex = new long[1];
        final long charcode = face.getFirstChar(dstGlyphIndex);

        Assertions.assertTrue(charcode > 0L);
        Assertions.assertEquals(face.getCharIndex(charcode), dstGlyphIndex[0]);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetNextChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long first = face.getFirstChar(null);
        final long next = face.getNextChar(first, null);

        Assertions.assertTrue(next > first);
        Assertions.assertTrue(next > 0L);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetNameIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final long index = face.getNameIndex("copyright");
        Assertions.assertTrue(index >= 0L);

        face.done();
        lib.done();
    }

    @Test
    public void ftGetPostscriptName() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final String postscriptName = face.getPostscriptName();
        Assertions.assertEquals("DroidSans", postscriptName);

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

        Assertions.assertEquals(target.getIndex(), dstPIndex[0]);
        Assertions.assertEquals(target.getFlagsRaw(), dstPFlags[0]);
        Assertions.assertEquals(target.getArg1(), dstPArg1[0]);
        Assertions.assertEquals(target.getArg2(), dstPArg2[0]);
        Assertions.assertEquals(target.getTransform(), dstPTransform);

        dstPTransform.free();

        face.done();
        lib.done();
    }

    @Test
    public void ftGetFSTypeFlags() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FSTypeFlags flags = face.getFSTypeFlags();
        Assertions.assertFalse(flags.isDefault());

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharVariantIndex() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/apple-color-emoji.ttf").readByteBuffer(), 0);

        final long variantIndex = face.getCharVariantIndex('❤', UnicodeVariationSelector.VS16_EMOJI);
        Assertions.assertEquals(168L, variantIndex);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharVariantIsDefault() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/apple-color-emoji.ttf").readByteBuffer(), 0);

        final int isDefault = face.getCharVariantIsDefault('❤', UnicodeVariationSelector.VS16_EMOJI);
        Assertions.assertEquals(1, isDefault);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetVariantSelectors() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/apple-color-emoji.ttf").readByteBuffer(), 0);

        final long[] selectors = face.getVariantSelectors();
        Assertions.assertTrue(selectors.length > 0);
        Assertions.assertEquals(UnicodeVariationSelector.VS16_EMOJI.code, selectors[0]);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetVariantsOfChar() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/apple-color-emoji.ttf").readByteBuffer(), 0);

        final long[] variants = face.getVariantsOfChar('❤');
        Assertions.assertTrue(variants.length > 0);

        face.done();
        lib.done();
    }

    @Test
    public void ftFaceGetCharsOfVariant() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/main.ttf").readByteBuffer(), 0);

        final long[] chars = face.getCharsOfVariant(face.getVariantSelectors()[0]);
        Assertions.assertTrue(chars.length > 0);

        face.done();
        lib.done();
    }

    @Test
    public void ftMulDiv() {
        Assertions.assertEquals(2L, FreeType.ftMulDiv(4L, 5L, 10L));
    }

    @Test
    public void ftMulFix() {
        Assertions.assertEquals(8L, FreeType.ftMulFix(1024L, 512L));
    }

    @Test
    public void ftDivFix() {
        Assertions.assertEquals(1024L, FreeType.ftDivFix(8L, 512L));
    }

    @Test
    public void ftRoundFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assertions.assertEquals(2F, FreeType.ftRoundFix(num), 0F);
        num.free();
    }

    @Test
    public void ftCeilFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assertions.assertEquals(2F, FreeType.ftCeilFix(num), 0F);
        num.free();
    }

    @Test
    public void ftFloorFix() {
        final FTFixed num = new FTFixed().set(1.5F);
        Assertions.assertEquals(1F, FreeType.ftFloorFix(num), 0F);
        num.free();
    }

    @Test
    public void ftVectorTransform() {
        final FTVector vector = new FTVector().set(10F, 20F, PosType.F16DOT16);
        final FTMatrix matrix = new FTMatrix().setScale(2F);

        vector.transform(matrix);

        Assertions.assertEquals(20F, vector.getX(PosType.F16DOT16), 0F);
        Assertions.assertEquals(40F, vector.getY(PosType.F16DOT16), 0F);

        vector.free();
        matrix.free();
    }

    @Test
    public void ftLibraryVersion() {
        final FTLibrary lib = new FTLibrary();

        final String version = lib.getVersion();
        Assertions.assertTrue(version.matches("\\d+\\.\\d+\\.\\d+"));

        lib.done();
    }

    @Test
    public void ftFaceCheckTrueTypePatents() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final boolean hasPatents = face.checkTrueTypePatents();
        Assertions.assertFalse(hasPatents);

        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderNew() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();

        final FTGlyphLoader loader = memory.newGlyphLoader();
        loader.done();

        lib.done();
    }

    @Test
    public void ftGlyphLoaderCreateExtra() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.createExtra();

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderDone() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();

        final FTGlyphLoader loader = memory.newGlyphLoader();
        loader.done();

        lib.done();
    }

    @Test
    public void ftGlyphLoaderReset() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.reset();

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderRewind() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.rewind();

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderCheckPoints() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.prepare(); // prepare current
        loader.checkPoints(10, 5);
        loader.getCurrent().getOutline().setNPoints(10);
        loader.getCurrent().getOutline().setNContours(5);
        loader.add(); // copy current -> base

        final FTOutline outline = loader.getBase().getOutline();

        Assertions.assertEquals(10, outline.getNPoints());
        Assertions.assertEquals(5, outline.getNContours());

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderCheckSubGlyphs() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.prepare(); // prepare current

        loader.checkSubGlyphs(3); // allocate 3 subglyphs
        loader.getCurrent().setNumSubglyphs(3);
        loader.getCurrent().getSubglyphs()[0].setIndex(228);

        loader.add(); // copy current -> base

        Assertions.assertEquals(3, loader.getBase().getNumSubglyphs());
        Assertions.assertEquals(228, loader.getBase().getSubglyphs()[0].getIndex());

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderPrepare() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.prepare(); // prepare current

        loader.done();
        lib.done();
    }

    @Test
    public void ftGlyphLoaderAdd() {
        final FTLibrary lib = new FTLibrary();
        final FTMemory memory = lib.getMemory();
        final FTGlyphLoader loader = memory.newGlyphLoader();

        loader.prepare(); // prepare current

        loader.checkPoints(1, 1); // allocate 1 point + 1 contour
        final FTOutline currentOutline = loader.getCurrent().getOutline();
        currentOutline.setNPoints(1);
        currentOutline.setNContours(1);
        currentOutline.getPoints()[0].set(100F, 200F, PosType.INT);

        loader.add(); // copy current -> base

        final FTOutline baseOutline = loader.getBase().getOutline();
        Assertions.assertEquals(1, baseOutline.getNPoints());
        Assertions.assertEquals(100F, baseOutline.getPoints()[0].getX(PosType.INT), 0F);
        Assertions.assertEquals(200F, baseOutline.getPoints()[0].getY(PosType.INT), 0F);

        loader.done();
        lib.done();
    }

    @Test
    public void ftNewGlyph() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyphSlot slot = face.getGlyph();

        final FTGlyph glyph = lib.newGlyph(FTGlyphFormat.OUTLINE);
        Assertions.assertEquals(FTGlyphFormat.OUTLINE, glyph.getFormat());
        glyph.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftGetGlyph() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyphSlot slot = face.getGlyph();

        final FTGlyph glyph = slot.getGlyph();
        Assertions.assertEquals(lib, glyph.getLibrary());
        glyph.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphCopy() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyph original = face.getGlyph().getGlyph();

        final FTGlyph copy = original.copy();
        Assertions.assertEquals(lib, copy.getLibrary());
        Assertions.assertTrue(original.getPointer() != copy.getPointer());
        copy.done();

        original.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphTransform() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTMatrix matrix = new FTMatrix().setRotation(45F);
        final FTVector delta = new FTVector().set(10F, 20F, PosType.F26DOT6);

        glyph.transform(matrix, delta);

        matrix.free();
        delta.free();
        glyph.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphGetCBox() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTBBox bbox = new FTBBox();
        final FTGlyphBBoxMode bboxMode = FTGlyphBBoxMode.TRUNCATE;
        glyph.getCBox(bboxMode, bbox);

        Assertions.assertTrue(bbox.getXMin(PosType.F26DOT6) < bbox.getXMax(bboxMode.posType));
        Assertions.assertTrue(bbox.getYMin(PosType.F26DOT6) < bbox.getYMax(bboxMode.posType));

        bbox.free();

        glyph.done();
        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphToBitmap() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, new FTVector(), false);
        final FTBitmap bitmap = bitmapGlyph.getBitmap();

        Assertions.assertTrue(bitmap.getWidth() > 0L);
        Assertions.assertTrue(bitmap.getRows() > 0L);

        glyph.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftDoneGlyph() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('A');
        final FTGlyph glyph = face.getGlyph().getGlyph();
        glyph.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftMatrixMultiply() {
        final FTMatrix a = new FTMatrix().setScale(2F);
        final FTMatrix b = new FTMatrix().setScale(2F);

        a.multiply(b);

        Assertions.assertEquals(4F, b.getXX(), 0F);
        Assertions.assertEquals(4F, b.getYY(), 0F);

        a.free();
        b.free();
    }

    @Test
    public void ftMatrixInvert() {
        final FTMatrix matrix = new FTMatrix().setScale(2F);

        matrix.invert();
        Assertions.assertEquals(0.5F, matrix.getXX(), 0F);

        matrix.free();
    }

    @Test
    public void ftOutlineDecompose() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.lineTo(new FTVector().set(100F, 100F, PosType.INT));
        stroker.lineTo(new FTVector().set(0F, 100F, PosType.INT));
        stroker.endSubPath();

        final long[] dstPoints = new long[1];
        final long[] dstContours = new long[1];
        stroker.getBorderCounts(FTStrokerBorder.LEFT, dstPoints, dstContours);

        Assertions.assertTrue(dstContours[0] > 0L, "Expected at least 1 contour");
        Assertions.assertTrue(dstPoints[0] >= 4L, "Expected at least 4 points");

        final FTOutline outline = lib.newOutline(dstPoints[0], dstContours[0]);

        outline.setNPoints(0);
        outline.setNContours(0);

        stroker.exportBorder(FTStrokerBorder.LEFT, outline);

        final FTOutlineFuncs funcs = new FTOutlineFuncs();
        AtomicBoolean invoked = new AtomicBoolean();
        funcs.setMoveTo((to) -> {
            invoked.set(true);
            return FTError.OK;
        });
        funcs.setLineTo((to) -> {
            invoked.set(true);
            return FTError.OK;
        });
        funcs.setConicTo((control, to) -> {
            invoked.set(true);
            return FTError.OK;
        });
        funcs.setCubicTo((control1, control2, to) -> {
            invoked.set(true);
            return FTError.OK;
        });

        outline.decompose(funcs);
        funcs.free();

        outline.done(lib);
        stroker.done();
        lib.done();

        Assertions.assertTrue(invoked.get());
    }

    @Test
    public void ftOutlineNew() {
        final FTLibrary lib = new FTLibrary();

        final FTOutline outline = lib.newOutline(0L, 0L);
        outline.done(lib);

        lib.done();
    }

    @Test
    public void ftOutlineDone() {
        final FTLibrary lib = new FTLibrary();

        final FTOutline outline = lib.newOutline(0L, 0L);
        outline.done(lib);

        lib.done();
    }

    @Test
    public void ftOutlineCheck() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(0F, 0F, PosType.INT),
            new FTVector().set(100F, 0F, PosType.INT),
            new FTVector().set(100F, 100F, PosType.INT),
            new FTVector().set(0F, 100F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        outline.check();

        outline.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineGetCBox() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(10F, 20F, PosType.INT),
            new FTVector().set(110F, 20F, PosType.INT),
            new FTVector().set(110F, 120F, PosType.INT),
            new FTVector().set(10F, 120F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        final FTBBox cbox = outline.getCBox();

        Assertions.assertEquals(10F, cbox.getXMin(PosType.INT));
        Assertions.assertEquals(20F, cbox.getYMin(PosType.INT));
        Assertions.assertEquals(110F, cbox.getXMax(PosType.INT));
        Assertions.assertEquals(120F, cbox.getYMax(PosType.INT));

        outline.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineTranslate() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(1L, 1L);

        outline.setNContours(1);
        outline.setNPoints(1);
        outline.setPoints(new FTVector().set(10F, 20F, PosType.INT));
        outline.setContours(0);
        outline.setFlags(FTOutlineFlag.OWNER);

        outline.translate(50F, 100F, PosType.INT);

        final FTVector[] points = outline.getPoints();
        Assertions.assertEquals(60F, points[0].getX(PosType.INT));
        Assertions.assertEquals(120F, points[0].getY(PosType.INT));

        outline.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineCopy() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline source = lib.newOutline(1L, 1L);
        final FTOutline target = lib.newOutline(1L, 1L);

        source.setNContours(1);
        source.setNPoints(1);
        source.setPoints(new FTVector().set(10F, 20F, PosType.INT));
        source.setContours(0);
        source.setFlags(FTOutlineFlag.OWNER);

        source.copy(target);

        final FTVector[] targetPoints = target.getPoints();
        Assertions.assertEquals(10F, targetPoints[0].getX(PosType.INT));
        Assertions.assertEquals(20F, targetPoints[0].getY(PosType.INT));

        source.done(lib);
        target.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineTransform() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(1L, 1L);
        final FTMatrix matrix = new FTMatrix();

        outline.setNContours(1);
        outline.setNPoints(1);
        outline.setPoints(new FTVector().set(10F, 0F, PosType.INT));
        outline.setContours(0);
        outline.setFlags(FTOutlineFlag.OWNER);

        matrix.setXX(0F);
        matrix.setXY(1F);
        matrix.setYX(-1F);
        matrix.setYY(0F);

        outline.transform(matrix);

        final FTVector[] points = outline.getPoints();
        Assertions.assertEquals(0F, points[0].getX(PosType.INT));
        Assertions.assertEquals(-10F, points[0].getY(PosType.INT));

        outline.done(lib);
        matrix.free();
        lib.done();
    }

    @Test
    public void ftOutlineEmbolden() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setCharSize(0F, 16F, 300L, 300L);
        final long glyphIndex = face.getCharIndex('A');
        face.loadGlyph(glyphIndex);

        final FTGlyphSlot slot = face.getGlyph();
        final FTOutline outline = slot.getOutline();

        final FTOutline emboldened = lib.newOutline(outline.getNPoints(), outline.getNContours());
        outline.copy(emboldened);

        emboldened.embolden(1);

        Assertions.assertTrue(emboldened.getNPoints() > 0);
        Assertions.assertTrue(emboldened.getNContours() > 0);

        emboldened.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineEmboldenXY() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setCharSize(0F, 16F, 300L, 300L);
        final long glyphIndex = face.getCharIndex('A');
        face.loadGlyph(glyphIndex);

        final FTGlyphSlot slot = face.getGlyph();
        final FTOutline outline = slot.getOutline();

        final FTOutline emboldened = lib.newOutline(outline.getNPoints(), outline.getNContours());
        outline.copy(emboldened);

        emboldened.embolden(1, 2);

        Assertions.assertTrue(emboldened.getNPoints() > 0);
        Assertions.assertTrue(emboldened.getNContours() > 0);

        emboldened.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineReverse() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(0F, 0F, PosType.INT),
            new FTVector().set(100F, 0F, PosType.INT),
            new FTVector().set(100F, 100F, PosType.INT),
            new FTVector().set(0F, 100F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        outline.reverse();

        final FTVector[] reversed = outline.getPoints();
        Assertions.assertEquals(0F, reversed[1].getX(PosType.INT));
        Assertions.assertEquals(100F, reversed[1].getY(PosType.INT));

        outline.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineGetBitmap() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(10F, 10F, PosType.INT),
            new FTVector().set(90F, 10F, PosType.INT),
            new FTVector().set(90F, 90F, PosType.INT),
            new FTVector().set(10F, 90F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        final FTBitmap bitmap = outline.getBitmap(lib, 100, 50, FTPixelMode.GRAY);

        final ByteBuffer buffer = bitmap.getBuffer();
        final long rows = bitmap.getRows();
        final long width = bitmap.getWidth();
        final int pitch = bitmap.getPitch();

        boolean filled = false;
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < width; x++) {
                final int index = (x + pitch * y);
                if(buffer.get(index) != 0) {
                    filled = true;
                    y = (int) rows;
                    break;
                }
            }
        }
        Assertions.assertTrue(filled);

        outline.done(lib);
        bitmap.free();
        lib.done();
    }

    @Test
    public void ftOutlineRender_DEFAULT() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(0F, 0F, PosType.INT),
            new FTVector().set(100F, 0F, PosType.INT),
            new FTVector().set(100F, 100F, PosType.INT),
            new FTVector().set(0F, 100F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        final FTRasterParams params = new FTRasterParams();

        final FTBitmap bitmap = new FTBitmap();
        bitmap.setRows(50);
        bitmap.setWidth(100);
        bitmap.setPitch(100);
        bitmap.setPixelMode(FTPixelMode.GRAY);
        bitmap.setBuffer();

        params.setTarget(bitmap);

        outline.render(lib, params);

        final ByteBuffer buffer = bitmap.getBuffer();
        final long rows = bitmap.getRows();
        final long width = bitmap.getWidth();
        final int pitch = bitmap.getPitch();

        boolean filled = false;
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < width; x++) {
                final int index = (x + pitch * y);
                if(buffer.get(index) != 0) {
                    filled = true;
                    y = (int) rows;
                    break;
                }
            }
        }
        Assertions.assertTrue(filled);

        outline.done(lib);
        params.free();
        lib.done();
    }

    @Test
    public void ftOutlineRender_DIRECT() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(0F, 0F, PosType.INT),
            new FTVector().set(100F, 0F, PosType.INT),
            new FTVector().set(100F, 100F, PosType.INT),
            new FTVector().set(0F, 100F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        final FTRasterParams params = new FTRasterParams();

        params.setFlags(FTRasterFlag.DIRECT, FTRasterFlag.AA);

        final AtomicBoolean graySpansInvoked = new AtomicBoolean(false);
        final AtomicBoolean blackSpansInvoked = new AtomicBoolean(false);
        final AtomicBoolean bitTestInvoked = new AtomicBoolean(false);
        final AtomicBoolean bitSetInvoked = new AtomicBoolean(false);

        params.setGraySpans((y, count, spans) -> {
            graySpansInvoked.set(true);
        });
        params.setBlackSpans((y, count, spans) -> {
            blackSpansInvoked.set(true);
        });
        params.setBitTest((y, x) -> {
            bitTestInvoked.set(true);
            return 1;
        });
        params.setBitSet((y, x) -> {
            bitSetInvoked.set(true);
        });

        outline.render(lib, params);

        Assertions.assertTrue(graySpansInvoked.get());
        Assertions.assertFalse(blackSpansInvoked.get()); // unused
        Assertions.assertFalse(bitTestInvoked.get()); // unused
        Assertions.assertFalse(bitSetInvoked.get()); // unused

        outline.done(lib);
        params.free();
        lib.done();
    }

    @Test
    public void ftOutlineGetOrientation() {
        final FTLibrary lib = new FTLibrary();
        final FTOutline outline = lib.newOutline(4L, 1L);

        outline.setNContours(1);
        outline.setNPoints(4);
        outline.setPoints(
            new FTVector().set(0F, 0F, PosType.INT),
            new FTVector().set(100F, 0F, PosType.INT),
            new FTVector().set(100F, 100F, PosType.INT),
            new FTVector().set(0F, 100F, PosType.INT)
        );
        outline.setContours(3);
        outline.setFlags(FTOutlineFlag.OWNER, FTOutlineFlag.EVEN_ODD_FILL, FTOutlineFlag.REVERSE_FILL, FTOutlineFlag.IGNORE_DROPOUTS);

        final FTOrientation orientation = outline.getOrientation();
        Assertions.assertTrue(orientation == FTOrientation.TRUETYPE || orientation == FTOrientation.POSTSCRIPT);

        outline.done(lib);
        lib.done();
    }

    @Test
    public void ftOutlineGetInsideBorder() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('O');
        final FTGlyphSlot slot = face.getGlyph();

        final FTStrokerBorder border = slot.getOutline().getInsideBorder();
        Assertions.assertSame(FTStrokerBorder.RIGHT, border);

        face.done();
        lib.done();
    }

    @Test
    public void ftOutlineGetOutsideBorder() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        face.setPixelSizes(0L, 16L);
        face.loadChar('O');
        final FTGlyphSlot slot = face.getGlyph();

        final FTStrokerBorder border = slot.getOutline().getOutsideBorder();
        Assertions.assertSame(FTStrokerBorder.LEFT, border);

        face.done();
        lib.done();
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
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        face.setPixelSizes(0L, 16L);

        final FTStroker stroker = lib.newStroker();
        stroker.set(1F / 64F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        face.loadChar('C');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTGlyph strokedGlyph = glyph.strokeBorder(stroker, false, true);

        final FTBitmapGlyph bitmapGlyph = strokedGlyph.toBitmap(FTRenderMode.NORMAL, null, true);
        final FTBitmap bitmap = bitmapGlyph.getBitmap();

        stroker.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftStrokerRewind() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();

        stroker.rewind();

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerParseOutline() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);

        final FTStroker stroker = lib.newStroker();
        stroker.set(64F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        face.setPixelSizes(0L, 16L);
        face.loadChar('C');
        final FTGlyphSlot slot = face.getGlyph();

        stroker.parseOutline(slot.getOutline(), false);

        stroker.done();

        face.done();
        lib.done();
    }

    @Test
    public void ftStrokerBeginSubPath() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        final FTVector start = new FTVector().set(0F, 0F, PosType.INT);

        stroker.beginSubPath(start, false);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.endSubPath();

        start.free();

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerEndSubPath() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.ROUND, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100F, 100F, PosType.INT));
        stroker.endSubPath();

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerLineTo() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();

        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);
        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), false);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.endSubPath();

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerConicTo() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);

        final FTVector control = new FTVector().set(50F, 100F, PosType.INT);
        final FTVector end = new FTVector().set(100F, 0F, PosType.INT);

        stroker.conicTo(control, end);
        stroker.endSubPath();

        control.free();
        end.free();

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerCubicTo() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);

        final FTVector control1 = new FTVector().set(30F, 100F, PosType.INT);
        final FTVector control2 = new FTVector().set(70F, 100F, PosType.INT);
        final FTVector end = new FTVector().set(100F, 0F, PosType.INT);

        stroker.cubicTo(control1, control2, end);
        stroker.endSubPath();

        control1.free();
        control2.free();

        end.free();
        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerGetBorderCounts() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.endSubPath();

        final long[] dstNumPoints = new long[1];
        final long[] dstNumContours = new long[1];
        stroker.getBorderCounts(FTStrokerBorder.LEFT, dstNumPoints, dstNumContours);

        Assertions.assertTrue(dstNumPoints[0] > 0L);
        Assertions.assertTrue(dstNumContours[0] > 0L);

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerExportBorder() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.lineTo(new FTVector().set(100F, 100F, PosType.INT));
        stroker.lineTo(new FTVector().set(0F, 100F, PosType.INT));
        stroker.endSubPath();

        final long[] dstPoints = new long[1];
        final long[] dstContours = new long[1];
        stroker.getBorderCounts(FTStrokerBorder.LEFT, dstPoints, dstContours);

        Assertions.assertTrue(dstContours[0] > 0, "Expected at least 1 contour");
        Assertions.assertTrue(dstPoints[0] >= 4, "Expected at least 4 points");

        final FTOutline outline = lib.newOutline(dstPoints[0], dstContours[0]);

        outline.setNPoints(0);
        outline.setNContours(0);

        stroker.exportBorder(FTStrokerBorder.LEFT, outline);

        Assertions.assertTrue(outline.getNPoints() > 0, "Outline should have points");
        Assertions.assertTrue(outline.getNContours() > 0, "Outline should have contours");

        final FTVector[] points = outline.getPoints();
        Assertions.assertNotNull(points);
        Assertions.assertTrue(points.length > 0);

        outline.done(lib);
        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerGetCounts() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0);

        stroker.beginSubPath(new FTVector().set(0, 0, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100, 0, PosType.INT));
        stroker.endSubPath();

        final long[] dstNumPoints = new long[1];
        final long[] dstNumContours = new long[1];
        stroker.getCounts(dstNumPoints, dstNumContours);

        Assertions.assertTrue(dstNumPoints[0] > 0);
        Assertions.assertTrue(dstNumContours[0] > 0);

        stroker.done();
        lib.done();
    }

    @Test
    public void ftStrokerExport() {
        final FTLibrary lib = new FTLibrary();
        final FTStroker stroker = lib.newStroker();
        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        stroker.beginSubPath(new FTVector().set(0F, 0F, PosType.INT), true);
        stroker.lineTo(new FTVector().set(100F, 0F, PosType.INT));
        stroker.endSubPath();

        final long[] dstNumPoints = new long[1];
        final long[] dstNumContours = new long[1];
        stroker.getBorderCounts(FTStrokerBorder.LEFT, dstNumPoints, dstNumContours);

        final FTOutline outline = lib.newOutline(dstNumPoints[0], dstNumContours[0]);

        outline.setNPoints(0);
        outline.setNContours(0);

        stroker.export(outline);
        Assertions.assertTrue(outline.getNPoints() > 0);
        outline.done(lib);

        stroker.done();
        lib.done();
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
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        final FTStroker stroker = lib.newStroker();

        stroker.set(10F, FTStrokerLineCap.ROUND, FTStrokerLineJoin.ROUND, 0F);

        face.setPixelSizes(0L, 24L);
        face.loadChar('B');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTGlyph strokedGlyph = glyph.stroke(stroker, true);
        Assertions.assertNotNull(strokedGlyph);

        final FTBBox bbox = new FTBBox();
        strokedGlyph.getCBox(FTGlyphBBoxMode.TRUNCATE, bbox);
        Assertions.assertTrue(bbox.getXMax(PosType.INT) > bbox.getXMin(PosType.INT));

        bbox.free();
        strokedGlyph.done();
        stroker.done();
        face.done();
        lib.done();
    }

    @Test
    public void ftGlyphStrokeBorder() {
        final FTLibrary lib = new FTLibrary();
        final FTFace face = lib.newMemoryFace(Resource.internal("/droidsans.ttf").readByteBuffer(), 0);
        final FTStroker stroker = lib.newStroker();

        stroker.set(10F, FTStrokerLineCap.BUTT, FTStrokerLineJoin.ROUND, 0F);

        face.setPixelSizes(0L, 24L);
        face.loadChar('C');
        final FTGlyph glyph = face.getGlyph().getGlyph();

        final FTGlyph strokedGlyph = glyph.strokeBorder(stroker, true, false);
        Assertions.assertNotNull(strokedGlyph);

        final FTBBox originalBox = glyph.getCBox(FTGlyphBBoxMode.TRUNCATE);
        final FTBBox strokedBox = strokedGlyph.getCBox(FTGlyphBBoxMode.TRUNCATE);

        Assertions.assertNotEquals(originalBox.getXMax(PosType.INT), strokedBox.getXMax(PosType.INT));

        originalBox.free();
        strokedBox.free();
        strokedGlyph.done();
        glyph.done();
        stroker.done();
        face.done();
        lib.done();
    }

}
