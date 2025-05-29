package generaloss.freetype.types;

public class FTTag {

    // FT_ENC_TAG(value, a, b, c, d)
    public static int encode(char a, char b, char c, char d) {
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    public static int encode(String tag) {
        return encode(tag.charAt(0), tag.charAt(1), tag.charAt(2), tag.charAt(3));
    }

    public static char[] decode(long tag) {
        return new char[] {
            (char) ((tag >> 24) & 0xFF),
            (char) ((tag >> 16) & 0xFF),
            (char) ((tag >> 8) & 0xFF),
            (char) ((tag) & 0xFF),
        };
    }

    public static String decodeString(long tag) {
        return new String(decode(tag));
    }

}
