package generaloss.freetype.stroke;

public enum FTStrokerLinejoin {

    /** Used to render rounded line joins. Circular arcs are used to join two lines smoothly. */
    ROUND          (0),

    /** Used to render beveled line joins. The outer corner of the joined lines is filled by enclosing the triangular region of the corner with a straight line between the outer corners of each stroke. */
    BEVEL          (1),

    /** Used to render mitered line joins, with variable bevels if the miter limit is exceeded. The intersection of the strokes is clipped perpendicularly to the bisector, at a distance corresponding to the miter limit. This prevents long spikes being created. FTStrokerLinejoin.MITER_VARIABLE generates a mitered line join as used in XPS. FTStrokerLinejoin.MITER is an alias for FTStrokerLinejoin.MITER_VARIABLE, retained for backward compatibility. */
    MITER          (2),
    MITER_VARIABLE (MITER.value),

    /** Used to render mitered line joins, with fixed bevels if the miter limit is exceeded. The outer edges of the strokes for the two segments are extended until they meet at an angle. A bevel join (see above) is used if the segments meet at too sharp an angle and the outer edges meet beyond a distance corresponding to the meter limit. This prevents long spikes being created. FTStrokerLinejoin.MITER_FIXED generates a miter line join as used in PostScript and PDF. */
    MITER_FIXED    (3);

    public final int value;

    FTStrokerLinejoin(int value) {
        this.value = value;
    }

    public static FTStrokerLinejoin byValue(int value) {
        if(value > 2)
            value++;
        return values()[value];
    }

}
