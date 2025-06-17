package unit;

import generaloss.freetype.FreeType;
import generaloss.freetype.types.*;
import org.junit.Assert;
import org.junit.Test;

public class TypesTests {

    @Test
    public void ftFixed() {
        final FTFixed num = new FTFixed().set(235.54335F);

        Assert.assertEquals(235.54335F, num.getFloat(), 0F);

        Assert.assertEquals(235, num.getIntFloor());
        Assert.assertEquals(236, num.getIntRound());
        Assert.assertEquals(236, num.getIntCeil());

        Assert.assertEquals(235F, FreeType.ftFloorFix(num), 0F);
        Assert.assertEquals(236F, FreeType.ftRoundFix(num), 0F);
        Assert.assertEquals(236F, FreeType.ftCeilFix(num), 0F);

        num.free();
    }

    @Test
    public void ftVector() {
        final FTVector vec = new FTVector().set(65535.25F, 1323.125F);

        Assert.assertEquals(65535.25F, vec.getX(), 0F);
        Assert.assertEquals(1323.125F, vec.getY(), 0F);

        vec.free();
    }

    @Test
    public void ftMatrix() {
        final FTMatrix mat = new FTMatrix();

        mat.setXX(1.5F);
        mat.setXY(-0.5F);
        mat.setYX(1.25F);
        mat.setYY(-0.25F);

        Assert.assertEquals(1.5F, mat.getXX(), 0F);
        Assert.assertEquals(-0.5F, mat.getXY(), 0F);
        Assert.assertEquals(1.25F, mat.getYX(), 0F);
        Assert.assertEquals(-0.25F, mat.getYY(), 0F);

        mat.free();
    }

    @Test
    public void ftF26Dot6() {
        FTF26Dot6 num = new FTF26Dot6().set(-17234.25F);

        Assert.assertEquals(-17234.25F, num.getFloat(), 0F);

        num.free();
    }

    @Test
    public void ftPos() {
        FTPos pos = new FTPos().set(65535.25F);

        Assert.assertEquals(65535.25F, pos.getFloat(), 0F);

        pos.free();
    }

}
