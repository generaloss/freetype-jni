package generaloss.freetype.image;

@FunctionalInterface
public interface FTRasterBitTestFunc {

    // int FT_Raster_BitTest_Func(int y, int x, void* user);
    int invoke(int y, int x);

}
