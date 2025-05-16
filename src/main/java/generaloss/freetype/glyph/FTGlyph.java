package generaloss.freetype.glyph;

import generaloss.freetype.FTObject;
import generaloss.freetype.stroker.FTStroker;

public class FTGlyph extends FTObject {

    public FTGlyph(long pointer) {
        super(pointer);
    }


    private static native long strokeBorder(long pointer, long stroker, boolean inside);

    /** Stroke a given outline glyph object with a given stroker, but only return either its inside or outside border.c */
    public void strokeBorder(FTStroker stroker, boolean inside) {
        super.pointer = strokeBorder(super.pointer, stroker.getPointer(), inside);
    }


    private static native long toBitmap(long pointer, int renderMode);

    /** Convert a given glyph object to a bitmap glyph object. */
    public FTBitmapGlyph toBitmap(FTRenderMode renderMode) {
        final long bitmapPointer = toBitmap(super.pointer, renderMode.value);
        return new FTBitmapGlyph(bitmapPointer);
    }


    private static native void done(long pointer);

    /** Destroy a given glyph. */
    @Override
    public void done() {
        done(super.pointer);
        super.done();
    }

}