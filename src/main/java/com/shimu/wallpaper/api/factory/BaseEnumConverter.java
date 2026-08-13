package com.shimu.wallpaper.api.factory;

import com.google.common.collect.Maps;
import com.shimu.wallpaper.api.enums.BaseEnum;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Map;

public class BaseEnumConverter<T extends BaseEnum> implements Converter<String, T> {

    private final Map<String, T> hashMap = Maps.newHashMap();

    public BaseEnumConverter(Class<T> targetType) {
        Arrays.stream(targetType.getEnumConstants())
                .forEach(t -> hashMap.put(t.getValue(), t));
    }

    @Nullable
    @Override
    public T convert(@NonNull String source) {
        T result = hashMap.get(source);
        if (result == null) {
            throw new WallpaperApiException("invalid value", 500);
        }
        return result;
    }
}
