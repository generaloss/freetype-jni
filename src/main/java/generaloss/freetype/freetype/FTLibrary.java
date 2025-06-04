package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FTStructCache;
import generaloss.freetype.FreeType;
import generaloss.freetype.glyph.FTGlyph;
import generaloss.freetype.image.FTGlyphFormat;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.types.FTError;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

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


    public void done() {
        final FTError error = FreeType.ftDoneFreeType(this);
        error.checkError();
    }

    public FTFace newFace(String filepath, long faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewFace(this, filepath, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstFacePointer[0], FTFace::new);
    }

    public FTFace newMemoryFace(ByteBuffer dataBuffer, long dataSize, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewMemoryFace(this, dataBuffer, dataSize, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstFacePointer[0], FTFace::new);
    }

    public FTFace newMemoryFace(ByteBuffer dataBuffer, int faceIndex) {
        return this.newMemoryFace(dataBuffer, dataBuffer.remaining(), faceIndex);
    }

    public FTFace newMemoryFace(byte[] data, long dataSize, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftNewMemoryFace(this, data, dataSize, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstFacePointer[0], FTFace::new);
    }

    public FTFace newMemoryFace(byte[] data, int faceIndex) {
        return this.newMemoryFace(data, data.length, faceIndex);
    }

    public FTFace openFace(FTOpenArgs args, int faceIndex) {
        final long[] dstFacePointer = new long[1];
        final FTError error = FreeType.ftOpenFace(this, args, faceIndex, dstFacePointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstFacePointer[0], FTFace::new);
    }

    public void getVersion(int[] dstMajor, int[] dstMinor, int[] dstPatch) {
        FreeType.ftLibraryVersion(this, dstMajor, dstMinor, dstPatch);
    }

    public FTGlyph newGlyph(FTGlyphFormat format) {
        final long[] dstGlyphPointer = new long[1];
        final FTError error = FreeType.ftNewGlyph(this, format, dstGlyphPointer);
        error.checkError();
        return FTStructCache.getOrCreate(dstGlyphPointer[0], FTGlyph::new);
    }

    public FTStroker newStroker() {
        final FTStroker dstStroker = FTStroker.newInstance();
        final FTError error = FreeType.ftStrokerNew(this, dstStroker);
        error.checkError();
        return dstStroker;
    }


    static {
        loadNative();
    }

    private static final String LIBRARY_NAME = "freetype_jni";

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

}
