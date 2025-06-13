package generaloss.freetype.freetype;

import generaloss.freetype.types.FTTag;

public enum FTEncoding {

    /** Reserved for all formats except BDF, PCF, and Windows FNT; see below for more information. */
    NONE           (0),

    /** Microsoft Symbol encoding, used to encode mathematical symbols and wingdings. For more information, see ‘<a href="https://www.microsoft.com/typography/otspec/recom.htm#non-standard-symbol-fonts">microsoft.com</a>’, ‘<a href="http://www.kostis.net/charsets/symbol.htm">kostis.net</a>’, and ‘<a href="http://www.kostis.net/charsets/wingding.htm">kostis.net</a>’.
     * This encoding uses character codes from the PUA (Private Unicode Area) in the range U+F020-U+F0FF. */
    MS_SYMBOL      ('s', 'y', 'm', 'b'),

    /** The Unicode character set. This value covers all versions of the Unicode repertoire, including ASCII and Latin-1. Most fonts include a Unicode charmap, but not all of them.
      * For example, if you want to access Unicode value U+1F028 (and the font contains it), use value 0x1F028 as the input value for FT_Get_Char_Index. */
    UNICODE        ('u', 'n', 'i', 'c'),

    /** Shift JIS encoding for Japanese. More info at ‘<a href="https://en.wikipedia.org/wiki/Shift_JIS">wikipedia.org</a>’. See note on multi-byte encodings below. */
    SJIS           ('s', 'j', 'i', 's'),
    MS_SJIS        (SJIS.value),

    /** Corresponds to encoding systems mainly for Simplified Chinese as used in People's Republic of China (PRC). The encoding layout is based on GB 2312 and its supersets GBK and GB 18030. */
    PRC            ('g', 'b', ' ', ' '),
    GB2312         (PRC.value),
    MS_GB2312      (PRC.value),

    /** Corresponds to an encoding system for Traditional Chinese as used in Taiwan and Hong Kong. */
    BIG5           ('b', 'i', 'g', '5'),
    MS_BIG5        (BIG5.value),

    /** Corresponds to the Korean encoding system known as Extended Wansung (MS Windows code page 949). For more information see ‘<a href="https://www.unicode.org/Public/MAPPINGS/VENDORS/MICSFT/WindowsBestFit/bestfit949.txt">unicode.org</a>’. */
    WANSUNG        ('w', 'a', 'n', 's'),
    MS_WANSUNG     (WANSUNG.value),

    /** The Korean standard character set (KS C 5601-1992), which corresponds to MS Windows code page 1361. This character set includes all possible Hangul character combinations. */
    JOHAB          ('j', 'o', 'h', 'a'),
    MS_JOHAB       (JOHAB.value),

    /** Adobe Standard encoding, as found in Type 1, CFF, and OpenType/CFF fonts. It is limited to 256 character codes. */
    ADOBE_STANDARD ('A', 'D', 'O', 'B'),

    /** Adobe Expert encoding, as found in Type 1, CFF, and OpenType/CFF fonts. It is limited to 256 character codes. */
    ADOBE_EXPERT   ('A', 'D', 'B', 'E'),

    /** Corresponds to a custom encoding, as found in Type 1, CFF, and OpenType/CFF fonts. It is limited to 256 character codes. */
    ADOBE_CUSTOM   ('A', 'D', 'B', 'C'),

    /** Corresponds to a Latin-1 encoding as defined in a Type 1 PostScript font. It is limited to 256 character codes. */
    ADOBE_LATIN_1  ('l', 'a', 't', '1'),

    /** This value is deprecated and was neither used nor reported by freetype.h.txt. Don't use or test for it. */
    OLD_LATIN_2    ('l', 'a', 't', '2'),

    /** Apple roman encoding. Many TrueType and OpenType fonts contain a charmap for this 8-bit encoding, since older versions of Mac OS are able to use it. */
    APPLE_ROMAN    ('a', 'r', 'm', 'n');

    public final int value;

    FTEncoding(int value) {
        this.value = value;
    }

    FTEncoding(char a, char b, char c, char d) {
        this.value = FTTag.ftMakeTag(a, b, c, d);
    }

    public static FTEncoding byValue(int value) {
        for(FTEncoding encoding: values())
            if(encoding.value == value)
                return encoding;
        return null;
    }

    @Override
    public String toString() {
        return super.name();
    }

}
