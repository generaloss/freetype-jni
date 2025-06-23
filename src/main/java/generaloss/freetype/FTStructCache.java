package generaloss.freetype;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FTStructCache {

    private static final Map<Long, Map<Class<?>, FTStruct>> STRUCT_MAP = new ConcurrentHashMap<>();

    protected static void register(FTStruct struct) {
        if(struct == null)
            return;
        if(struct.isDestroyed())
            throw new IllegalArgumentException("Cannot register FTStruct with pointer=0L");

        STRUCT_MAP.compute(struct.getPointer(), (pointer, typeStructMap) -> {
            if(typeStructMap == null)
                typeStructMap = new HashMap<>();

            final Class<?> type = struct.getClass();

            if(typeStructMap.containsKey(type))
                throw new RuntimeException("Double FTStruct registration");

            typeStructMap.put(type, struct);

            // System.out.println("Pointer " + pointer + " pool " + (typeStructMap.size() == 1 ? "created" : "increased") + " {");
            // for(FTStruct value: typeStructMap.values())
            //     System.out.println("  " + value + (value == struct ? " (new)" : ""));
            // System.out.println("}");

            return typeStructMap;
        });
    }

    protected static void unregister(long pointer, Class<?> type) {
        final Map<Class<?>, FTStruct> typeStructMap = STRUCT_MAP.get(pointer);
        if(typeStructMap == null)
            return;

        typeStructMap.remove(type);

        if(typeStructMap.isEmpty())
            STRUCT_MAP.remove(pointer, typeStructMap);

        // if(typeStructMap.isEmpty()) {
        //     System.out.println("Pointer " + pointer + " pool removed { }");
        // }else{
        //     System.out.println("Pointer " + pointer + " pool decreased {");
        //     for(FTStruct value: typeStructMap.values())
        //         System.out.println("  " + value);
        //     System.out.println("}");
        // }
    }

    protected static void unregister(FTStruct struct) {
        if(struct == null)
            return;
        if(struct.isDestroyed())
            throw new IllegalArgumentException("Cannot unregister FTStruct with pointer=0L");
        unregister(struct.getPointer(), struct.getClass());
    }

    protected static void clear() {
        STRUCT_MAP.clear();
    }

    public static int size() {
        return STRUCT_MAP.size();
    }

    public static boolean exists(long pointer, Class<?> type) {
        final Map<Class<?>, FTStruct> classStructMap = STRUCT_MAP.get(pointer);
        if(classStructMap == null)
            return false;
        return classStructMap.containsKey(type);
    }

    public static boolean exists(FTStruct struct) {
        if(struct == null)
            return false;
        return exists(struct.getPointer(), struct.getClass());
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(long pointer, Class<T> type) {
        final Map<Class<?>, FTStruct> classStructMap = STRUCT_MAP.get(pointer);
        if(classStructMap == null)
            return null;
        return (T) classStructMap.get(type);
    }

    public static <T extends FTStruct> T getOrCreate(Class<T> type, long pointer, Function<Long, T> consumer) {
        if(pointer == 0L)
            return null;

        final T struct = get(pointer, type);
        if(struct == null)
            return consumer.apply(pointer);

        return struct;
    }

}
