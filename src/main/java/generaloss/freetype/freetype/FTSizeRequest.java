package generaloss.freetype.freetype;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

public class FTSizeRequest extends FTStruct { // struct done.

    public FTSizeRequest(long pointer) {
        super(pointer);
    }

    public FTSizeRequest() {
        this(createPointer());
    }

    static {
        FreeType.init();
    }

    private static native long createPointer();

    private static native void freePointer(long pointer);

    public void free() {
        freePointer(this.pointer);
        super.destroyPointer();
    }


    // FT_Size_Request_Type type;
    private static native int getType(long pointer);

    public FTSizeRequestType getType() {
        final int raw = getType(super.pointer);
        return FTSizeRequestType.byValue(raw);
    }

    // FT_Long width;
    private static native long getWidth(long pointer);

    public long getWidth() {
        return getWidth(super.pointer);
    }

    // FT_Long height;
    private static native long getHeight(long pointer);

    public long getHeight() {
        return getHeight(super.pointer);
    }

    // FT_UInt horiResolution;
    private static native long getHoriResolution(long pointer);

    public long getHoriResolution() {
        return getHoriResolution(super.pointer);
    }

    // FT_UInt vertResolution;
    private static native long getVertResolution(long pointer);

    public long getVertResolution() {
        return getVertResolution(super.pointer);
    }

}
