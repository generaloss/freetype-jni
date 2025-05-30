package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class FSTypeFlags extends BitMask {

    public FSTypeFlags() { }

    public FSTypeFlags(int bits) {
        super(bits);
    }


    public boolean has(FTFSType flag) {
        return super.has(flag.value);
    }

    public FSTypeFlags set(FTFSType flag) {
        super.set(flag.value);
        return this;
    }

    public FSTypeFlags clear(FTFSType flag) {
        super.clear(flag.value);
        return this;
    }


    public boolean hasInstallableEmbedding() {
        return this.has(FTFSType.INSTALLABLE_EMBEDDING);
    }

    public boolean hasRestrictedLicenseEmbedding() {
        return this.has(FTFSType.RESTRICTED_LICENSE_EMBEDDING);
    }

    public boolean hasPreviewAndPrintEmbedding() {
        return this.has(FTFSType.PREVIEW_AND_PRINT_EMBEDDING);
    }

    public boolean hasEditableEmbedding() {
        return this.has(FTFSType.EDITABLE_EMBEDDING);
    }

    public boolean hasNoSubsetting() {
        return this.has(FTFSType.NO_SUBSETTING);
    }

    public boolean hasBitmapEmbeddingOnly() {
        return this.has(FTFSType.BITMAP_EMBEDDING_ONLY);
    }

}
