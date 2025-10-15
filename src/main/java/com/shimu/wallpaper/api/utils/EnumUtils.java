package com.shimu.wallpaper.api.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnumUtils {

    public static <E extends Enum<E>> Map<String, Object> enumToMap(Class<E> enumClass) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            for (E e : enumClass.getEnumConstants()) {
                Map<String, Object> fieldMap = new LinkedHashMap<>();
                for (Field f : enumClass.getDeclaredFields()) {
                    if (!f.isEnumConstant() && !Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true);
                        fieldMap.put(f.getName(), f.get(e));
                    }
                }
                result.put(e.name(), fieldMap);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
