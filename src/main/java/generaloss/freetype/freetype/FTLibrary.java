package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.system.FTMemory;
import generaloss.freetype.types.FTError;

import java.nio.ByteBuffer;

public class FTLibrary extends FTStruct { // struct done.

    public FTLibrary(long poniter) {
        super(poniter);
    }

    public FTLibrary() {
        super(freeTypeInit());
    }

    private static long freeTypeInit() {
        final long[] dstLibraryPointer = new long[1];
        final FTError error = FreeType.ftInitFreeType(dstLibraryPointer);
        error.checkError();
        return dstLibraryPointer[0];
    }


    // FT_Memory memory;
    private static native long getMemory(long pointer);

    public FTMemory getMemory() {
        final long pointer = getMemory(super.pointer);
        return FTStructCache.getOrCreate(FTMemory.class, pointer, FTMemory::new);
    }


    public void done() {
        final FTError error = FreeType.ftDoneFreeType(this);
        error.checkError();
    }

    public FTFace newFace(String filepath, long faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewFace(this, filepath, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTFace.class, dstFacePointer[0], FTFace::new); 
    }

    public FTFace newMemoryFace(ByteBuffer dataBuffer, long dataSize, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewMemoryFace(this, dataBuffer, dataSize, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTFace.class, dstFacePointer[0], FTFace::new); 
    }

    public FTFace newMemoryFace(ByteBuffer dataBuffer, int faceIndex) {
        return this.newMemoryFace(dataBuffer, dataBuffer.remaining(), faceIndex);
    }

    public FTFace newMemoryFace(byte[] data, long dataSize, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewMemoryFace(this, data, dataSize, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTFace.class, dstFacePointer[0], FTFace::new); 
    }

    public FTFace newMemoryFace(byte[] data, int faceIndex) {
        return this.newMemoryFace(data, data.length, faceIndex);
    }

    public FTFace openFace(FTOpenArgs args, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftOpenFace(this, args, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTFace.class, dstFacePointer[0], FTFace::new); 
    }

    public void getVersion(int[] dstMajor, int[] dstMinor, int[] dstPatch) {
        FreeType.ftLibraryVersion(this, dstMajor, dstMinor, dstPatch);
    }

    public String getVersion() {
        final int[] dstMajor = new int[1];
        final int[] dstMinor = new int[1];
        final int[] dstPatch = new int[1];
        this.getVersion(dstMajor, dstMinor, dstPatch);
        return (dstMajor[0] + "." + dstMinor[0] + "." + dstPatch[0]);
    }

    public FTGlyph newGlyph(FTGlyphFormat format) {
        final long[] dstGlyphPointer = new long[1];
        final FTError error = FreeType.ftNewGlyph(this, format, dstGlyphPointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTGlyph.class, dstGlyphPointer[0], FTGlyph::new); 
    }

    public FTStroker newStroker() {
        final long[] dstStrokerPointer = new long[1];
        final FTError error = FreeType.ftStrokerNew(this, dstStrokerPointer);
        error.checkError();
        return FTStructCache.getOrCreate(FTStroker.class, dstStrokerPointer[0], FTStroker::new); 
    }

}
