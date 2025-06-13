package generaloss.freetype.types;

public class FTTag {

    public static int ftMakeTag(int x1, int x2, int x3, int x4) {
        return (x1 << 24) | (x2 << 16) | (x3 << 8) | x4;
    }

    public static int ftMakeTag(String tag) {
        return ftMakeTag(tag.charAt(0), tag.charAt(1), tag.charAt(2), tag.charAt(3));
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
