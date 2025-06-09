import generaloss.freetype.FreeType;
import generaloss.freetype.types.*;

public class TypesTest {

    public static void main(String[] args) {
        // Fixed
        {
            System.out.println("FTFixed {");
            final FTFixed num = new FTFixed();

            num.set(235.54335F);
            System.out.println("  " + num.getFloat());

            System.out.println("  " + num.getIntFloor());
            System.out.println("  " + num.getIntRound());
            System.out.println("  " + num.getIntCeil());

            System.out.println("  " + FreeType.ftFloorFix(num));
            System.out.println("  " + FreeType.ftRoundFix(num));
            System.out.println("  " + FreeType.ftCeilFix(num));

            num.free();
            System.out.println("}");
        }

        // Vector
        {
            System.out.println("FTVector {");
            final FTVector vec = new FTVector();

            vec.set(235.54335F, 13.13F);
            System.out.println("  " + vec);

            vec.free();
            System.out.println("}");
        }

        // Matrix
        {
            System.out.println("FTMatrix {");
            final FTMatrix mat = new FTMatrix();

            mat.setXX(1.5F);
            mat.setXY(-0.5F);
            mat.setYX(1.25F);
            mat.setYY(-0.25F);
            System.out.println("  " + mat);

            mat.free();
            System.out.println("}");
        }

        // F26Dot6
        {
            System.out.println("FTF26Dot6 {");
            FTF26Dot6 num = new FTF26Dot6();

            num.set(-17.253647F);
            System.out.println("  " + num.getFloat());

            num.free();
            System.out.println("}");
        }

        // FTPos
        {
            System.out.println("FTPos {");
            FTPos pos = new FTPos();

            pos.set(65535.25F);
            System.out.println("  " + pos.getFloat());

            pos.free();
            System.out.println("}");
        }
    }

}
