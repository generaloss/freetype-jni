package generaloss.freetype.image;

@FunctionalInterface
public interface FTRasterBitSetFunc {

    // void FT_Raster_BitSet_Func(int y, int x, void* user);
    void invoke(int y, int x);

}
