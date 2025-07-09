package generaloss.freetype.image;

import generaloss.freetype.BitMask;
import generaloss.freetype.freetype.SubglyphFlags;

public class RasterFlags extends BitMask<FTRasterFlag> {

    public RasterFlags() { }

    public RasterFlags(int bits) {
        super(bits);
    }


    public boolean hasAA() {
        return super.has(FTRasterFlag.AA);
    }

    public boolean hasDirect() {
        return super.has(FTRasterFlag.DIRECT);
    }

    public boolean hasClip() {
        return super.has(FTRasterFlag.CLIP);
    }

    public boolean hasSDF() {
        return super.has(FTRasterFlag.SDF);
    }


    @Override
    public String toString() {
        return super.toString(FTRasterFlag.class);
    }

    @Override
    public RasterFlags copy() {
        return new RasterFlags(super.getBits());
    }

}