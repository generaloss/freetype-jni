package generaloss.freetype.charmap;

import generaloss.freetype.FTObject;
import generaloss.freetype.face.FTFace;

public class FTCharMap extends FTObject {

    private final FTFace face;

    public FTCharMap(long pointer, FTFace face) {
        super(pointer);
        this.face = face;
    }

    public FTFace getFace() {
        return face;
    }


    private static native int getEncoding(long pointer);

    public FTEncoding getEncoding() {
        final int raw = getEncoding(super.pointer);
        return FTEncoding.byValue(raw);
    }


    private static native int getPlatformID(long pointer);

    public int getPlatformID() {
        return getPlatformID(super.pointer);
    }


    private static native int getEncodingID(long pointer);

    public int getEncodingID() {
        return getEncodingID(super.pointer);
    }

}
