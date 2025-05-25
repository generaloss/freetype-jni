package generaloss.freetype.freetype;

import generaloss.freetype.types.FTError;
import generaloss.freetype.FTStruct;
import generaloss.freetype.stroker.FTStroker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FTLibrary extends FTStruct {

    private FTLibrary(long poniter) {
        super(poniter);
    }


    private static native long initFreeType();

    public FTLibrary() {
        super(initFreeType());
    }


    private static native long newFace(long pointer, String filepath, long faceIndex);

    public FTFace newFace(String filepath, long faceIndex) {
        final long facePointer = newFace(super.pointer, filepath, faceIndex);
        return new FTFace(facePointer, this);
    }


    private static native long newMemoryFace(long pointer, ByteBuffer data, int dataSize, int faceIndex);

    public FTFace newMemoryFace(ByteBuffer buffer, int faceIndex) {
        final long facePointer = newMemoryFace(super.pointer, buffer, buffer.remaining(), faceIndex);
        return new FTFace(facePointer, this);
    }

    public FTFace newMemoryFace(byte[] data, int faceIndex) {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.put(data);
        buffer.position(0);
        return newMemoryFace(buffer, faceIndex);
    }


    // FTError openFace(FTLibrary library, FTOpenArgs args, long faceIndex, FTFace* face)


    // void FTLibraryVersion(FTLibrary library, int* major, int* minor, int* patch)


    private static native long strokerNew(long pointer);

    public FTStroker strokerNew() {
        final long strokerPointer = strokerNew(super.pointer);
        return new FTStroker(strokerPointer);
    }


    private static native void doneFreeType(long pointer);

    @Override
    public void done() {
        doneFreeType(super.pointer);
        super.done();
    }


    private static native int getLastErrorCode();

    public static FTError getLastError() {
        return FTError.byCode(getLastErrorCode());
    }


    public static int encodeChars(char a, char b, char c, char d) {
        return (a << 24) | (b << 16) | (c << 8) | d;
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
