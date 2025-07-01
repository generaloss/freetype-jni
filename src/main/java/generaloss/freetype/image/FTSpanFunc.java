package generaloss.freetype.image;

import generaloss.freetype.FTStructCache;

@FunctionalInterface
public interface FTSpanFunc {

    // void FT_SpanFunc(int y, int count, const FT_Span* spans, void* user);
    void invoke(int y, int count, FTSpan[] spans);

    default void invokeNative(int y, int count, long[] spansPointers) {
        final FTSpan[] spans = new FTSpan[count];
        for(int i = 0; i < count; i++)
            spans[i] = FTStructCache.getOrCreate(FTSpan.class, spansPointers[i], FTSpan::new);

        this.invoke(y, count, spans);
    }

}
