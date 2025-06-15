package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class LoadFlags extends BitMask<FTLoad> {

    public LoadFlags() { }

    public LoadFlags(int bits) {
        super(bits);
    }


    public boolean has(FTLoadTarget target) {
        final int targetBits = (super.getBits() & FTLoadTarget.MASK);
        return (targetBits == target.value);
    }

    public LoadFlags set(FTLoadTarget target) {
        this.clearTarget();
        super.set(target.value);
        return this;
    }

    public LoadFlags clearTarget() {
        super.clear(FTLoadTarget.MASK);
        return this;
    }

    public FTLoadTarget getTarget() {
        final int value = (super.getBits() & FTLoadTarget.MASK);
        return FTLoadTarget.byValue(value);
    }


    public boolean hasNoScale() {
        return super.has(FTLoad.NO_SCALE);
    }

    public boolean hasNoHinting() {
        return super.has(FTLoad.NO_HINTING);
    }

    public boolean hasRender() {
        return super.has(FTLoad.RENDER);
    }

    public boolean hasNoBitmap() {
        return super.has(FTLoad.NO_BITMAP);
    }

    public boolean hasVerticalLayout() {
        return super.has(FTLoad.VERTICAL_LAYOUT);
    }

    public boolean hasForceAutohint() {
        return super.has(FTLoad.FORCE_AUTOHINT);
    }

    public boolean hasCropBitmap() {
        return super.has(FTLoad.CROP_BITMAP);
    }

    public boolean hasPedantic() {
        return super.has(FTLoad.PEDANTIC);
    }

    public boolean hasIgnoreGlobalAdvanceWidth() {
        return super.has(FTLoad.IGNORE_GLOBAL_ADVANCE_WIDTH);
    }

    public boolean hasNoRecurse() {
        return super.has(FTLoad.NO_RECURSE);
    }

    public boolean hasIgnoreTransform() {
        return super.has(FTLoad.IGNORE_TRANSFORM);
    }

    public boolean hasMonochrome() {
        return super.has(FTLoad.MONOCHROME);
    }

    public boolean hasLinearDesign() {
        return super.has(FTLoad.LINEAR_DESIGN);
    }

    public boolean hasSBitsOnly() {
        return super.has(FTLoad.SBITS_ONLY);
    }

    public boolean hasNoAutohint() {
        return super.has(FTLoad.NO_AUTOHINT);
    }

    public boolean hasColor() {
        return super.has(FTLoad.COLOR);
    }

    public boolean hasComputeMetrics() {
        return super.has(FTLoad.COMPUTE_METRICS);
    }

    public boolean hasBitmapMetricsOnly() {
        return super.has(FTLoad.BITMAP_METRICS_ONLY);
    }

    public boolean hasNoSVG() {
        return super.has(FTLoad.NO_SVG);
    }


    public boolean isTargetLight() {
        return this.has(FTLoadTarget.LIGHT);
    }

    public boolean isTargetMono() {
        return this.has(FTLoadTarget.MONO);
    }

    public boolean isTargetLCD() {
        return this.has(FTLoadTarget.LCD);
    }

    public boolean isTargetLCDV() {
        return this.has(FTLoadTarget.LCD_V);
    }


    @Override
    public String toString() {
        final String string = super.toString(FTLoad.class);
        return string.substring(0, string.length() - 1) + ", FTLoadTarget=" + this.getTarget() + "}";
    }

}
