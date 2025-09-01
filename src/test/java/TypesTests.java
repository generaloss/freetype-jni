import generaloss.freetype.FreeType;
import generaloss.freetype.types.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypesTests {

    @Test
    public void ftFixed() {
        final FTFixed num = new FTFixed().set(235.54335F);

        Assertions.assertEquals(235.54335F, num.getFloat(), 0F);

        Assertions.assertEquals(235, num.getIntFloor());
        Assertions.assertEquals(236, num.getIntRound());
        Assertions.assertEquals(236, num.getIntCeil());

        Assertions.assertEquals(235F, FreeType.ftFloorFix(num), 0F);
        Assertions.assertEquals(236F, FreeType.ftRoundFix(num), 0F);
        Assertions.assertEquals(236F, FreeType.ftCeilFix(num), 0F);

        num.free();
    }

    @Test
    public void ftVector() {
        final FTVector vec = new FTVector().set(65535.25F, 1323.125F, PosType.F26DOT6);

        Assertions.assertEquals(65535.25F, vec.getX(PosType.F26DOT6), 0F);
        Assertions.assertEquals(1323.125F, vec.getY(PosType.F26DOT6), 0F);

        vec.free();
    }

    @Test
    public void ftMatrix() {
        final FTMatrix mat = new FTMatrix();

        mat.setXX(1.5F);
        mat.setXY(-0.5F);
        mat.setYX(1.25F);
        mat.setYY(-0.25F);

        Assertions.assertEquals(1.5F, mat.getXX(), 0F);
        Assertions.assertEquals(-0.5F, mat.getXY(), 0F);
        Assertions.assertEquals(1.25F, mat.getYX(), 0F);
        Assertions.assertEquals(-0.25F, mat.getYY(), 0F);

        mat.free();
    }

    @Test
    public void ftF26Dot6() {
        final FTF26Dot6 num = new FTF26Dot6().set(-17234.25F);

        Assertions.assertEquals(-17234.25F, num.getFloat(), 0F);

        num.free();
    }

    @Test
    public void ftPos() {
        final FTPos pos = new FTPos(PosType.F26DOT6).set(65535.25F);

        Assertions.assertEquals(65535.25F, pos.getFloat(), 0F);

        pos.free();
    }

}
