package generaloss.freetype.freetype;

import generaloss.freetype.BitMask;

public class FSTypeFlags extends BitMask<FTFSType> {

    public FSTypeFlags() { }

    public FSTypeFlags(int bits) {
        super(bits);
    }


    public boolean hasInstallableEmbedding() {
        return super.has(FTFSType.INSTALLABLE_EMBEDDING);
    }

    public boolean hasRestrictedLicenseEmbedding() {
        return super.has(FTFSType.RESTRICTED_LICENSE_EMBEDDING);
    }

    public boolean hasPreviewAndPrintEmbedding() {
        return super.has(FTFSType.PREVIEW_AND_PRINT_EMBEDDING);
    }

    public boolean hasEditableEmbedding() {
        return super.has(FTFSType.EDITABLE_EMBEDDING);
    }

    public boolean hasNoSubsetting() {
        return super.has(FTFSType.NO_SUBSETTING);
    }

    public boolean hasBitmapEmbeddingOnly() {
        return super.has(FTFSType.BITMAP_EMBEDDING_ONLY);
    }


    @Override
    public String toString() {
        return super.toString(FTFSType.class);
    }

}
