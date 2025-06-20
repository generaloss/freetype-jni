package generaloss.freetype.types;

import generaloss.freetype.FTStruct;
import generaloss.freetype.FreeType;

import java.util.Objects;

public class FTMatrix extends FTStruct { // struct done.

    public FTMatrix(long pointer) {
        super(pointer);
    }

    public FTMatrix() {
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


    // FT_Fixed xx;
    private static native int getXX(long pointer);

    public float getXX() {
        final int raw = getXX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setXX(long pointer, int valueRaw);

    public FTMatrix setXX(float value) {
        final int raw = FTFixed.of(value);
        setXX(super.pointer, raw);
        return this;
    }

    public FTMatrix setXX(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setXX(value.getFloat());
    }

    // FT_Fixed xy;
    private static native int getXY(long pointer);

    public float getXY() {
        final int raw = getXY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setXY(long pointer, int valueRaw);

    public FTMatrix setXY(float value) {
        final int raw = FTFixed.of(value);
        setXY(super.pointer, raw);
        return this;
    }

    public FTMatrix setXY(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setXY(value.getFloat());
    }

    // FT_Fixed yx;
    private static native int getYX(long pointer);

    public float getYX() {
        final int raw = getYX(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYX(long pointer, int valueRaw);

    public FTMatrix setYX(float value) {
        final int raw = FTFixed.of(value);
        setYX(super.pointer, raw);
        return this;
    }

    public FTMatrix setYX(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setYX(value.getFloat());
    }

    // FT_Fixed yy;
    private static native int getYY(long pointer);

    public float getYY() {
        final int raw = getYY(super.pointer);
        return FTFixed.toFloat(raw);
    }

    private static native void setYY(long pointer, int valueRaw);

    public FTMatrix setYY(float value) {
        final int raw = FTFixed.of(value);
        setYY(super.pointer, raw);
        return this;
    }

    public FTMatrix setYY(FTFixed value) {
        if(value == null)
            throw new NullPointerException("Value is null");
        return this.setYY(value.getFloat());
    }


    public FTMatrix setIdentity() {
        this.setXX(1F);
        this.setYY(1F);
        return this;
    }

    public FTMatrix setRotationRad(double angleRad) {
        final float cos = (float) Math.cos(angleRad);
        final float sin = (float) Math.sin(angleRad);
        this.setXX(cos);
        this.setXY(-sin);
        this.setYX(sin);
        this.setYY(cos);
        return this;
    }

    public FTMatrix setRotation(double angleDeg) {
        final double angleRad = Math.toRadians(angleDeg);
        return this.setRotationRad(angleRad);
    }

    public FTMatrix setScale(float scaleX, float scaleY) {
        this.setXX(scaleX);
        this.setYY(scaleY);
        return this;
    }

    public FTMatrix scale(float scaleX, float scaleY) {
        final float x = (this.getXX() * scaleX);
        final float y = (this.getYY() * scaleY);
        return this.setScale(x, y);
    }


    @Override
    public String toString() {
        return (super.toString() + "{xx=" + this.getXX() + ", xy=" + this.getXY() + ", yx=" + this.getYX() + ", yy=" + this.getYY() + '}');
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || this.getClass() != object.getClass())
            return false;
        final FTMatrix matrix = (FTMatrix) object;
        return (
            this.getXX() == matrix.getXX() && this.getXY() == matrix.getXY() &&
            this.getYX() == matrix.getYX() && this.getYY() == matrix.getYY()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getXX(), this.getXY(), this.getYX(), this.getYY());
    }


    public void multiply(FTMatrix b) {
        FreeType.ftMatrixMultiply(this, b);
    }

    public void invert() {
        final FTError error = FreeType.ftMatrixInvert(this);
        error.checkError();
    }

}
