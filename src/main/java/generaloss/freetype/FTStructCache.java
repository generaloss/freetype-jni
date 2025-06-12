package generaloss.freetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FTStructCache {

    private static final Map<Long, FTStruct> STRUCT_BY_POINTER_MAP = new ConcurrentHashMap<>();
    private static final List<FTStruct> UNCASTED_STRUCTS = new ArrayList<>(); // second cache layer for caching FTGlyph / FTBitmapGlyph with same pointer

    protected static void register(FTStruct struct) {
        if(struct.isDestroyed())
            throw new IllegalArgumentException("Cannot register FTStruct with pointer=0L");
        STRUCT_BY_POINTER_MAP.put(struct.getPointer(), struct);
    }

    protected static void unregister(long pointer) {
        STRUCT_BY_POINTER_MAP.remove(pointer);
    }

    protected static void clear() {
        STRUCT_BY_POINTER_MAP.clear();
        UNCASTED_STRUCTS.clear();
    }

    public static boolean exists(long pointer) {
        return STRUCT_BY_POINTER_MAP.containsKey(pointer);
    }

    @SuppressWarnings("unchecked")
    public static <T extends FTStruct> T get(long pointer) {
        return (T) STRUCT_BY_POINTER_MAP.get(pointer);
    }

    public static <T extends FTStruct> T getOrCreate(Class<T> type, long pointer, Function<Long, T> consumer) {
        if(pointer == 0L)
            return null;

        if(exists(pointer)) {
            final T struct = get(pointer);
            if(struct.getClass() == type)
                return struct;

            // unable to cast
            // DEBUG System.out.println("Unable to cast " + struct.getClass().getSimpleName() + " to " + type.getSimpleName() + ". Rewriting");
            unregister(pointer);
            UNCASTED_STRUCTS.add(struct);
        }
        return getUncastedOrCreate(type, pointer, consumer);
    }

    @SuppressWarnings("unchecked")
    private static <T extends FTStruct> T getUncastedOrCreate(Class<T> type, long pointer, Function<Long, T> consumer) {
        UNCASTED_STRUCTS.removeIf(FTStruct::isDestroyed);

        for(FTStruct struct: UNCASTED_STRUCTS) {
            if(struct.getClass() == type && struct.getPointer() == pointer) {
                // DEBUG System.out.println("Found uncasted " + struct.getClass().getSimpleName() + ". Register");
                UNCASTED_STRUCTS.remove(struct);
                // replace with previous uncasted struct
                register(struct);
                return (T) struct;
            }
        }

        // create new struct
        return consumer.apply(pointer);
    }

}
