package generaloss.freetype;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FTStructRegistry {

    private static final Map<Long, FTStruct> STRUCT_BY_POINTER_MAP = new ConcurrentHashMap<>();

    protected static void register(FTStruct struct) {
        // registry debugging
        if(exists(struct.pointer))
            System.err.println("FTStruct double register attempt for pointer " + struct.pointer);

        STRUCT_BY_POINTER_MAP.put(struct.getPointer(), struct);
    }

    protected static void unregister(long pointer) {
        // registry debugging
        if(!exists(pointer))
            System.err.println("FTStruct unregister attempt for unknown pointer: " + pointer);

        STRUCT_BY_POINTER_MAP.remove(pointer);
    }

    public static boolean exists(long pointer) {
        return STRUCT_BY_POINTER_MAP.containsKey(pointer);
    }

    @SuppressWarnings("unchecked")
    public static <T extends FTStruct> T get(long pointer) {
        return (T) STRUCT_BY_POINTER_MAP.get(pointer);
    }

    public static <T extends FTStruct> T getOrCreate(long pointer, Function<Long, T> consumer) {
        if(exists(pointer))
            return get(pointer);
        return consumer.apply(pointer);
    }

}
