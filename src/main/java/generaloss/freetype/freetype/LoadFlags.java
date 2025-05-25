package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class LoadFlags extends BitMask {

    public LoadFlags() { }

    public LoadFlags(int bits) {
        super(bits);
    }


    public boolean has(FTLoad flag) {
        return super.has(flag.value);
    }

    public LoadFlags set(FTLoad flag) {
       super.set(flag.value);
        return this;
    }

    public LoadFlags clear(FTLoad flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean has(FTLoadTarget target) {
        return super.has(target.value);
    }

    public LoadFlags set(FTLoadTarget target) {
        super.set(target.value);
        return this;
    }

    public LoadFlags clear(FTLoadTarget target) {
        super.clear(target.value);
        return this;
    }


    public boolean hasNoScale() {
        return this.has(FTLoad.NO_SCALE);
    }

    public boolean hasNoHinting() {
        return this.has(FTLoad.NO_HINTING);
    }

    public boolean hasRender() {
        return this.has(FTLoad.RENDER);
    }

    public boolean hasNoBitmap() {
        return this.has(FTLoad.NO_BITMAP);
    }

    public boolean hasVerticalLayout() {
        return this.has(FTLoad.VERTICAL_LAYOUT);
    }

    public boolean hasForceAutohint() {
        return this.has(FTLoad.FORCE_AUTOHINT);
    }

    public boolean hasCropBitmap() {
        return this.has(FTLoad.CROP_BITMAP);
    }

    public boolean hasPedantic() {
        return this.has(FTLoad.PEDANTIC);
    }

    public boolean hasIgnoreGlobalAdvanceWidth() {
        return this.has(FTLoad.IGNORE_GLOBAL_ADVANCE_WIDTH);
    }

    public boolean hasNoRecurse() {
        return this.has(FTLoad.NO_RECURSE);
    }

    public boolean hasIgnoreTransform() {
        return this.has(FTLoad.IGNORE_TRANSFORM);
    }

    public boolean hasMonochrome() {
        return this.has(FTLoad.MONOCHROME);
    }

    public boolean hasLinearDesign() {
        return this.has(FTLoad.LINEAR_DESIGN);
    }

    public boolean hasSBitsOnly() {
        return this.has(FTLoad.SBITS_ONLY);
    }

    public boolean hasNoAutohint() {
        return this.has(FTLoad.NO_AUTOHINT);
    }

    public boolean hasColor() {
        return this.has(FTLoad.COLOR);
    }

    public boolean hasComputeMetrics() {
        return this.has(FTLoad.COMPUTE_METRICS);
    }

    public boolean hasBitmapMetricsOnly() {
        return this.has(FTLoad.BITMAP_METRICS_ONLY);
    }

    public boolean hasNoSVG() {
        return this.has(FTLoad.NO_SVG);
    }


    public boolean hasTargetLight() {
        return this.has(FTLoadTarget.LIGHT);
    }

    public boolean hasTargetMono() {
        return this.has(FTLoadTarget.MONO);
    }

    public boolean hasTargetLCD() {
        return this.has(FTLoadTarget.LCD);
    }

    public boolean hasTargetLCD_V() {
        return this.has(FTLoadTarget.LCD_V);
    }


}
