package dev;

import generaloss.freetype.FreeType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

public class NullTests {

    public static void main(String[] args) {
        for(Method method: FreeType.class.getDeclaredMethods()) {
            if(!Modifier.isPublic(method.getModifiers()) || !method.getName().startsWith("ft"))
                continue;

            System.out.println("invoke " + method.getName());
            try{
                method.setAccessible(true);
                final Class<?>[] types = method.getParameterTypes();
                final Object[] params = new Object[types.length];
                for(int i = 0; i < params.length; i++) {
                    final Class<?> type = types[i];
                    if(type.isPrimitive()) {
                        if(type.equals(boolean.class)) {
                            params[i] = false;
                        }else{
                            params[i] = 0;
                        }
                    }else{
                        params[i] = null;
                    }
                }
                method.invoke(null, params);
            }catch(Exception e){
                System.out.println(e.getCause());
            }
        }
    }

}
